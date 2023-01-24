package com.wei.map.impl;

import com.wei.map.Strategy;

public class Food implements Strategy {


    @Override
    public void issue(Object... params) {
        System.out.println("美食策略" + params);
    }
}
