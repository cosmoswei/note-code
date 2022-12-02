/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2022. All Rights Reserved.
 */
package com.wei.sink;

import com.wei.pojo.Event;
import org.apache.flink.connector.jdbc.JdbcConnectionOptions;
import org.apache.flink.connector.jdbc.JdbcExecutionOptions;
import org.apache.flink.connector.jdbc.JdbcSink;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author huangxuwei
 * @date 2022年10月21日 13:54
 */
public class SinkToJDBCTest {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.setParallelism(1);

        DataStreamSource<Event> streamSource = env.fromElements(
                new Event("A1", "/A1", 1000L),
                new Event("A2", "/A2", 2000L),
                new Event("A3", "/A3", 3000L),
                new Event("A4", "/A4", 4000L),
                new Event("A5", "/A5", 5000L),
                new Event("A6", "/A6", 6000L),
                new Event("A7", "/A7", 7000L)
        );
        streamSource.addSink(JdbcSink.sink("insert into clicks (user,url) values (?,?)", (statement, r) -> {
            statement.setString(1, r.user);
            statement.setString(2, r.url);
        }, JdbcExecutionOptions.builder()
                .withBatchSize(1000).withBatchIntervalMs(200).withMaxRetries(5).build(), new JdbcConnectionOptions.JdbcConnectionOptionsBuilder()
                .withUrl("jdbc:mysql://huangxuwei.com:3306/huangxuwei")
                .withDriverName("com.mysql.jdbc.Driver")
                .withUsername("huangxuwei")
                .withPassword("HKLC6c4zY7xMheNd")
                .build()));

        env.execute();
    }
}