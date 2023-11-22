package com.wei.limit.limiter.impl;

import com.wei.limit.DTO.LimiterMataData;
import com.wei.limit.constant.SimpleLimiterConstant;
import com.wei.limit.limiter.LimiterAbstract;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


@Component(SimpleLimiterConstant.SLIDING_WINDOW_V2)
@Slf4j
public class SlidingWindowLimiterV2 extends LimiterAbstract {

    /**
     * key 与统计指标
     */
    private final Map<String, Quota> map = new HashMap<>();

    // 判断是否允许新请求
    @Override
    public boolean limit(LimiterMataData limiterMataData) {
        // 此处 key 为请求路径
        String key = limiterMataData.key;
        int limit = limiterMataData.limit;
        int interval = limiterMataData.interval;
        if (!map.containsKey(key)) {
            init(key, interval);
            return false;
        }
        Quota quota = map.get(key);
        boolean res = isRateLimited(quota, limit);
//        update(quota);
        // 被限流的请求不加指标
        if (!res) {
            incr(key, interval);
        } else {
            // 清除过期窗口
            long currentTime = System.currentTimeMillis();
            extracted(quota, currentTime);
        }
        return res;
    }

    public boolean isRateLimited(Quota quota, int limit) {
        // 更新时间窗口
        AtomicInteger[] window = quota.getWindow();
        // 计算窗口内的总请求数
        int totalRequests = 0;
        for (AtomicInteger count : window) {
            totalRequests += count.get();
        }
        // 判断是否超过限制
        return totalRequests > limit;
    }


    private void update(Quota quota) {
        updateWindow(quota);
        AtomicInteger[] window = quota.getWindow();
        int currentIndex = quota.getCurrentIndex();
        window[currentIndex].incrementAndGet();
    }

    @Override
    public void incr(String key, int interval) {
        if (map.containsKey(key)) {
            Quota quota = map.get(key);
            update(quota);
        } else {
            init(key, interval);
        }
    }

    public void init(String key, int interval) {
        Quota quota = new Quota();
        quota.setKey(key);
        quota.setInterval(interval);
        AtomicInteger[] atomicIntegers = new AtomicInteger[(int) (interval / 100)];
        for (int i = 0; i < atomicIntegers.length; i++) {
            atomicIntegers[i] = new AtomicInteger(0);
        }
        quota.setWindow(atomicIntegers);
        map.put(key, quota);
    }

    private void updateWindow(Quota quota) {
        long lastUpdateTime = quota.getLastUpdateTime();
        int currentIndex = quota.getCurrentIndex();
        AtomicInteger[] window = quota.getWindow();
        long currentTime = System.currentTimeMillis();
        int timePassed = (int) ((currentTime - lastUpdateTime) / 100);
        log.info("currentTime = {},lastUpdateTime = {},timePassed= {}",
                currentTime,
                lastUpdateTime,
                timePassed);
        // 清零过期的窗口
        extracted(quota, currentTime);
        // 更新当前索引和最后更新时间，如果一直有请求，窗口索引一直不会增加，只过期窗口，不加指标行不行？
        currentIndex = (currentIndex + timePassed) % window.length;
        log.info("currentIndex = {},window.length = {},timePassed= {}",
                currentIndex,
                window.length,
                timePassed);
        lastUpdateTime = currentTime;
        quota.setCurrentIndex(currentIndex);
        quota.setLastUpdateTime(lastUpdateTime);
    }

    private static void extracted(Quota quota, long currentTime) {
        int timePassed = (int) ((currentTime - quota.getLastUpdateTime()) / 100);
        AtomicInteger[] window = quota.getWindow();
        int currentIndex = quota.getCurrentIndex();
        for (int i = 0; i < timePassed; i++) {
            window[(currentIndex + i) % window.length].set(0);
        }
    }

    @Data
    class Quota {
        /**
         * key
         */
        private String key;
        /**
         * 时间间隔
         */
        private int interval;
        /**
         * 窗口
         */
        private AtomicInteger[] window;
        /**
         * 当前窗口索引
         */
        private int currentIndex = 0;
        /**
         * 最近的更新时间
         */
        private long lastUpdateTime = System.currentTimeMillis();

        public Quota() {
        }
    }
}
