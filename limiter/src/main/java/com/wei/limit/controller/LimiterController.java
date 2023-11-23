package com.wei.limit.controller;


import com.wei.limit.aop.SimpleLimiter;
import com.wei.limit.constant.SimpleLimiterConstant;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;


@RestController
public class LimiterController {

    static AtomicInteger cnt = new AtomicInteger();

    @GetMapping("/test0")
    public String test0() {
        return "ok,cnt = " + cnt.incrementAndGet();
    }

    @GetMapping("/test1")
    @SimpleLimiter(limit = 500, interval = 1000, type = SimpleLimiterConstant.COUNTER, callback = "callback")
    public String test1() {
        return "ok,cnt = " + cnt.incrementAndGet();
    }

    @GetMapping("/test2")
    @SimpleLimiter(limit = 500, interval = 1000, type = SimpleLimiterConstant.SLIDING_WINDOW_V1, callback = "callback")
    public String test2() {
        return "ok,cnt = " + cnt.incrementAndGet();
    }

    @GetMapping("/test3")
    @SimpleLimiter(limit = 500, interval = 1000, type = SimpleLimiterConstant.SLIDING_WINDOW_V2, callback = "callback")
    public String test3() {
        return "ok,cnt = " + cnt.incrementAndGet();
    }

    @GetMapping("/test4")
    public String test4() {
        return "ok,cnt = " + cnt.incrementAndGet();
    }

    @GetMapping("/test5")
    @RateLimiter(name = "myRateLimiter", fallbackMethod = "fallback")
    public String test5() {
        return "ok,cnt = " + cnt.incrementAndGet();
    }

    public String fallback(Throwable t) {
        // 限流时的降级处理逻辑
        System.out.println("Rate limit exceeded, fallback method called.");
        return "触发流控";
    }

    public String callback() {
        return "触发流控";
    }

//    public String fallback() {
//        return "触发流控";
//    }
}
