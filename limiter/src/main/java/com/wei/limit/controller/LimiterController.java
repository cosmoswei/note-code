package com.wei.limit.controller;


import com.wei.limit.aop.SimpleLimiter;
import com.wei.limit.constant.SimpleLimiterConstant;
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
    @SimpleLimiter(limit = 100, time = 1, type = SimpleLimiterConstant.COUNTER, callback = "callback")
    public String test1() {
        cnt++;
        System.out.println("ok11: " + cnt);
        return "ok11";
    }

    @GetMapping("/test12")
    @SimpleLimiter(limit = 100, time = 1, type = SimpleLimiterConstant.SLIDING_WINDOW, callback = "callback")
    public String test2() {
        cnt++;
        System.out.println(cnt);
        return "ok2";
    }

    @GetMapping("/test21")
    public String test3() {
        return "ok2";
    }

    public String callback() {
        return "触发流控";
    }
}
