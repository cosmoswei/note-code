package com.wei.limit.limiter.chat;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;

@Slf4j
public class SlidingWindowRateLimiterV2 implements RateLimiter {

    private final int limit;
    private final long interval;
    private final Queue<Long> requestTimestamps = new LinkedList<>();

    public SlidingWindowRateLimiterV2(int limit, long interval) {
        this.limit = limit;
        this.interval = interval;
    }

    @Override
    public boolean isRateLimited() {
        cleanExpiredRequests();
        return requestTimestamps.size() > limit;
    }

    @Override
    public void update() {
        cleanExpiredRequests();
        requestTimestamps.offer(System.currentTimeMillis());
    }

    private void cleanExpiredRequests() {
        long currentTime = System.currentTimeMillis();
        while (!requestTimestamps.isEmpty() && currentTime - requestTimestamps.peek() > interval) {
            requestTimestamps.poll();
        }
    }
}
