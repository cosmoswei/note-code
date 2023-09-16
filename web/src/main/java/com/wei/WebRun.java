package com.wei;

import com.wei.limit.limiter.handler.LimiterHandler;
import com.wei.limit.limiter.impl.CounterLimiter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebRun {

    public static void main(String[] args) {
        LimiterHandler.setLimiter(CounterLimiter.getInstance());
        SpringApplication.run(WebRun.class);
    }
}
