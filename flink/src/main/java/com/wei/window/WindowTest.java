/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2022. All Rights Reserved.
 */
package com.wei.window;

import com.wei.pojo.Event;
import com.wei.source.ClickSource;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.*;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;


/**
 * @author huangxuwei
 * @date 2022年10月21日 14:11
 */
public class WindowTest {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        DynamicProcessingTimeSessionWindows<Tuple2<String, Long>> tuple2DynamicProcessingTimeSessionWindows = ProcessingTimeSessionWindows.withDynamicGap(
                new SessionWindowTimeGapExtractor<Tuple2<String, Long>>() {
                    @Override
                    public long extract(Tuple2<String, Long> element) {
                        return element.f0.length() * 1000;
                    }
                });

        DataStreamSource<String> lineDataStreamSource = env.readTextFile("input/word.txt");

        SingleOutputStreamOperator<Tuple2<String, Long>> wordAndOneTuple = lineDataStreamSource.flatMap((String line, Collector<Tuple2<String, Long>> out) -> {
            String[] s = line.split(" ");
            for (String s1 : s) {
                out.collect(Tuple2.of(s1, 1L));
            }
        }).returns(Types.TUPLE(Types.STRING, Types.LONG));

        KeyedStream<Tuple2<String, Long>, String> wordAndOneKeyedStream = wordAndOneTuple.keyBy(data -> data.f0);

        wordAndOneKeyedStream.window(TumblingAlignedProcessingTimeWindows.of(Time.seconds(5)));

        wordAndOneKeyedStream.window(SlidingProcessingTimeWindows.of(Time.seconds(10), Time.seconds(5)));

        wordAndOneKeyedStream.window(ProcessingTimeSessionWindows.withGap(Time.seconds(10)));

        wordAndOneKeyedStream.window(tuple2DynamicProcessingTimeSessionWindows);

        wordAndOneKeyedStream.window(TumblingEventTimeWindows.of(Time.seconds(6)));

        wordAndOneKeyedStream.window(SlidingEventTimeWindows.of(Time.seconds(10), Time.seconds(5)));

        wordAndOneKeyedStream.window(EventTimeSessionWindows.withGap(Time.seconds(10)));

        wordAndOneKeyedStream.countWindow(10);

        wordAndOneKeyedStream.countWindow(10, 3);

        wordAndOneKeyedStream.window(GlobalWindows.create());
    }
}