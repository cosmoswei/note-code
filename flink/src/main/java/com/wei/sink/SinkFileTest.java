/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2022. All Rights Reserved.
 */
package com.wei.sink;

import com.wei.pojo.Event;
import org.apache.flink.api.common.serialization.SimpleStringEncoder;
import org.apache.flink.core.fs.Path;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.filesystem.StreamingFileSink;
import org.apache.flink.streaming.api.functions.sink.filesystem.rollingpolicies.DefaultRollingPolicy;

import java.util.concurrent.TimeUnit;

/**
 * @author huangxuwei
 * @date 2022年10月21日 10:44
 */
public class SinkFileTest {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(4);
        DataStreamSource<Event> streamSource = env.fromElements(
                new Event("A1", "/A1", 1000L),
                new Event("A2", "/A2", 2000L),
                new Event("A3", "/A3", 3000L),
                new Event("A4", "/A4", 4000L),
                new Event("A5", "/A5", 5000L),
                new Event("A6", "/A6", 6000L),
                new Event("A7", "/A7", 7000L)
        );

        StreamingFileSink<String> fileSink = StreamingFileSink.<String>forRowFormat(new Path("./output"), new SimpleStringEncoder<>("UTF-8"))
                .withRollingPolicy(DefaultRollingPolicy.builder()
                        .withRolloverInterval(TimeUnit.MINUTES.toMillis(15))
                        .withInactivityInterval(TimeUnit.MINUTES.toMillis(5))
                        .withMaxPartSize(1024 << 20).build())
                .build();

        streamSource.map(Event::toString).addSink(fileSink);

        env.execute();
    }
}