package com.wei;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/test")
public class EventController {
    @Resource
    private ApplicationContext applicationContext;
    @GetMapping("/publishEvent")
    public void publishEvent() {
        applicationContext.publishEvent(new RegisterSuccessEvent("歪歪"));
    }
}
