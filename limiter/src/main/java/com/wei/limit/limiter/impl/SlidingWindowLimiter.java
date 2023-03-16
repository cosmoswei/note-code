package com.wei.limit.limiter.impl;

import com.wei.limit.limiter.DTO.LimiterDTO;
import com.wei.limit.limiter.LimiterAbstract;
import com.wei.limit.limiter.constant.RedisConstant;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

public class SlidingWindowLimiter extends LimiterAbstract {


    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public boolean check(LimiterDTO restrictDTO) {
        return false;
    }

    public boolean canAccess(String key, int windowInSecond, long maxCount) {
        key = RedisConstant.SLIDING_WINDOW + key;
        Long count = redisTemplate.opsForZSet().zCard(key);
        if (count < maxCount) {
            increment(key, windowInSecond);
            return true;
        } else {
            return false;
        }
    }

    public void increment(String key, Integer windowInSecond) {
        long currentMs = System.currentTimeMillis();
        long windowStartMs = currentMs - windowInSecond * 1000;
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        zSetOperations.removeRangeByScore(key, 0, windowStartMs);
        zSetOperations.add(key, String.valueOf(currentMs), currentMs);
        redisTemplate.expire(key, windowInSecond, TimeUnit.SECONDS);
    }
}
