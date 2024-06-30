package com.wei.service;

@me.xuqu.palmx.spring.PalmxService
public class PalmxServiceImpl implements com.wei.service.PalmxService {

    @Override
    public String invokePalmx(long l) {
//        try {
//            Thread.sleep(l);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        return "success";
    }
}
