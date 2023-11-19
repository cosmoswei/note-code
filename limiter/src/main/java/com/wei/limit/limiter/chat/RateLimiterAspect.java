package com.wei.limit.limiter.chat;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
@Slf4j
public class RateLimiterAspect {

    private static final Map<String, Map<String, RateLimiter>> rateLimitersMap = new ConcurrentHashMap<>();

    @Pointcut("@annotation(com.wei.limit.limiter.chat.RateLimit)")
    public void rateLimitedMethods() {
    }

    @Around("rateLimitedMethods()")
    public Object checkRateLimit(ProceedingJoinPoint joinPoint) throws Throwable {
        RateLimit rateLimitAnnotation = getRateLimitAnnotation(joinPoint);
        String methodName = joinPoint.getSignature().toShortString();
        String type = rateLimitAnnotation.type();

        // 获取对应类型的限流器
        RateLimiter rateLimiter = getRateLimiter(methodName, type, rateLimitAnnotation);

        // 检查是否超过限流
        if (rateLimiter.isRateLimited()) {
            log.info("接口访问频率过高，被限流！");
            throw new RuntimeException("接口访问频率过高，被限流！");
        } else {
            // 更新限流器状态
            log.info("成功！");
        }
        rateLimiter.update();

        // 执行原始方法
        Object result = joinPoint.proceed();

        return result;
    }

    private RateLimit getRateLimitAnnotation(JoinPoint joinPoint) throws NoSuchMethodException {
        return joinPoint
                .getSignature()
                .getDeclaringType()
                .getMethod(((org.aspectj.lang.reflect.MethodSignature) joinPoint.getSignature()).getMethod().getName())
                .getAnnotation(RateLimit.class);
    }

    private RateLimiter getRateLimiter(String methodName, String type, RateLimit rateLimitAnnotation) {
        rateLimitersMap.putIfAbsent(methodName, new ConcurrentHashMap<>());
        rateLimitersMap.get(methodName).putIfAbsent(type, createRateLimiter(rateLimitAnnotation));
        return rateLimitersMap.get(methodName).get(type);
    }

    private RateLimiter createRateLimiter(RateLimit rateLimitAnnotation) {
        switch (rateLimitAnnotation.type()) {
            case "counter":
                return new CounterRateLimiter(rateLimitAnnotation.limit(), rateLimitAnnotation.interval());
            case "slidingWindow":
                return new SlidingWindowRateLimiterV2(rateLimitAnnotation.limit(), rateLimitAnnotation.interval());
            // 可以根据需要扩展其他类型的限流器
            default:
                throw new IllegalArgumentException("Unsupported rate limiter type: " + rateLimitAnnotation.type());
        }
    }
}
