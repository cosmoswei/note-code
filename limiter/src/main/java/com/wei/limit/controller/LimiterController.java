package com.wei.limit.controller;


import com.wei.limit.aop.SimpleLimiter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: Xhy
 * CreateTime: 2023-03-08 22:48
 */

@RestController
public class LimiterController {

    @GetMapping("/test12")
    @SimpleLimiter(limit = 5, time = 5, callback = "test2")
    public String test1() {
        return "ok";
    }

    @GetMapping("/test22")
    @SimpleLimiter()
    public String test2() {
        return "ok2";
    }

    public String callback() {
        return "触发流控";
    }
}
