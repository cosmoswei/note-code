package com.wei.util;

import java.util.Objects;
import java.util.function.Supplier;

public class ObjectUtils {

    private ObjectUtils() {
    }

    public static <T> T defaultIfNull(T object, Supplier<T> defaultValue) {
        return null != object ? object : defaultValue.get();
    }

    public static <T> T defaultIfNull(T object, T defaultValue) {
        return null != object ? object : defaultValue;
    }

    public void nonNull(Object o) {
        if (Objects.isNull(o)) {
            throw new NullPointerException("对象不能为空");
        }
    }
}
