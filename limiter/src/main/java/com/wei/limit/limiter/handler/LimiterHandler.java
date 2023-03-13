package com.wei.limit.limiter.handler;

import com.wei.limit.limiter.DTO.LimiterDTO;
import com.wei.limit.limiter.Limiter;
import com.wei.limit.limiter.impl.CounterLimiter;
import org.springframework.stereotype.Component;

/**
 * Author: Xhy
 * CreateTime: 2023-03-08 09:37
 * 限流处理器
 */
@Component
public class LimiterHandler implements Limiter {

    // 默认限流方式是计数器
    static Limiter limiter = CounterLimiter.getInstance();

    @Override
    public void set(String key, Integer value, long time) {
        limiter.set(key,value,time);
    }

    @Override
    public Integer get(String key) {
        return limiter.get(key);
    }

    @Override
    public void remove(String key) {
        limiter.remove(key);
    }

    @Override
    public void incr(String key,long time) {
        limiter.incr(key,time);
    }

    @Override
    public boolean check(LimiterDTO restrictDTO) {
        return limiter.check(restrictDTO);
    }

    public static void setLimiter(Limiter r){
        limiter = r;
    }
}
