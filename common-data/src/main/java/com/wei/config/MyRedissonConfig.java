package com.wei.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

//@Configuration
public class MyRedissonConfig {

    //注册RedissonClient对象
//    @Bean(destroyMethod = "shutdown")
    RedissonClient redisson() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://43.138.81.164:6379")
                .setPassword("huangxuwei")
                .setDatabase(0);
        return Redisson.create(config);
    }
}
