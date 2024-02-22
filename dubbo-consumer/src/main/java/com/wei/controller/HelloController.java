package com.wei.controller;


import com.wei.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HelloController {


    @Resource
    private UserService userService;

    @RequestMapping("/test")
    public String test() {
        return userService.byTicket();
    }
}
