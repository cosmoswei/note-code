package com.wei.limit.limiter.impl;

import com.wei.limit.DTO.MataData;
import com.wei.limit.constant.SimpleLimiterConstant;
import com.wei.limit.limiter.LimiterAbstract;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


@Component(SimpleLimiterConstant.SLIDING_WINDOW)
public class SlidingWindowLimiter extends LimiterAbstract {

    private BlockingQueue<Long> requestQueue;

    /**
     * 每个Key的指标
     */
    private final Map<String, BlockingQueue<Long>> keyQuota = new HashMap<>();

    public SlidingWindowLimiter() {
//        this.requestQueue = new ArrayBlockingQueue<>(20);
    }

    // 判断是否允许新请求
    @Override
    public boolean check(MataData restrictDTO) {

        // 此处key应该为请求路径+userId。此处是为了测试方便
        String key = restrictDTO.key;
        int limit = restrictDTO.limit;
        int time = restrictDTO.time;
        if (!keyQuota.containsKey(key)) {
            set(key, 1, time);
            return false;
        }

        if (Objects.isNull(requestQueue)) {
            requestQueue = new ArrayBlockingQueue<>(restrictDTO.limit);
        }
        return !allowRequest(restrictDTO.time, restrictDTO.limit);
    }

    @Override
    public void set(String key, Integer value, long time) {
//        keyQuota.put(key, new CounterLimiter.Data(value, time));
//        delayQueue.add(new CounterLimiter.DataDelay(key, time));
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
