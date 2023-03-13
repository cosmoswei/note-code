package com.wei.limit.controller;


import com.wei.limit.limiter.Limit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: Xhy
 * CreateTime: 2023-03-08 22:48
 */

@RestController
public class LimiterController {

    @GetMapping("/test1")
    @Limit(limit = 5,time = 5,msg = "计数器的")
    public String test1(){
        return "ok";
    }

    @GetMapping("/test2")
    @Limit()
    public String test2(){
        return "ok";
    }
}
