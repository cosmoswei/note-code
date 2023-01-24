package com.wei.map.impl;

import com.wei.map.Strategy;

import java.util.Date;

public class Waimai implements Strategy {
    @Override
    public void issue(Object... params) {
        Date date = new Date();
        System.out.println("waimai策略");
    }
}
