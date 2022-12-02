package com.wei.cep;

import com.wei.pojo.LoginEvent;
import com.wei.pojo.OrderEvent;
import org.apache.flink.api.common.eventtime.SerializableTimestampAssigner;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.cep.CEP;
import org.apache.flink.cep.PatternSelectFunction;
import org.apache.flink.cep.PatternStream;
import org.apache.flink.cep.functions.PatternProcessFunction;
import org.apache.flink.cep.functions.TimedOutPartialMatchHandler;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.cep.pattern.conditions.SimpleCondition;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;

import java.util.List;
import java.util.Map;

public class OrderTimeoutDetectExample {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.setParallelism(1);

        KeyedStream<OrderEvent, String> stream = env.fromElements(
                        new OrderEvent("user_1", "10086", "fail", 20000L),
                        new OrderEvent("user_1", "10085", "fail", 30000L),
                        new OrderEvent("user_2", "10086", "fail", 40000L),
                        new OrderEvent("user_1", "10084", "fail", 50000L),
                        new OrderEvent("user_2", "10086", "fail", 60000L),
                        new OrderEvent("user_2", "10086", "fail", 70000L),
                        new OrderEvent("user_2", "10086", "fail", 80000L))
                .assignTimestampsAndWatermarks(WatermarkStrategy.<OrderEvent>forMonotonousTimestamps()
                        .withTimestampAssigner(new SerializableTimestampAssigner<OrderEvent>() {
                            @Override
                            public long extractTimestamp(OrderEvent orderEvent, long l) {
                                return orderEvent.timestamp;
                            }
                        })).keyBy(orderEvent -> orderEvent.orderId);

        Pattern<OrderEvent, OrderEvent> pattern = Pattern.<OrderEvent>begin("create")
                .where(new SimpleCondition<OrderEvent>() {
                    @Override
                    public boolean filter(OrderEvent orderEvent) throws Exception {
                        return orderEvent.eventType.equals("create");
                    }
                })
                .followedBy("pay")
                .where(new SimpleCondition<OrderEvent>() {
                    @Override
                    public boolean filter(OrderEvent orderEvent) throws Exception {
                        return orderEvent.eventType.equals("pay");
                    }
                }).within(Time.minutes(15));

        PatternStream<OrderEvent> patternStream = CEP.pattern(stream, pattern);
        SingleOutputStreamOperator<String> process = patternStream.process(new OrderPatternProcessFunction());
        process.print("payed");
        process.getSideOutput(new OutputTag<>("timeout")).print();
        SingleOutputStreamOperator<Object> lateData = patternStream.sideOutputLateData(new OutputTag<>("late_data")).select(new PatternSelectFunction<OrderEvent, Object>() {
            @Override
            public Object select(Map<String, List<OrderEvent>> map) {
                return null;
            }
        });
        lateData.getSideOutput(new OutputTag<>("late_data")).print();
        env.execute();
    }

    private static class OrderPatternProcessFunction extends PatternProcessFunction<OrderEvent, String> implements TimedOutPartialMatchHandler<OrderEvent> {

        @Override
        public void processMatch(Map<String, List<OrderEvent>> map, Context context, Collector<String> collector) throws Exception {
            OrderEvent pay = map.get("pay").get(0);
            collector.collect("order: " + pay.orderId + " is payed! ");
        }

        @Override
        public void processTimedOutMatch(Map<String, List<OrderEvent>> map, Context context) throws Exception {
            OrderEvent create = map.get("create").get(0);
            context.output(new OutputTag<String>("timeout") {
            }, "order: " + create.orderId + " is timeout! this userId is " + create.userId);
        }
    }
}
