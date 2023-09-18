package com.wei.controller;

import com.wei.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class LimitTestController {

    @Resource
    private TestService testService;

    @RequestMapping("/test")
    public String test() throws InterruptedException {
        return testService.test();
    }

    @RequestMapping("/test2")
    public String test2() throws InterruptedException {
        return testService.test2();
    }
}
