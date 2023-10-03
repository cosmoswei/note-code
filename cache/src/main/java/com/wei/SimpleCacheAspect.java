package com.wei;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.support.AopUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


/**
 * @author 黄旭伟
 */
@ConditionalOnProperty(
        name = "mkt.megrez.simple.cache.aspect",
        matchIfMissing = true,
        havingValue = "true"
)
@Slf4j
@Aspect
@Component
public class SimpleCacheAspect {

    @Pointcut(value = "@annotation(annotation.SimpleCache)")
    public void cachePointcut() {
    }

    private static final Cache<String, Object> LOCAL_CACHE = Caffeine.newBuilder()
            .expireAfterWrite(30, TimeUnit.MINUTES)
            .maximumSize(1000)
            .build();

    @Around("cachePointcut()")
    public Object cacheAround(ProceedingJoinPoint point) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method targetMethod = AopUtils.getMostSpecificMethod(methodSignature.getMethod(), point.getTarget().getClass());
        //获取注解信息
        SimpleCache simpleCache = targetMethod.getAnnotation(SimpleCache.class);
        //获取缓存key
        String cacheKey = this.getKey(targetMethod, simpleCache, point);
        return this.getData(cacheKey, point);
    }

    /**
     * 获取缓存Key
     */
    private String getKey(Method targetMethod, SimpleCache simpleCache, ProceedingJoinPoint point) {
        String prefix = simpleCache.prefix();
        if (null == prefix || prefix.isEmpty()) {
            prefix = targetMethod.getDeclaringClass().getName() + "." + targetMethod.getName();
        }
        String key = simpleCache.key();
        if (null == key || key.isEmpty()) {
            return prefix;
        }
        String parser = SpelUtils.parser(point, key).toString();
        return prefix + parser;
    }


    /**
     * 获取数据
     */
    private Object getData(final String cacheKey, final ProceedingJoinPoint joinPoint) throws Throwable {
        //从缓存读取数据
        Object result = getDataFromCache(cacheKey);

        if (Objects.isNull(result)) {
            result = joinPoint.proceed();
            // 将查询到的数据写入缓存
            setDataToCache(cacheKey, result);
        } else {
            log.debug("命中缓存，key = {}，data = {}", cacheKey, ObjectUtils.nullSafeToString(result));
        }
        return result;

    }

    /**
     * 从缓存中获取数据
     */
    private Object getDataFromCache(String cacheKey) {
        return LOCAL_CACHE.getIfPresent(cacheKey);
    }

    /**
     * 设置数据到缓存
     */
    private void setDataToCache(String cacheKey, Object data) {
        log.debug("设置数据到缓存，key = {}，data = {}", cacheKey, ObjectUtils.nullSafeToString(data));
        LOCAL_CACHE.put(cacheKey, data);
    }
}
