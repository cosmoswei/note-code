/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2022. All Rights Reserved.
 */
package com.wei.sink;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;

import java.util.Properties;

/**
 * @author huangxuwei
 * @date 2022年10月21日 11:08
 */
public class SinkToKafkaTest {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();

        executionEnvironment.setParallelism(1);

        Properties properties = new Properties();

        properties.put("bootstrap.server", "127.0.0.1:9092");

        DataStreamSource<String> streamSource = executionEnvironment.readTextFile("input/clicks.csv");

        streamSource.addSink(new FlinkKafkaProducer<>(
                "clicks",
                new SimpleStringSchema(),
                properties));

        executionEnvironment.execute();
    }
}