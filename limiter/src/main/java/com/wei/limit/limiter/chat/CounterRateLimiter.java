package com.wei.limit.limiter.chat;

public class CounterRateLimiter implements RateLimiter {

    int limit;

    long interval;

    public CounterRateLimiter(int limit, long interval) {

    }

    @Override
    public boolean isRateLimited() {
        return false;
    }

    @Override
    public void update() {

    }
    // 实现计数器算法的具体逻辑
}