package com.wei.map.impl;

import com.wei.map.Strategy;

public class Hotel implements Strategy {
    @Override
    public void issue(Object... params) {
        System.out.println("酒店策略");
    }
}
