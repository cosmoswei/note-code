package com.wei.process;

import com.wei.pojo.Event;
import com.wei.source.ClickSource;
import org.apache.flink.api.common.eventtime.SerializableTimestampAssigner;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.ProcessAllWindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.SlidingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class ProcessAllWindowTopN {
    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.setParallelism(1);

        SingleOutputStreamOperator<Event> stream = env.addSource(new ClickSource())
                .assignTimestampsAndWatermarks(WatermarkStrategy.<Event>forMonotonousTimestamps()
                        .withTimestampAssigner(new SerializableTimestampAssigner<Event>() {
                            @Override
                            public long extractTimestamp(Event event, long l) {
                                return event.timestamp;
                            }
                        }));
        SingleOutputStreamOperator<String> result = stream.map(new MapFunction<Event, String>() {
                    @Override
                    public String map(Event event) throws Exception {
                        return event.url;
                    }
                }).windowAll(SlidingEventTimeWindows.of(Time.seconds(10), Time.seconds(5)))
                .process(new ProcessAllWindowFunction<String, String, TimeWindow>() {
                    @Override
                    public void process(ProcessAllWindowFunction<String, String, TimeWindow>.Context context, Iterable<String> iterable, Collector<String> collector) throws Exception {
                        HashMap<String, Long> stringLongHashMap = new HashMap<>();
                        for (String url : iterable) {
                            if (stringLongHashMap.containsKey(url)) {
                                Long aLong = stringLongHashMap.get(url);
                                stringLongHashMap.put(url, aLong + 1L);
                            } else {
                                stringLongHashMap.put(url, 1L);
                            }
                        }
                        List<Tuple2<String, Long>> mapList = new ArrayList<>();
                        for (String key : stringLongHashMap.keySet()) {
                            mapList.add(Tuple2.of(key, stringLongHashMap.get(key)));
                        }
                        mapList.sort(new Comparator<Tuple2<String, Long>>() {
                            @Override
                            public int compare(Tuple2<String, Long> o1, Tuple2<String, Long> o2) {
                                return o2.f1.intValue() - o1.f1.intValue();
                            }
                        });
                        StringBuilder result = new StringBuilder();
                        result.append("==============================\n");
                        for (int i = 0; i < 2; i++) {
                            Tuple2<String, Long> temp = mapList.get(i);
                            String info = "浏览量No." + (i + 1) + "url: " + temp.f0 + "浏览量：" + temp.f1 + "窗口结束时间：" + new Timestamp(context.window().getEnd()) + "\n";
                            result.append(info);
                        }
                        result.append("==============================\n");
                        collector.collect(result.toString());
                    }
                });

        result.print();

        env.execute();
    }
}
