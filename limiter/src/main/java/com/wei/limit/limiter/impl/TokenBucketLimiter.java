package com.wei.limit.limiter.impl;


import com.wei.limit.DTO.MataData;
import com.wei.limit.limiter.LimiterAbstract;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 令牌桶限流
 * 按照一定的速率往桶中放token
 * 每次请求校验桶中是否有token
 */
@Deprecated
@Component("FlowControlConstant.TOKEN_BUCKET")
public class TokenBucketLimiter extends LimiterAbstract {

    // 桶容量
    private final Integer capacity = 5;

    // 每次放多少token
    private final Integer rate = 1;

    private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    // 剩余的token
    private AtomicInteger surplus = new AtomicInteger(5);

    private static TokenBucketLimiter instance;

    // 单例模式,如果创建多个 TokenBucketLimiter 则会有多个定时器
    public static synchronized TokenBucketLimiter getInstance() {
        if (instance == null) {
            instance = new TokenBucketLimiter();
        }
        return instance;
    }

    private TokenBucketLimiter() {
        init();
    }

    @Override
    public boolean check(MataData restrictDTO) {
        // 是否有容量
        if (surplus.get() <= 0) {
            return true;
        }
        surplus.getAndDecrement();
        return false;
    }

    // 定时器,以一定的速率往桶中存放token
    private void init() {
        // 间隔时间
        int interval = 5;
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            // 不能超过容量
            if (surplus.get() < capacity) {
                surplus.getAndAdd(rate);
            }
        }, 0, interval, TimeUnit.SECONDS);
    }
}
