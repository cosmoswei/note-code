/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2022. All Rights Reserved.
 */
package com.wei.transform;

import com.wei.pojo.Event;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.runtime.jobmaster.ExecutionDeploymentState;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * @author huangxuwei
 * @date 2022年10月20日 20:58
 */
public class TransMapTest {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
        executionEnvironment.setParallelism(1);
        DataStreamSource<Event> eventDataStreamSource = executionEnvironment.fromElements(
                new Event("A", "/1", 1L),
                new Event("A", "/1", 2L),
                new Event("A", "/1", 3L),
                new Event("B", "/1", 3L),
                new Event("C", "/1", 3L));

//        eventDataStreamSource.map(new MapFunction<Event, Object>() {
//            @Override
//            public Object map(Event event) throws Exception {
//                return event.getUser();
//            }
//        });
//
//        eventDataStreamSource.map((MapFunction<Event, Object>) event -> event.getUser());
//
//        eventDataStreamSource.map((MapFunction<Event, Object>) Event::getUser);
//
//        eventDataStreamSource.map(new UserExtractor()).print();
//
//        eventDataStreamSource.print();
//
//        eventDataStreamSource.filter((FilterFunction<Event>) event -> event.getUser().equals("A"));
//
//        eventDataStreamSource.filter(new UserFilter()).print();

        eventDataStreamSource.flatMap(new UserFlatMap()).print();

//        eventDataStreamSource.flatMap((FlatMapFunction<Event, String>) (event, collector) -> {
//            if (event.user.equals("A")) {
//                collector.collect(event.user);
//            } else if (event.user.equals("B")) {
//                collector.collect(event.user);
//                collector.collect(event.url);
//            }
//        }).print();

        executionEnvironment.execute();
    }

    public static class UserExtractor implements MapFunction<Event, String> {

        @Override
        public String map(Event event) throws Exception {
            return event.getUser();
        }
    }

    public static class UserFilter implements FilterFunction<Event> {

        @Override
        public boolean filter(Event event) throws Exception {
            return event.user.equals("A");
        }
    }

    public static class UserFlatMap implements FlatMapFunction<Event, String> {
        @Override
        public void flatMap(Event event, Collector collector) throws Exception {
            if (event.user.equals("A")) {
                collector.collect(event.user);
            } else if (event.user.equals("B")) {
                collector.collect(event.user);
                collector.collect(event.url);
            }
        }
    }
}