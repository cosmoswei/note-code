package com.wei.limit.controller;


import com.wei.limit.aop.SimpleLimiter;
import com.wei.limit.constant.SimpleLimiterConstant;
import com.wei.chat.RateLimit;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LimiterController {

    static int cnt = 0;

    @GetMapping("/test11")
    @SimpleLimiter(limit = 500, interval = 1000, type = SimpleLimiterConstant.SLIDING_WINDOW_V2, callback = "callback")
    public String test1() {
        cnt++;
        return "ok,cnt = " + cnt;
    }

    @GetMapping("/test12")
    @SimpleLimiter(limit = 1, interval = 1000, type = SimpleLimiterConstant.SLIDING_WINDOW_V2, callback = "callback")
    public String test2() {
        cnt++;
        return "ok,cnt = " + cnt;
    }

    @GetMapping("/test21")
    public String test3() {
        return "ok2";
    }

    @GetMapping("/test31")
    @RateLimit(limit = 200, interval = 1000, type = "slidingWindow")
    public String test31() {
        cnt++;
        return "ok,cnt = " + cnt;
    }

    @GetMapping("/test41")
    @RateLimiter(name = "myRateLimiter")
    public String test41() {
        cnt++;
        // 执行业务逻辑
        System.out.println("Performing business logic with rate limiting...");
        return "ok,cnt = " + cnt;
    }

    public void fallback(Throwable t) {
        // 限流时的降级处理逻辑
        System.out.println("Rate limit exceeded, fallback method called.");
    }

    public String callback() {
        return "触发流控";
    }

    public String fallback() {
        return "触发流控";
    }
}
