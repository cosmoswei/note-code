/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2022. All Rights Reserved.
 */
package com.wei.source;

import org.apache.flink.streaming.api.functions.source.ParallelSourceFunction;

import java.util.Random;

/**
 * @author huangxuwei
 */
public class CustomSource implements ParallelSourceFunction<Integer> {
    private Boolean running = true;
    private Random random = new Random();


    @Override
    public void run(SourceContext<Integer> sourceContext) throws Exception {
        while (running) {
            sourceContext.collect(random.nextInt());
        }
    }

    @Override
    public void cancel() {
        running = false;
    }
}