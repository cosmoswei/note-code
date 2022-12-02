/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2022. All Rights Reserved.
 */
package com.wei.source;

import com.wei.pojo.Event;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huangxuwei
 * @date 2022年10月20日 16:18
 */
public class DataStreamDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        environment.setParallelism(1);
        List<Event> clicks = new ArrayList<>();
        clicks.add(new Event("huang", "/home", 1000L));
        clicks.add(new Event("xu", "/xu", 2000L));
        clicks.add(new Event("wei", "/wei", 3000L));
        DataStream<Event> eventDataStream1 = environment.fromCollection(clicks);

        DataStream<Event> eventDataStream2 = environment.fromElements(
                new Event("h", "/x", 1L),
                new Event("h", "/x", 1L)
        );
        eventDataStream1.print();
        eventDataStream2.print();
        environment.execute();
    }
}