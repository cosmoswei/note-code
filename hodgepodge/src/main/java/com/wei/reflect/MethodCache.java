package com.wei.reflect;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MethodCache {
    private static Map<String, Method> cache = new HashMap<>();

    public static Method getMethod(Class<?> clazz, String methodName) throws NoSuchMethodException {
        String key = clazz.getName() + "#" + methodName;
        Method method = cache.get(key);
        if (method == null) {
            method = clazz.getDeclaredMethod(methodName);
            cache.put(key, method);
        }
        return method;
    }
}