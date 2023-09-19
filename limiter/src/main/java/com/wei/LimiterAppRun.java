package com.wei;

import com.wei.limit.limiter.handler.SimpleLimiterHandler;
import com.wei.limit.limiter.impl.TokenBucketLimiter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LimiterAppRun {
    public static void main(String[] args) {
        // 具体的限流组件
        SimpleLimiterHandler.setLimiter(TokenBucketLimiter.getInstance());
        SpringApplication.run(LimiterAppRun.class, args);
    }
}
