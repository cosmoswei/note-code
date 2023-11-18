package com.wei.limit.limiter.chat;

public interface RateLimiter {
    boolean isRateLimited();
    void update();
}



