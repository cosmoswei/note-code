package com.wei.util;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 流 工具类
 *
 * @author PC
 */
public class StreamUtils {

    private StreamUtils() {
    }

    /**
     * 对象根据字段去重-用用于流中 filter
     *
     * @param keyExtractor 函数接口
     * @param <T>          泛型
     * @return 返回判断结果
     */
    public static <T> Predicate<T> distinctByAttr(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * 对象根据字段去重-直接操作List
     *
     * @param keyExtractor 函数接口
     * @param <T>          泛型
     * @return 返回去重后的List
     */
    public static <T> List<T> distinctByAttr(List<T> list, Function<? super T, ?> keyExtractor) {
        notNull(list);
        return list.stream().filter(distinctByAttr(keyExtractor)).collect(Collectors.toList());
    }

    /**
     * jdk9  takeWhile模拟接口
     */
    public static <T> List<T> takeWhile(List<T> list, Predicate<T> p) {
        int i = 0;
        for (T item : list) {
            if (!p.test(item)) {
                return list.subList(0, i);
            }
            i++;
        }
        return list;
    }

    /**
     * jdk9 dropWhile 模拟接口
     */
    public static <T> List<T> dropWhile(List<T> list, Predicate<T> p) {
        notNull(list);
        int i = 0;
        for (T item : list) {
            if (p.test(item)) {
                return list.subList(i, list.size());
            }
            i++;
        }
        return list;
    }

    private static void notNull(Object object) {
        if (object == null) {
            throw new NullPointerException("list 不可为空");
        }
    }
}