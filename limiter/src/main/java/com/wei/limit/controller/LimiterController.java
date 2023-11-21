package com.wei.limit.controller;


import com.wei.limit.aop.SimpleLimiter;
import com.wei.limit.constant.SimpleLimiterConstant;
import com.wei.limit.limiter.chat.RateLimit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: Xhy
 * CreateTime: 2023-03-08 22:48
 */

@RestController
public class LimiterController {

    static int cnt = 0;

    @GetMapping("/test11")
    @SimpleLimiter(limit = 500, interval = 1000, type = SimpleLimiterConstant.SLIDING_WINDOW_V3, callback = "callback")
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

    public String callback() {
        return "触发流控";
    }
}
