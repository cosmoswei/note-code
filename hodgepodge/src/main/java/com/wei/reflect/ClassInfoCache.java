package com.wei.reflect;

import java.util.HashMap;
import java.util.Map;

public class ClassInfoCache {
    private static Map<String, Class<?>> cache = new HashMap<>();

    public static Class<?> getClass(String className) throws ClassNotFoundException {
        Class<?> clazz = cache.get(className);
        if (clazz == null) {
            clazz = Class.forName(className);
            cache.put(className, clazz);
        }
        return clazz;
    }
}