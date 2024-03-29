package com.wei.process;

import com.wei.pojo.Event;
import org.apache.flink.api.common.eventtime.SerializableTimestampAssigner;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.util.Collector;

public class EventTimeTimerTest {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        SingleOutputStreamOperator<Event> streamOperator = env.addSource(new CustomSource()).assignTimestampsAndWatermarks(
                WatermarkStrategy.<Event>forMonotonousTimestamps()
                        .withTimestampAssigner(new SerializableTimestampAssigner<Event>() {
                            @Override
                            public long extractTimestamp(Event event, long l) {
                                return event.timestamp;
                            }
                        })
        );
        streamOperator.keyBy(data -> true)
                .process(new KeyedProcessFunction<Boolean, Event, String>() {
                    @Override
                    public void processElement(Event event, KeyedProcessFunction<Boolean, Event, String>.Context context, Collector<String> collector) throws Exception {
                        collector.collect("数据达到，时间戳为：" + context.timestamp());
                        collector.collect("数据达到，水位线为：" + context.timerService().currentWatermark());
                        context.timerService().registerEventTimeTimer(context.timestamp() + 10 * 1000L);
                    }

                    @Override
                    public void onTimer(long timestamp, KeyedProcessFunction<Boolean, Event, String>.OnTimerContext ctx, Collector<String> out) throws Exception {
                        out.collect("定时器触发，触发时间：" + timestamp);
                    }
                }).print();
        env.execute();
    }

    private static class CustomSource implements SourceFunction<Event> {
        @Override
        public void run(SourceContext<Event> sourceContext) throws Exception {
            sourceContext.collect(new Event("huangxuwei", "/home", 1000L));
            Thread.sleep(5000L);
            sourceContext.collect(new Event("huangxuwei", "/home", 11000L));
            Thread.sleep(5000L);
            sourceContext.collect(new Event("cosmoswei", "/home", 11001L));
            Thread.sleep(5000L);
        }

        @Override
        public void cancel() {

        }
    }
}
