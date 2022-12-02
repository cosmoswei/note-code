/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2022. All Rights Reserved.
 */
package com.wei.transform;

import com.wei.pojo.Event;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author huangxuwei
 * @date 2022年10月20日 22:02
 */
public class RichFunctionTest {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
        executionEnvironment.setParallelism(1);
        DataStreamSource<Event> clicks = executionEnvironment.fromElements(
                new Event("A", "/1", 1L),
                new Event("A", "/1", 2L),
                new Event("A", "/1", 3L),
                new Event("B", "/1", 3L),
                new Event("C", "/1", 3L));

        clicks.map(new RichMapFunction<Event, Object>() {

            @Override
            public void open(Configuration parameters) throws Exception {
                super.open(parameters);
                System.out.println("索引为" + getRuntimeContext().getIndexOfThisSubtask() + "任务开始");
                System.out.println("索引为" + getRuntimeContext().getTaskName() + "任务开始");
                System.out.println("索引为" + getRuntimeContext().getMaxNumberOfParallelSubtasks() + "任务开始");
            }

            @Override
            public Object map(Event event) throws Exception {
                return event.getTimestamp();
            }

            @Override
            public void close() throws Exception {
                super.close();
                System.out.println("索引为" + getRuntimeContext().getIndexOfThisSubtask() + "任务结束");
            }
        }).print();
        executionEnvironment.execute();
    }
}