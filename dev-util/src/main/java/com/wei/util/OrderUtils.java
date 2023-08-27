package com.wei.util;

import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 排序工具类
 *
 * @author huangxuwei
 * @date 2023年02月06日 13:50
 */
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
    public static <V> void orderBySeq(List<?> seq, List<V> targetList, String filed) throws NoSuchMethodException {

        //按照字段名来排序
        Method method = targetList.get(0).getClass().getMethod(getMethodStr(filed));
        //按照字段名来排序
        targetList.sort(((obj1, obj2) -> {
            int index1;
            int index2;
            try {
                index1 = seq.indexOf(method.invoke(obj1));
                index2 = seq.indexOf(method.invoke(obj2));
                if (index1 != -1) {
                    index1 = targetList.size() - index1;
                }
                if (index2 != -1) {
                    index2 = targetList.size() - index2;
                }
            } catch (Exception e) {
                throw new RuntimeException("反射获取方法异常 info： {}", e);
            }
            return index2 - index1;
        }));
    }

    /**
     * 拼接 get 属性方法名
     *
     * @param filed 属性
     * @return 方法名
     */
    private static String getMethodStr(String filed) {
        String prefix = "get";
        if (StringUtils.isBlank(filed)) {
            return prefix;
        }
        return prefix + filed.substring(0, 1).toUpperCase() + filed.substring(1);
    }
}
