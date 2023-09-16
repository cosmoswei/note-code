package com.wei;

import com.wei.limit.limiter.handler.LimiterHandler;
import com.wei.limit.limiter.impl.TokenBucketLimiter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LimiterAppRun {
    public static void main(String[] args) {
        // 具体的限流组件
        LimiterHandler.setLimiter(TokenBucketLimiter.getInstance());
        SpringApplication.run(LimiterAppRun.class, args);
    }
}
