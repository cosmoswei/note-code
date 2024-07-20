package com.wei.chat;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TokenBucket {
    private final int capacity; // 桶的容量
    private final int refillRate; // 令牌补充速率
    private int tokens; // 当前桶中的令牌数量
    private long lastRefillTime; // 上次补充令牌的时间

    public TokenBucket(int capacity, int refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.tokens = capacity; // 初始化时桶是满的
        this.lastRefillTime = System.currentTimeMillis();
    }

    // 尝试获取一个令牌，如果成功返回 true，否则返回 false
    public synchronized boolean getToken() {
        refill();

        if (tokens > 0) {
            tokens--;
            return true;
        } else {
            return false;
        }
    }

    // 模拟补充令牌过程
    private void refill() {
        long currentTime = System.currentTimeMillis();
        long timePassed = currentTime - lastRefillTime;

        int newTokens = (int)(timePassed / 1000 * refillRate); // 新补充的令牌数
        if (newTokens > 0) {
            tokens = Math.min(capacity, tokens + newTokens);
            lastRefillTime = currentTime;
        }
    }

    public static void main(String[] args) {
        TokenBucket bucket = new TokenBucket(10, 1000); // 创建一个容量为 10，令牌补充速率为每秒 1 个的桶

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            boolean accepted = bucket.getToken();
            System.out.println("Request " + (accepted ? "accepted" : "rejected") + ". Current token count: " + bucket.tokens);
        }, 0, 500, TimeUnit.MILLISECONDS);


    }
}
