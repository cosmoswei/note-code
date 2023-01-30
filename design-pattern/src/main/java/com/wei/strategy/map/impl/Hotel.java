package com.wei.strategy.map.impl;

import com.wei.strategy.map.Strategy;

public class Hotel implements Strategy {
    @Override
    public void issue(Object... params) {
        System.out.println("酒店策略");
    }
}
