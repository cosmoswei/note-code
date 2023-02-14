package com.wei.util.function;

/**
 * 抛异常接口
 **/
@FunctionalInterface
public interface ThrowFunction<T> {

    /**
     * 抛出异常信息
     **/
    void message(T message);
}