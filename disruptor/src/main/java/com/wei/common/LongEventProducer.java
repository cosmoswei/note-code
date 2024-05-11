package com.wei.common;

import com.lmax.disruptor.RingBuffer;
import com.wei.pojo.LongEvent;

import java.nio.ByteBuffer;

public class LongEventProducer {
    //环形缓冲区,装载生产好的数据；
    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    //将数据推入到缓冲区的方法：将数据装载到ringBuffer
    public void onData1(ByteBuffer bb) {
        long sequence = ringBuffer.next(); // Grab the next sequence //获取下一个可用的序列号
        try {
            LongEvent event = ringBuffer.get(sequence); // Get the entry in the Disruptor //通过序列号获取空闲可用的LongEvent
            // for the sequence
            event.set(bb.getLong(0)); // Fill with data //设置数值
        } finally {
            ringBuffer.publish(sequence); // 数据发布，只有发布后的数据才会真正被消费者看见
        }
    }
    public void onData2(ByteBuffer byteBuffer) {
        // 1.ringBuffer 事件队列 下一个槽
        long sequence = ringBuffer.next();
        Long data = null;
        try {
            //2.取出空的事件队列
            LongEvent longEvent = ringBuffer.get(sequence);
            data = byteBuffer.getLong(0);
            //3.获取事件队列传递的数据
            longEvent.setValue(data);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } finally {
            System.out.println("生产这准备发送数据");
            //4.发布事件
            ringBuffer.publish(sequence);
        }
    }
}