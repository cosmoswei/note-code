package com.wei.chat;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LeakyBucket {
    private final int capacity; // 桶的容量
    private final int leakRate; // 漏水速率
    private int water; // 当前桶中的水量（当前请求数）
    private long lastLeakTime; // 上次漏水的时间

    public LeakyBucket(int capacity, int leakRate) {
        this.capacity = capacity;
        this.leakRate = leakRate;
        this.water = 0;
        this.lastLeakTime = System.currentTimeMillis();
    }

    // 尝试添加一个请求，如果成功返回 true，否则返回 false
    public synchronized boolean addRequest() {
        leak();

        if (water < capacity) {
            water++;
            return true;
        } else {
            return false;
        }
    }

    // 模拟漏水过程
    private void leak() {
        long currentTime = System.currentTimeMillis();
        long timePassed = currentTime - lastLeakTime;

        int leaked = (int)(timePassed / 1000 * leakRate); // 漏掉的水量
        if (leaked > 0) {
            water = Math.max(0, water - leaked);
            lastLeakTime = currentTime;
        }
    }

    public static void main(String[] args) {
        LeakyBucket bucket = new LeakyBucket(10, 10); // 创建一个容量为 10，漏水速率为 1 的桶

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(20);
        scheduler.scheduleAtFixedRate(() -> {
            boolean accepted = bucket.addRequest();
            System.out.println("Request " + (accepted ? "accepted" : "rejected") + ". Current water level: " + bucket.water);
        }, 0, 50, TimeUnit.MILLISECONDS);
    }
}
