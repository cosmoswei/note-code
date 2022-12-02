package com.wei.process;

import com.wei.pojo.Event;
import com.wei.source.ClickSource;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;

import java.sql.Timestamp;

public class ProcessingTimeTimerTest {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        DataStreamSource<Event> streamSource = env.addSource(new ClickSource());

        streamSource.keyBy(data -> true)
                .process(new KeyedProcessFunction<Boolean, Event, Object>() {
                    @Override
                    public void processElement(Event event, KeyedProcessFunction<Boolean, Event, Object>.Context context, Collector<Object> collector) throws Exception {
                        long currentProcessingTime = context.timerService().currentProcessingTime();

                        collector.collect("date come, time is " + new Timestamp(currentProcessingTime));

                        context.timerService().registerProcessingTimeTimer(currentProcessingTime + 10 * 1000L);
                    }

                    @Override
                    public void onTimer(long timestamp, KeyedProcessFunction<Boolean, Event, Object>.OnTimerContext ctx, Collector<Object> out) throws Exception {
                        out.collect("timer trigger, trigger time is " + new Timestamp(timestamp));
                    }
                }).print();

        env.execute();
    }
}
