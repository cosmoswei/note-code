package com.wei.strategy.map.impl;

import com.wei.strategy.map.Strategy;

public class Food implements Strategy {


    @Override
    public void issue(Object... params) {
        System.out.println("美食策略" + params);
    }
}
