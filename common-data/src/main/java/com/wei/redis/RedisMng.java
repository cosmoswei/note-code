package com.wei.redis;

import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RedisMng {

    @Resource
    private RedissonClient redissonClient;

    public Object getKey(String key) {
        return redissonClient.getBucket(key).get();
    }
}
