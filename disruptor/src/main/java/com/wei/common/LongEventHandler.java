package com.wei.common;

import com.lmax.disruptor.EventHandler;
import com.wei.pojo.LongEvent;

//消费者实现为WorkHandler接口，是Disruptor框架中的类
public class LongEventHandler implements EventHandler<LongEvent> {
    //onEvent()方法是框架的回调用法
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) {
        System.out.println("消费者 Event: " + event.toString());
    }
}