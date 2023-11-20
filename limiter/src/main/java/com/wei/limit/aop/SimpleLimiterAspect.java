package com.wei.limit.aop;


import com.wei.limit.DTO.MataData;
import com.wei.limit.exception.SimpleLimiterException;
import com.wei.limit.limiter.LimiterAbstract;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Author: Xhy
 * CreateTime: 2023-03-08 09:40
 */
@Aspect
@Component
@Slf4j
public class SimpleLimiterAspect {

    @Resource
    private Map<String, LimiterAbstract> limiterMap;

    /**
     * 拦截Limit注解的请求
     */
    @Around("@annotation(simpleLimiter)")
    public Object restriction(ProceedingJoinPoint joinPoint, SimpleLimiter simpleLimiter) throws Throwable {
        String key = getKey(joinPoint, simpleLimiter.key());
        int limit = simpleLimiter.limit();
        int time = simpleLimiter.interval();
        String type = simpleLimiter.type();
        LimiterAbstract limiterAbstract = limiterMap.get(type);
        MataData mataData = new MataData(limit, time, key);
        boolean result = limiterAbstract.limit(mataData);
        if (!result) {
            return joinPoint.proceed();
        } else {
            String callback = simpleLimiter.callback();
            if (null == callback || callback.isEmpty()) {
                throw new SimpleLimiterException(simpleLimiter.limitMsg());
            }
            // 限流失败，执行指定的回调方法
            Object target = joinPoint.getTarget();
            try {
                // 使用反射调用指定的回调方法
                return target.getClass()
                        .getMethod(callback)
                        .invoke(target);
            } catch (Exception e) {
                // 处理反射调用异常
                log.error("流控降低调用反射异常！", e);
                // 返回一个适当的响应，例如限流失败的提示
                return "Rate limit exceeded. Callback invoked.";
            }
        }

    }

    private String getKey(ProceedingJoinPoint joinPoint, String key) {
        Signature signature = joinPoint.getSignature();
        if (null == key || key.isEmpty()) {
            return signature.getDeclaringType().getName()
                    + "."
                    + signature.getName();
        }
        return key;
    }
}
