package com.wei.strategy.map.impl;

import com.wei.strategy.map.Strategy;

import java.util.Date;

public class Waimai implements Strategy {
    @Override
    public void issue(Object... params) {
        Date date = new Date();
        System.out.println("waimai策略");
    }
}
