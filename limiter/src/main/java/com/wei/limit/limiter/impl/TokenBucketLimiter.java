package com.wei.limit.limiter.impl;


import com.wei.limit.limiter.DTO.LimiterDTO;
import com.wei.limit.limiter.LimiterAbstract;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author: Xhy
 * CreateTime: 2023-03-09 16:01
 * 令牌桶限流
 * 按照一定的速率往桶中放token
 * 每次请求校验桶中是否有token
 */
public class TokenBucketLimiter extends LimiterAbstract {

    // 桶容量
    private Integer capacity = 5;

    // 每次放多少token
    private Integer rate = 1;

    // 间隔时间
    private Integer interval = 5;

    private ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    // 剩余的token
    private AtomicInteger surplus = new AtomicInteger(5);

    private static TokenBucketLimiter instance;

    // 单例模式,如果创建多个 TokenBucketLimiter 则会有多个定时器
    public static synchronized TokenBucketLimiter getInstance(){
        if (instance == null){
            instance = new TokenBucketLimiter();
        }
        return instance;
    }

    private TokenBucketLimiter(){
        init();
    }

    @Override
    public boolean check(LimiterDTO restrictDTO) {
        // 是否有容量
        if (surplus.get() <= 0){
            return true;
        }
        surplus.getAndDecrement();
        return false;
    }

    // 定时器,以一定的速率往桶中存放token
    private void init(){
        scheduledExecutorService.scheduleWithFixedDelay(()->{
            // 不能超过容量
            if (surplus.get() < capacity){
                surplus.getAndAdd(rate);
            }
        },0,interval, TimeUnit.SECONDS);
    }
}
