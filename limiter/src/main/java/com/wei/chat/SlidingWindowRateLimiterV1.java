package com.wei.chat;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class SlidingWindowRateLimiterV1 implements RateLimiter {

    private final int limit;
    private final long interval;
    private final AtomicInteger[] window;
    private int currentIndex = 0;
    private long lastUpdateTime = System.currentTimeMillis();

    public SlidingWindowRateLimiterV1(int limit, long interval) {
        this.limit = limit;
        this.interval = interval;
        this.window = new AtomicInteger[(int) (interval / 100)];
        for (int i = 0; i < window.length; i++) {
            window[i] = new AtomicInteger(0);
        }
    }

    @Override
    public boolean isRateLimited() {
        // 更新时间窗口
        updateWindow();
        // 计算窗口内的总请求数
        int totalRequests = 0;
        for (AtomicInteger count : window) {
            totalRequests += count.get();
        }
        // 判断是否超过限制
        return totalRequests > limit;
    }

    @Override
    public void update() {
        // 更新时间窗口
        updateWindow();
        // 增加当前窗口的计数
        window[currentIndex].incrementAndGet();
    }

    private void updateWindow() {
        long currentTime = System.currentTimeMillis();
        int timePassed = (int) ((currentTime - lastUpdateTime) / 100);
        // 清零过期的窗口
        for (int i = 0; i < timePassed; i++) {
            window[(currentIndex + i) % window.length].set(0);
        }
        // 更新当前索引和最后更新时间
        currentIndex = (currentIndex + timePassed) % window.length;
        lastUpdateTime = currentTime;
    }
}
