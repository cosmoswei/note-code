package com.wei.limit.aop;


import com.wei.limit.limiter.DTO.LimiterDTO;
import com.wei.limit.limiter.Limit;
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
     * @param joinPoint
     * @param limiter
     * @return
     * @throws Throwable
     */
    @Around("@annotation(limiter)")
    public Object restriction(ProceedingJoinPoint joinPoint, Limit limiter) throws Throwable {
        String key = request.getRequestURI();
        int limit = limiter.limit();
        int time = limiter.time();
        LimiterDTO limiterDTO = new LimiterDTO(limit,time,key);
        boolean result = restrictHandler.check(limiterDTO);
        if (result){
            throw new RuntimeException(limiter.msg());
        }
        Object o = joinPoint.proceed();
        return o;
    }


}
