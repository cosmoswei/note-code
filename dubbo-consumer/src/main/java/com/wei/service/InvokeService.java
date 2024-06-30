package com.wei.service;

import me.xuqu.palmx.spring.PalmxClient;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

@Component
public class InvokeService {

    @DubboReference
    private DubboService dubboService;
    @PalmxClient
    private PalmxService palmxService;

    public String invokeDubbo() {
        return "invokeDubbo = " + dubboService.invokeDubbo(1L);
    }

    public String invokePalmx() {
        return "invokePalmx = " + palmxService.invokePalmx(1L);
    }
}
