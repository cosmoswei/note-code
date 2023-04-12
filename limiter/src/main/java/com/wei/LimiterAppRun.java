package com.wei;

import com.wei.limit.limiter.handler.LimiterHandler;
import com.wei.limit.limiter.impl.CounterLimiter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LimiterAppRun {
    public static void main(String[] args) {
        // 具体的限流组件
        LimiterHandler.setLimiter(CounterLimiter.getInstance());
        SpringApplication.run(LimiterAppRun.class, args);
    }
}
