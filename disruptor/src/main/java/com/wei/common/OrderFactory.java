package com.wei.common;

import com.lmax.disruptor.EventFactory;
import com.wei.pojo.Order;

public class OrderFactory implements EventFactory {
    @Override
    public Object newInstance() {
        System.out.println("OrderFactory.newInstance");
        return new Order();
    }}