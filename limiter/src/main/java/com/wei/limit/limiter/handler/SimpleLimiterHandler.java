package com.wei.limit.limiter.handler;

import com.wei.limit.DTO.LimiterMataData;
import com.wei.limit.limiter.Limiter;
import com.wei.limit.limiter.impl.CounterLimiter;
import org.springframework.stereotype.Component;

/**
 * 限流处理器
 */
@Component
public class SimpleLimiterHandler implements Limiter {

    // 默认限流方式是计数器
    static Limiter limiter = CounterLimiter.getInstance();

    @Override
    public void set(String key, Integer value, long interval) {
        limiter.set(key, value, interval);
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
    public void incr(String key, int interval) {
        limiter.incr(key, interval);
    }

    @Override
    public boolean limit(LimiterMataData limiterMataData) {
        return limiter.limit(limiterMataData);
    }

    public static void setLimiter(Limiter r) {
        limiter = r;
    }
}
