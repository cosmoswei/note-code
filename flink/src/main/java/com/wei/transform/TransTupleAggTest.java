/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2022. All Rights Reserved.
 */
package com.wei.transform;

import com.wei.pojo.Event;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author huangxuwei
 * @date 2022年10月20日 21:33
 */
public class TransTupleAggTest {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.setParallelism(1);

        DataStreamSource<Tuple2<String, Integer>> streamSource = env.fromElements(
                Tuple2.of("A1", 1),
                Tuple2.of("A2", 2),
                Tuple2.of("A3", 3),
                Tuple2.of("A3", 5),
                Tuple2.of("A4", 4)
        );

//        streamSource.keyBy(r -> r.f0).sum(1).print();

//        streamSource.keyBy(r -> r.f0).sum("f1").print();

//        streamSource.keyBy(r -> r.f0).max(1).print();

        streamSource.keyBy(r -> r.f0).maxBy(1).print();


        env.execute();
    }
}