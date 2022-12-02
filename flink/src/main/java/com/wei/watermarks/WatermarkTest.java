package com.wei.watermarks;

import com.wei.pojo.Event;
import org.apache.flink.api.common.eventtime.SerializableTimestampAssigner;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import java.time.Duration;

public class WatermarkTest {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
        executionEnvironment.setParallelism(1);

        executionEnvironment.socketTextStream("127.0.0.1", 8080)
                .map(new MapFunction<String, Event>() {
                    @Override
                    public Event map(String s) throws Exception {
                        String[] split = s.split(",");
                        return new Event(split[0].trim(), split[1].trim(), Long.valueOf(split[3].trim()));
                    }
                })
                .assignTimestampsAndWatermarks(WatermarkStrategy
                        .<Event>forBoundedOutOfOrderness(Duration.ofSeconds(5))
                        .withTimestampAssigner(new SerializableTimestampAssigner<Event>() {
                            @Override
                            public long extractTimestamp(Event event, long l) {
                                return event.timestamp;
                            }
                        }))
                .keyBy(data -> data.user)
                .window(TumblingEventTimeWindows.of(Time.seconds(10)))
                .process(new WatermarkTestResult()).print();
        executionEnvironment.execute();
    }

    private static class WatermarkTestResult extends ProcessWindowFunction<Event, String, String, TimeWindow> {

        @Override
        public void process(String s, ProcessWindowFunction<Event, String, String, TimeWindow>.Context context, Iterable<Event> iterable, Collector<String> collector) throws Exception {
            Long start = context.window().getStart();
            Long end = context.window().getEnd();
            Long l = context.currentWatermark();
            Long exactSizeIfKnown = iterable.spliterator().getExactSizeIfKnown();
            collector.collect("window: " + start + "~" + end + " has " + exactSizeIfKnown + " elements, when computed, the watermark is " + l);
        }
    }
}
