package com.wei.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 排序工具类
 *
 * @author huangxuwei
 * @date 2023年02月06日 13:50
 */
@Slf4j
public class OrderUtils {

    private OrderUtils() {
    }

    /**
     * 根据指定序列排序
     *
     * @param seq        指定的排序序列
     * @param targetList 待排序列表
     * @param filed      需要排序的字段
     * @param <V>        列表对象泛型
     */
    public static <V> void orderBySeq(List<String> seq, List<V> targetList, String filed) {

        //按照字段名来排序
        targetList.sort(((o1, o2) -> {
            int io1 = 0;
            int io2 = 0;
            try {
                Method method = o1.getClass().getMethod(getMethodStr(filed));
                io1 = seq.indexOf(method.invoke(o1));
                io2 = seq.indexOf(method.invoke(o2));

                if (io1 != -1) {
                    io1 = targetList.size() - io1;
                }
                if (io2 != -1) {
                    io2 = targetList.size() - io2;
                }
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("反射获取方法异常 info： {}", e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("反射访问方法异常 info： {}", e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException("反射调用字节码异常 info： {}", e);
            }

            return io2 - io1;
        }));
    }

    /**
     * 拼接 get 属性方法名
     *
     * @param filed 属性
     * @return
     */
    private static String getMethodStr(String filed) {
        if (StringUtils.isBlank(filed)) {
            return StringUtils.EMPTY;
        }
        // todo 换一种更加优雅的写法
        return "get" + filed.substring(0, 1).toUpperCase() + filed.substring(1);
    }

}
