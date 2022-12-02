/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2022. All Rights Reserved.
 */
package com.wei.sink;

import com.wei.pojo.Event;
import com.wei.source.ClickSource;
import org.apache.flink.api.common.eventtime.*;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;

import java.nio.charset.StandardCharsets;

/**
 * @author huangxuwei
 * @date 2022年10月21日 14:11
 */
public class SinkCustomHbase {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        DataStreamSource<Event> streamSource = env.addSource(new ClickSource());
        streamSource.assignTimestampsAndWatermarks(new WatermarkStrategy<Event>() {

            @Override
            public TimestampAssigner<Event> createTimestampAssigner(TimestampAssignerSupplier.Context context) {
                return WatermarkStrategy.super.createTimestampAssigner(context);
            }

            @Override
            public WatermarkGenerator<Event> createWatermarkGenerator(WatermarkGeneratorSupplier.Context context) {
                return null;
            }
        });

        env.getConfig().setAutoWatermarkInterval(60 * 1000L);

        env.fromElements("hello", "world").addSink(new RichSinkFunction<String>() {

            public org.apache.hadoop.conf.Configuration configuration;

            public Connection connection;

            @Override
            public void open(Configuration parameters) throws Exception {
                super.open(parameters);
                configuration = HBaseConfiguration.create();
                configuration.set("zookeeper cluster", "127.0.0.1:8080");
                connection = ConnectionFactory.createConnection(configuration);
            }

            @Override
            public void close() throws Exception {
                super.close();
                connection.close();
            }

            @Override
            public void invoke(String value, Context context) throws Exception {
                Table table = connection.getTable(TableName.valueOf("test"));
                Put put = new Put("rowKey".getBytes(StandardCharsets.UTF_8));
                put.addColumn("info".getBytes(StandardCharsets.UTF_8),
                        value.getBytes(StandardCharsets.UTF_8),
                        "1".getBytes(StandardCharsets.UTF_8
                        ));
                table.put(put);
                table.close();
            }
        });

        env.execute();
    }
}