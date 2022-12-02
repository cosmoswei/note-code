/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2022. All Rights Reserved.
 */
package com.wei.watermarks;

import com.wei.pojo.Event;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.api.watermark.Watermark;

import java.util.Calendar;
import java.util.Random;

/**
 * @author huangxuwei
 * @date 2022年10月21日 16:24
 */
public class EmitWatermarkInSourceFunction {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
        executionEnvironment.setParallelism(1);
        executionEnvironment.addSource(new ClickSourceWithWatermark()).print();
        executionEnvironment.execute();
    }

    private static class ClickSourceWithWatermark implements SourceFunction<Event> {

        private Boolean running = true;

        @Override
        public void run(SourceContext<Event> sourceContext) throws Exception {
            Random random = new Random();
            String[] userArr = {"Mary", "Bob", "Alice"};
            String[] urlArr = {"./a", "./B", "./AA"};
            while (running) {
                long currTs = Calendar.getInstance().getTimeInMillis();
                String username = userArr[random.nextInt(userArr.length)];
                String url = urlArr[random.nextInt(urlArr.length)];
                Event event = new Event(username, url, currTs);
                sourceContext.collectWithTimestamp(event, event.timestamp);
                sourceContext.emitWatermark(new Watermark(event.timestamp - 1));
                Thread.sleep(1000L);
            }

        }

        @Override
        public void cancel() {
            running = false;
        }
    }
}