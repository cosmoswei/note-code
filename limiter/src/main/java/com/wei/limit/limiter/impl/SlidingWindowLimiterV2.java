package com.wei.limit.limiter.impl;

import com.wei.limit.DTO.MataData;
import com.wei.limit.DTO.Quota;
import com.wei.limit.constant.SimpleLimiterConstant;
import com.wei.limit.limiter.LimiterAbstract;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;


@Component(SimpleLimiterConstant.SLIDING_WINDOW_V2)
@Slf4j
public class SlidingWindowLimiterV2 extends LimiterAbstract {


    /**
     * key 与统计指标
     */
    private final Map<String, Quota> map = new HashMap<>();

    // 判断是否允许新请求
    @Override
    public boolean limit(MataData mataData) {
        // 此处 key 为请求路径
        String key = mataData.key;
        int limit = mataData.limit;
        int time = mataData.interval;
        if (!map.containsKey(key)) {
            init(key, time);
            return false;
        }
        Quota quota = map.get(key);
        cleanExpiredRequests(quota);
        boolean res = quota.getRequestTimestamps().size() > limit;
        // 被限流的请求不加指标
        if (!res) {
            incr(key, time);
        }
        return res;
    }

    private void cleanExpiredRequests(Quota quota) {
        long currentTime = System.currentTimeMillis();
        Queue<Long> requestTimestamps = quota.getRequestTimestamps();
        int limit = quota.getInterval();
        while (!requestTimestamps.isEmpty() && currentTime - requestTimestamps.peek() > limit) {
            requestTimestamps.poll();
        }
    }

    @Override
    public void incr(String key, int time) {
        if (map.containsKey(key)) {
            Quota quota = map.get(key);
            update(quota);
        } else {
            init(key, time);
        }
    }

    public void update(Quota quota) {
        cleanExpiredRequests(quota);
        quota.getRequestTimestamps().offer(System.currentTimeMillis());
    }


    public void init(String key, int time) {
        Quota quota = new Quota(key, time);
        quota.getRequestTimestamps().offer(System.currentTimeMillis());
        map.put(key, quota);
    }
}
