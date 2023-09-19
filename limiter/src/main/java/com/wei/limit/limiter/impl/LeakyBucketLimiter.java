package com.wei.limit.limiter.impl;


import com.wei.limit.DTO.MataData;
import com.wei.limit.constant.SimpleLimiterConstant;
import com.wei.limit.limiter.LimiterAbstract;
import org.springframework.stereotype.Component;

import java.util.concurrent.Semaphore;

/**
 * 漏桶限流
 */
@Component(SimpleLimiterConstant.LEAKY_BUCKET)
public class LeakyBucketLimiter extends LimiterAbstract {


    private final Semaphore semaphore;

    public LeakyBucketLimiter() {
        // 初始化漏桶，设置每秒允许的请求数
        // 初始许可为1，可以根据需求调整
        this.semaphore = new Semaphore(1);
    }

    @Override
    public boolean check(MataData restrictDTO) {
        return !semaphore.tryAcquire(1);
    }

    @Override
    public void remove(String key) {
        semaphore.release(1);
    }
}
