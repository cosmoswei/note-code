/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2022. All Rights Reserved.
 */
package com.wei.watermarks;

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
import java.time.Duration;

/**
 * @author huangxuwei
 * @date 2022年10月21日 14:11
 */
public class WatermarkStrategyTest {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        DataStreamSource<Event> streamSource = env.addSource(new ClickSource());

//        streamSource.assignTimestampsAndWatermarks(new WatermarkStrategy<Event>() {
//
//            @Override
//            public TimestampAssigner<Event> createTimestampAssigner(TimestampAssignerSupplier.Context context) {
//                return WatermarkStrategy.super.createTimestampAssigner(context);
//            }
//
//            @Override
//            public WatermarkGenerator<Event> createWatermarkGenerator(WatermarkGeneratorSupplier.Context context) {
//                return null;
//            }
//        });
//        streamSource.assignTimestampsAndWatermarks(WatermarkStrategy.<Event>forMonotonousTimestamps()
//                .withTimestampAssigner((SerializableTimestampAssigner<Event>) (event, l) -> event.timestamp));

        streamSource.assignTimestampsAndWatermarks(WatermarkStrategy.<Event>forBoundedOutOfOrderness(Duration.ofSeconds(5))
                .withTimestampAssigner(new SerializableTimestampAssigner<Event>() {
                    @Override
                    public long extractTimestamp(Event event, long l) {
                        return event.timestamp;
                    }
                }));
        streamSource.assignTimestampsAndWatermarks(new CustomWatermarkStrategy()).print();
        env.execute();
    }

    private static class CustomWatermarkStrategy implements WatermarkStrategy<Event> {

        @Override
        public WatermarkGenerator<Event> createWatermarkGenerator(WatermarkGeneratorSupplier.Context context) {
            return new CustomPeriodicGenerator();
        }

        @Override
        public TimestampAssigner<Event> createTimestampAssigner(TimestampAssignerSupplier.Context context) {
            return new SerializableTimestampAssigner<Event>() {
                @Override
                public long extractTimestamp(Event event, long l) {
                    return event.timestamp;
                }
            };
        }
    }

    private static class CustomPeriodicGenerator implements WatermarkGenerator<Event> {


        private Long delayTime = 5000L;
        private Long maxTs = Long.MAX_VALUE + delayTime + 1L;

        @Override
        public void onEvent(Event event, long l, WatermarkOutput watermarkOutput) {
//            maxTs = Math.max(event.timestamp, maxTs);

            if ("Mary".equals(event.user)) {
                watermarkOutput.emitWatermark(new Watermark(event.timestamp - 1));
            }
        }

        @Override
        public void onPeriodicEmit(WatermarkOutput watermarkOutput) {
            watermarkOutput.emitWatermark(new Watermark(maxTs - delayTime - 1L));
        }
    }
}