package com.wei.limit.aop;

import com.wei.limit.constant.SimpleLimiterConstant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author: Xhy
 * CreateTime: 2022-06-05 00:37
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SimpleLimiter {

    /**
     * 限流次数
     */
    int limit() default 0;

    /**
     * 间隔时间，单位毫秒
     */
    int interval() default 0;


    /**
     * 限流Key 默认为方法名
     */
    String key() default "";

    /**
     * 限流器类型
     */
    String type() default SimpleLimiterConstant.COUNTER;

    /**
     * 限流异常提示
     */
    String limitMsg() default "系统服务繁忙";

    /**
     * 回调方法
     */
    String callback() default "";
}
