package com.wei.limit.aop;


import com.wei.limit.limiter.DTO.LimiterDTO;
import com.wei.limit.limiter.FlowControl;
import com.wei.limit.limiter.handler.LimiterHandler;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Author: Xhy
 * CreateTime: 2023-03-08 09:40
 */
@Aspect
@Component
public class LimiterAop {

    @Autowired
    HttpServletRequest request;

    @Autowired
    LimiterHandler restrictHandler;

    /**
     * 拦截Limit注解的请求
     */
    @Around("@annotation(limiter)")
    public Object restriction(ProceedingJoinPoint joinPoint, FlowControl limiter) throws Throwable {
        String key = request.getRequestURI();
        int limit = limiter.limit();
        int time = limiter.time();
        LimiterDTO limiterDTO = new LimiterDTO(limit, time, key);
        boolean result = restrictHandler.check(limiterDTO);
        if (!result) {
            return joinPoint.proceed();
        } else {
            String callback = limiter.callback();
            if (null == callback || callback.isEmpty()) {
                throw new RuntimeException("");
            }
            // 限流失败，执行指定的回调方法
            Object target = joinPoint.getTarget();
            try {
                // 使用反射调用指定的回调方法
                return target.getClass()
                        .getMethod(limiter.callback())
                        .invoke(target);
            } catch (Exception e) {
                // 处理反射调用异常
                e.printStackTrace();
                // 返回一个适当的响应，例如限流失败的提示
                return "Rate limit exceeded. Callback invoked.";

            }
        }

    }
}
