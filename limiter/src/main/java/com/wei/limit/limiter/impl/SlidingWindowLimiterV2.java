package com.wei.limit.limiter.impl;

import com.wei.limit.DTO.MataData;
import com.wei.limit.constant.SimpleLimiterConstant;
import com.wei.limit.limiter.LimiterAbstract;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;


@Component(SimpleLimiterConstant.SLIDING_WINDOW_V2)
@Slf4j
public class SlidingWindowLimiterV2 extends LimiterAbstract {


    private int limit;
    private long interval;
    private AtomicInteger[] window;
    private int currentIndex = 0;
    private long lastUpdateTime = System.currentTimeMillis();

    public SlidingWindowLimiterV2() {
        init();
    }

    private void init() {
        this.limit = limit;
        this.interval = interval;
        this.window = new AtomicInteger[(int) (interval / 100)];
        for (int i = 0; i < window.length; i++) {
            window[i] = new AtomicInteger(0);
        }
    }

    // 判断是否允许新请求
    @Override
    public boolean check(MataData restrictDTO) {

        // 更新时间窗口
        updateWindow();

        // 计算窗口内的总请求数
        int totalRequests = 0;
        for (AtomicInteger count : window) {
            totalRequests += count.get();
        }

        log.info("totalRequests = {},limit = {}", totalRequests, limit);
        // 判断是否超过限制
        return totalRequests > limit;
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
