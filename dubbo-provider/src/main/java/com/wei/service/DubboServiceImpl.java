package com.wei.service;

import org.apache.dubbo.config.annotation.DubboService;


@DubboService
public class DubboServiceImpl implements com.wei.service.DubboService {

    @Override
    public String invokeDubbo(long l) {
//        try {
//            Thread.sleep(l);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        return "success";
    }
}
