package com.wei.limit.limiter.impl;

import com.wei.limit.DTO.MataData;
import com.wei.limit.constant.FlowControlConstant;
import com.wei.limit.limiter.LimiterAbstract;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


@Component(FlowControlConstant.SLIDING_WINDOW)
public class SlidingWindowLimiter extends LimiterAbstract {

    private final BlockingQueue<Long> requestQueue;

    public SlidingWindowLimiter() {
        this.requestQueue = new ArrayBlockingQueue<>(20);
    }

    // 判断是否允许新请求
    @Override
    public boolean check(MataData restrictDTO) {
        return !allowRequest(restrictDTO.time, restrictDTO.limit);
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
