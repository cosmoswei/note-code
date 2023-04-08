package com.wei.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class DataSourceAspect {


    @Pointcut("@annotation(com.wei.datasource.DataSource)")
    public void checkPointCut() {

    }

    @Before("checkPointCut()")
    public void checkBefore(JoinPoint joinPoint) {

        try {
            Class<?> clazz = joinPoint.getTarget().getClass();
            String methodName = joinPoint.getSignature().getName();
            Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();
            Method method = clazz.getMethod(methodName, parameterTypes);
            DataSource dataSource = method.getAnnotation(DataSource.class);
            String dataSourceKey = dataSource.value();
            DataSourceContextHolder.setKey(dataSourceKey);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    @After("checkPointCut()")
    public void checkAfter(){
        DataSourceContextHolder.clearKey();
    }
}
