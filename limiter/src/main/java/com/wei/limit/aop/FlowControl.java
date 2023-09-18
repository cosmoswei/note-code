package com.wei.limit.aop;

import com.wei.limit.constant.FlowControlConstant;

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
public @interface FlowControl {

    int limit() default 0;

    int time() default 0;

    String key() default "";

    /**
     * 限流器类型
     */
    String type() default FlowControlConstant.COUNTER;

    String msg() default "系统服务繁忙";

    String callback() default "";
}
