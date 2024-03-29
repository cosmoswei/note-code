package com.wei.common;

import com.lmax.disruptor.EventFactory;
import com.wei.pojo.LongEvent;

//产生LongEvent的工厂类，它会在Disruptor系统初始化时，构造所有的缓冲区中的对象实例（预先分配空间）
public class LongEventFactory implements EventFactory<LongEvent> {
    public LongEvent newInstance() {
        return new LongEvent();
    }
}