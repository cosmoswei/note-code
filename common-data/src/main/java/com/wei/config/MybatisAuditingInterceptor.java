package com.wei.config;

import com.wei.entity.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Intercepts(@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}))
public class MybatisAuditingInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();

        MappedStatement mappedStatement = (MappedStatement) args[0];
        // 不是很清楚这个 SqlCommandType 是如何归类得到的，很奇怪
        // 方法命名为 delete_xxx 时类型就是 DELETE, 而实际的 Mapper 实现是一个 update ...
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();

        // 如果当前 SQL 命令类型不是新增、更新或删除则提前返回
        if (sqlCommandType != SqlCommandType.INSERT && sqlCommandType != SqlCommandType.UPDATE &&
                sqlCommandType != SqlCommandType.DELETE) {
            return invocation.proceed();
        }

        Object entity = args[1];

        if (entity == null) {
            // 暂时不清楚这里直接返回 null 会有什么影响，还是把方法执行一下吧
            return invocation.proceed();
        }

        // 对于批量新增、更新操作的处理
        // 这里并不是很严谨，如果接口方法声明处的参数指定了 @Param 注解的话，这里可能会失效
        if (entity.getClass() == MapperMethod.ParamMap.class) {
            MapperMethod.ParamMap<?> paramMap = (MapperMethod.ParamMap<?>) entity;
            if (paramMap.containsKey("collection")) {
                Collection<?> objects = (Collection<?>) paramMap.get("collection");
                for (Object o : objects) {
                    fillEntity(o, sqlCommandType);
                }
            } else if (paramMap.containsKey("et")) {
                fillEntity(paramMap.get("et"), sqlCommandType);
            }
        } else {
            fillEntity(entity, sqlCommandType);
        }


        return invocation.proceed();
    }

    private void fillEntity(Object entity, SqlCommandType sqlCommandType) throws IllegalAccessException {
        Class<?> clazz = entity.getClass();

        // 假设自定义的实体类应该都是由 AppClassLoader 加载的
        // 再假设 JDK 自带的相关类是由 BootClassLoader 或者 PlatformClassLoader 加载的
        // 可以确定的是常见的 JDK 自带的类都是由 BootClassLoader 加载的，程序获取会得到 null，因此这里简单过滤一些
        if (clazz.getClassLoader() == null) {
            return;
        }

        Class<?> superclass = clazz.getSuperclass();

        List<Field> fields = Stream
                .concat(
                        Arrays.stream(clazz.getDeclaredFields()),
                        Arrays.stream(superclass.getDeclaredFields())
                )
                .collect(Collectors.toList());

        for (Field field : fields) {
            if (!trySetAccessible(field)) {
                return;
            }

            fillCreatedDate(entity, field, sqlCommandType);
            fillLastModifiedDate(entity, field);

            // 如果当前应用接入了 Kylin 认证的依赖，则可以根据当前请求上下文更新 @CreatedBy 和 @LastModifiedBy 注解标识的字段
            fillCreatedBy(entity, field, sqlCommandType);
            fillLastModifiedBy(entity, field);
        }
    }

    private boolean trySetAccessible(Field field) {
        try {
            field.setAccessible(true);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void fillCreatedBy(Object object, Field field, SqlCommandType sqlCommandType) throws IllegalAccessException {
        if (!field.isAnnotationPresent(CreatedBy.class) || sqlCommandType != SqlCommandType.INSERT) {
            return;
        }

        fillUserField(object, field);
    }

    private void fillLastModifiedBy(Object object, Field field) throws IllegalAccessException {
        if (!field.isAnnotationPresent(LastModifiedBy.class)) {
            return;
        }

        fillUserField(object, field);
    }

    private void fillCreatedDate(Object object, Field field, SqlCommandType sqlCommandType) throws IllegalAccessException {
        // 如果该属性没有被 @CreatedDate 注解所标识则直接返回
        // 只有 SQL 命令类型是新增时才需要填充属性值
        if (!field.isAnnotationPresent(CreatedDate.class) || sqlCommandType != SqlCommandType.INSERT) {
            return;
        }

        fillDateField(object, field);
    }

    private void fillLastModifiedDate(Object object, Field field) throws IllegalAccessException {
        // 如果该属性没有被 @LastModifiedDate 注解所标识则直接返回
        // SQL 命令类型是新增或者更新都需要更新该属性的值
        if (!field.isAnnotationPresent(LastModifiedDate.class)) {
            return;
        }

        fillDateField(object, field);
    }

    /**
     * 填充时间类型的字段
     *
     * @param object 实体类对象
     * @param field  待填充的字段
     * @throws IllegalAccessException 填充字段值时可能抛出
     */
    private void fillDateField(Object object, Field field) throws IllegalAccessException {
        Class<?> type = field.getType();

        if (type == LocalDate.class) {
            field.set(object, LocalDate.now());
        } else if (type == LocalDateTime.class) {
            field.set(object, LocalDateTime.now());
        } else if (type == Date.class) {
            field.set(object, new Date(System.currentTimeMillis()));
        } else if (type == Timestamp.class) {
            field.set(object, new Timestamp(System.currentTimeMillis()));
        } else if (type == java.util.Date.class) {
            field.set(object, new java.util.Date());
        } else if (type == Long.class) {
            field.set(object, System.currentTimeMillis());
        } else {
            log.debug("Can't fill {}", field);
        }
    }

    /**
     * 填充用户名字段（创建人、更新人）
     *
     * @param object 实体类对象
     * @param field  待填充的字段
     * @throws IllegalAccessException 填充字段值时可能抛出
     */
    private void fillUserField(Object object, Field field) throws IllegalAccessException {
        // 如果该属性不是字符串类型则直接返回（无 ORM 框架也没有用户表，为了方便起见应该直接存储用户名）
        if (field.getType() != String.class && field.getType() != Long.class) {
            return;
        }

        UserDTO currentUser = getCurrentUser();
        // 未从当前上下文中获取到用户信息，填充默认值或直接返回
        if (currentUser == null) {
            if (field.getType() == String.class) {
                field.set(object, "Unknown");
            } else {
                field.set(object, 0L);
            }
        } else {
            if (field.getType() == String.class) {
                // 暂时这里也还是改成 Kylin 用户主键吧
                field.set(object, String.valueOf(currentUser.getUserId()));
            } else {
                field.set(object, currentUser.getUserId());
            }
        }
    }

    /**
     * 获取当前请求上下文中的当前用户
     *
     */
    private UserDTO getCurrentUser() {
        try {
            HttpServletRequest request =
                    ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            return (UserDTO) request.getAttribute("userDTO");
        } catch (Exception e) {
            return null;
        }
    }

}
