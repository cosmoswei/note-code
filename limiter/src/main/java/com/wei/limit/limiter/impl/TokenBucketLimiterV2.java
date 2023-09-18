package com.wei.limit.limiter.impl;


import com.wei.limit.DTO.MataData;
import com.wei.limit.constant.FlowControlConstant;
import com.wei.limit.limiter.LimiterAbstract;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 令牌桶限流
 * 按照一定的速率往桶中放token
 * 每次请求校验桶中是否有token
 */
@Slf4j
@Component(FlowControlConstant.TOKEN_BUCKET)
public class TokenBucketLimiterV2 extends LimiterAbstract {

    private final Semaphore semaphore;

    /**
     * 令牌桶容量
     */
    private static final int capacity = 5;

    private ScheduledExecutorService scheduledExecutorService;


    public TokenBucketLimiterV2() {
        // 初始化令牌桶，设置每秒生成的令牌数
        this.semaphore = new Semaphore(capacity);
    }

    @Override
    public boolean check(MataData restrictDTO) {
        lazyInit();
        // 是否有容量
        return !semaphore.tryAcquire();
    }

    private void lazyInit() {
        log.info("lazyInit: method = {}", this.getClass().getCanonicalName());
        if (Objects.isNull(scheduledExecutorService)) {
            scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        }
        init();
    }

    // 定时器,以一定的速率往桶中存放token
    private void init() {
        // 间隔时间
        int interval = 5;
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            // 不能超过容量
            if (semaphore.availablePermits() < capacity) {
                semaphore.release(1);
            }
        }, 0, 1, TimeUnit.SECONDS);
    }
}
