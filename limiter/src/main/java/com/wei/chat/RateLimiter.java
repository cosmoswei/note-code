package com.wei.chat;

public interface RateLimiter {
    boolean isRateLimited();
    void update();
}



