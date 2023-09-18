package com.wei.limit.limiter.handler;

import com.wei.limit.DTO.MataData;
import com.wei.limit.limiter.Limiter;
import com.wei.limit.limiter.impl.CounterLimiter;
import org.springframework.stereotype.Component;

/**
 * 限流处理器
 */
@Component
public class FlowControlHandler implements Limiter {

    // 默认限流方式是计数器
    static Limiter limiter = CounterLimiter.getInstance();

    @Override
    public void set(String key, Integer value, long time) {
        limiter.set(key, value, time);
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
    public void incr(String key, long time) {
        limiter.incr(key, time);
    }

    @Override
    public boolean check(MataData restrictDTO) {
        return limiter.check(restrictDTO);
    }

    public static void setLimiter(Limiter r) {
        limiter = r;
    }
}
