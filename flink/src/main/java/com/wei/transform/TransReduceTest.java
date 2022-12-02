/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2022. All Rights Reserved.
 */
package com.wei.transform;

import com.wei.pojo.Event;
import com.wei.source.ClickSource;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author huangxuwei
 * @date 2022年10月20日 21:45
 */
public class TransReduceTest {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();

        executionEnvironment.setParallelism(1);

//        executionEnvironment.addSource(new ClickSource())
//                .map((MapFunction<Event, Tuple2<String, Long>>) event -> Tuple2.of(event.user, 1L))
//                .returns(Types.TUPLE(Types.STRING, Types.LONG))
//                .keyBy(r -> r.f0)
//                .reduce((ReduceFunction<Tuple2<String, Long>>) (stringLongTuple2, t1) -> Tuple2.of(stringLongTuple2.f0, stringLongTuple2.f1 + t1.f1))
//                .returns(Types.TUPLE(Types.STRING, Types.LONG))
//                .keyBy(r -> true)
//                .reduce((ReduceFunction<Tuple2<String, Long>>) (stringLongTuple2, t1) -> stringLongTuple2.f1 > t1.f1 ? stringLongTuple2 : t1)
//                .returns(Types.TUPLE(Types.STRING, Types.LONG))
//                .print();

        executionEnvironment.addSource(new ClickSource())
                .map(new MapFunction<Event, Tuple2<String, Long>>() {
                    @Override
                    public Tuple2<String, Long> map(Event event) throws Exception {
                        return Tuple2.of(event.user, 1L);
                    }
                })
                .keyBy(r -> r.f0)
                .reduce(new ReduceFunction<Tuple2<String, Long>>() {
                    @Override
                    public Tuple2<String, Long> reduce(Tuple2<String, Long> stringLongTuple2, Tuple2<String, Long> t1) throws Exception {
                        return Tuple2.of(stringLongTuple2.f0, stringLongTuple2.f1 + t1.f1);
                    }
                })
                .keyBy(r -> true)
                .reduce(new ReduceFunction<Tuple2<String, Long>>() {
                    @Override
                    public Tuple2<String, Long> reduce(Tuple2<String, Long> stringLongTuple2, Tuple2<String, Long> t1) throws Exception {
                        return stringLongTuple2.f1 > t1.f1 ? stringLongTuple2 : t1;
                    }
                }).print();

        executionEnvironment.execute();
    }
}