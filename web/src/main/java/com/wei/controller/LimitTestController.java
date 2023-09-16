package com.wei.controller;

import com.google.gson.Gson;
import com.wei.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class LimitTestController {

    @Resource
    private TestService testService;

    @RequestMapping("/test")
    public String test() {
        Gson gson = new Gson();
        return gson.toJson(testService.test());
    }
}
