package com.wei.limit.limiter.impl;

import com.wei.limit.DTO.LimiterMataData;
import com.wei.limit.constant.SimpleLimiterConstant;
import com.wei.limit.limiter.LimiterAbstract;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


@Deprecated
@Component(SimpleLimiterConstant.SLIDING_WINDOW_V3)
public class SlidingWindowLimiterV3 extends LimiterAbstract {

    private BlockingQueue<Long> requestQueue;

    /**
     * 每个Key的指标
     */
    private final Map<String, BlockingQueue<Long>> keyQuota = new HashMap<>();

    public SlidingWindowLimiterV3() {
    }

    // 判断是否允许新请求
    @Override
    public boolean limit(LimiterMataData limiterMataData) {

        // 此处key应该为请求路径+userId。此处是为了测试方便
        String key = limiterMataData.key;
        int time = limiterMataData.interval;
        if (!keyQuota.containsKey(key)) {
            set(key, 1, time);
            return false;
        }

        if (Objects.isNull(requestQueue)) {
            requestQueue = new ArrayBlockingQueue<>(limiterMataData.limit);
        }
        return !allowRequest(limiterMataData.interval, limiterMataData.limit);
    }

    @Override
    public void set(String key, Integer value, long interval) {

    }

    public synchronized boolean allowRequest(int windowSize, int maxRequests) {
        long currentTimestamp = System.currentTimeMillis();
        // 移除超过滑动窗口大小的时间戳
        while (!requestQueue.isEmpty() && currentTimestamp - requestQueue.peek() >= windowSize * 1000L) {
            requestQueue.poll();
        }

        // 检查请求数是否超过最大请求数
        if (requestQueue.size() < maxRequests) {
            return requestQueue.offer(currentTimestamp);
        } else {
            return false; // 限流
        }
    }
}
