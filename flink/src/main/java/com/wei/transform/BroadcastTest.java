/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2022. All Rights Reserved.
 */
package com.wei.transform;

import com.wei.pojo.Event;
import com.wei.source.ClickSource;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author huangxuwei
 * @date 2022年10月21日 10:15
 */
public class BroadcastTest {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();

        executionEnvironment.setParallelism(1);

        DataStreamSource<Event> streamSource = executionEnvironment.addSource(new ClickSource());

        streamSource.broadcast().print("Broadcast").setParallelism(4);

        executionEnvironment.execute();
    }
}