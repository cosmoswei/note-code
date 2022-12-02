/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2022. All Rights Reserved.
 */
package com.wei.transform;

import com.wei.pojo.Event;
import com.wei.source.ClickSource;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;

/**
 * @author huangxuwei
 * @date 2022年10月21日 10:08
 */
public class RescaleTest {
    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();

        executionEnvironment.setParallelism(1);

        executionEnvironment.addSource(new RichParallelSourceFunction<Integer>() {

                    @Override
                    public void run(SourceContext<Integer> sourceContext) throws Exception {
                        for (int i = 0; i < 8; i++) {
                            if ((i + 1) % 2 == getRuntimeContext().getIndexOfThisSubtask()) {
                                sourceContext.collect(i + 1);
                            }
                        }
                    }

                    @Override
                    public void cancel() {

                    }
                }).setParallelism(2)
                .rescale().print().setParallelism(4);

        executionEnvironment.execute();
    }
}