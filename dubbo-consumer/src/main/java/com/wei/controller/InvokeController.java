package com.wei.controller;


import com.wei.service.InvokeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class InvokeController {

    @Resource
    private InvokeService invokeService;

    int empty = 0;
    int dubbo = 0;
    int palmx = 0;

    @RequestMapping("/empty")
    public String empty() {
        return "empty = " + empty++;
    }

    @RequestMapping("/dubbo")
    public String dubbo() {
        return invokeService.invokeDubbo() + dubbo++;
    }

    @RequestMapping("/palmx")
    public String palmx() {
        return invokeService.invokePalmx() + palmx++;
    }
}
