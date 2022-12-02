/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2022. All Rights Reserved.
 */
package com.wei.transform;

import com.wei.pojo.Event;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author huangxuwei
 * @date 2022年10月20日 21:27
 */
public class TransKeyByTest {
    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.setParallelism(1);

        DataStreamSource<Event> streamSource = env.fromElements(
                new Event("A", "/1", 1L),
                new Event("A", "/1", 2L),
                new Event("A", "/1", 3L),
                new Event("B", "/1", 3L),
                new Event("C", "/1", 3L));

        KeyedStream<Event, String> eventStringKeyedStream = streamSource.keyBy(Event::getUser);

        eventStringKeyedStream.sum("timestamp").print();

        env.execute();

    }
}