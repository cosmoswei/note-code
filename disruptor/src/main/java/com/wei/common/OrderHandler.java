package com.wei.common;

import com.lmax.disruptor.EventHandler;
import com.wei.pojo.Order;

public class OrderHandler implements EventHandler<Order> {
    @Override
    public void onEvent(Order order, long l, boolean b) throws Exception {
        System.out.println(Thread.currentThread().getName() + " 消费者处理中:" + l);
        order.setInfo("info" + order.getId());
        order.setPrice(Math.random());    }}