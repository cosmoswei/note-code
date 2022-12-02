package com.wei.cep;

import com.wei.pojo.Event;
import com.wei.pojo.LoginEvent;
import org.apache.flink.api.common.eventtime.SerializableTimestampAssigner;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.cep.*;
import org.apache.flink.cep.functions.PatternProcessFunction;
import org.apache.flink.cep.functions.TimedOutPartialMatchHandler;
import org.apache.flink.cep.nfa.aftermatch.AfterMatchSkipStrategy;
import org.apache.flink.cep.pattern.GroupPattern;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.cep.pattern.conditions.SimpleCondition;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;

import java.util.List;
import java.util.Map;

public class LoginFailDetect {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        KeyedStream<LoginEvent, String> stream = env.fromElements(
                new LoginEvent("user_1", "10086", "fail", 20000L),
                new LoginEvent("user_1", "10085", "fail", 30000L),
                new LoginEvent("user_2", "10086", "fail", 40000L),
                new LoginEvent("user_1", "10084", "fail", 50000L),
                new LoginEvent("user_2", "10086", "fail", 60000L),
                new LoginEvent("user_2", "10086", "fail", 70000L),
                new LoginEvent("user_2", "10086", "fail", 80000L)
        ).assignTimestampsAndWatermarks(WatermarkStrategy.<LoginEvent>forMonotonousTimestamps()
                .withTimestampAssigner(new SerializableTimestampAssigner<LoginEvent>() {
                    @Override
                    public long extractTimestamp(LoginEvent loginEvent, long l) {
                        return loginEvent.timestamp;
                    }
                })).keyBy(r -> r.userId);

        Pattern<LoginEvent, LoginEvent> pattern = Pattern.<LoginEvent>begin("first")
                .where(new SimpleCondition<LoginEvent>() {
                    @Override
                    public boolean filter(LoginEvent loginEvent) throws Exception {
                        return loginEvent.eventType.equals("fail");
                    }
                }).next("second").where(
                        new SimpleCondition<LoginEvent>() {
                            @Override
                            public boolean filter(LoginEvent loginEvent) throws Exception {
                                return loginEvent.eventType.equals("fail");
                            }
                        }
                ).next("third").where(
                        new SimpleCondition<LoginEvent>() {
                            @Override
                            public boolean filter(LoginEvent loginEvent) throws Exception {
                                return loginEvent.eventType.equals("fail");
                            }
                        });

        GroupPattern<LoginEvent, LoginEvent> start = Pattern.begin(
                Pattern.<LoginEvent>begin("start_start")
                        .where(new SimpleCondition<LoginEvent>() {
                            @Override
                            public boolean filter(LoginEvent loginEvent) throws Exception {
                                return loginEvent.eventType.equals("fail");
                            }
                        })
                        .followedBy("_start_middle")
                        .where(
                                new SimpleCondition<LoginEvent>() {
                                    @Override
                                    public boolean filter(LoginEvent loginEvent) throws Exception {
                                        return loginEvent.eventType.equals("fail");
                                    }
                                }
                        )
        );

        Pattern<LoginEvent, LoginEvent> strict = start.next(Pattern.<LoginEvent>begin("next")
                        .where(new SimpleCondition<LoginEvent>() {
                            @Override
                            public boolean filter(LoginEvent loginEvent) throws Exception {
                                return loginEvent.eventType.equals("fail");
                            }
                        })
                        .followedBy("next_middle")
                        .where(new SimpleCondition<LoginEvent>() {
                            @Override
                            public boolean filter(LoginEvent loginEvent) throws Exception {
                                return loginEvent.eventType.equals("fail");
                            }
                        }))
                .times(2).consecutive();

        Pattern<LoginEvent, LoginEvent> relaxed = start
                .followedBy(
                        Pattern.<LoginEvent>begin("followedBy_start")
                                .where(
                                        new SimpleCondition<LoginEvent>() {
                                            @Override
                                            public boolean filter(LoginEvent loginEvent) throws Exception {
                                                return loginEvent.eventType.equals("fail");
                                            }
                                        }
                                )
                                .followedBy("followed_by_middle")
                                .where(
                                        new SimpleCondition<LoginEvent>() {
                                            @Override
                                            public boolean filter(LoginEvent loginEvent) throws Exception {
                                                return loginEvent.eventType.equals("fail");
                                            }
                                        }
                                )
                ).oneOrMore();
        Pattern<LoginEvent, LoginEvent> nonDeterminRelaxed = start
                .followedByAny(Pattern.<LoginEvent>begin("followedByAny_start")
                        .where(
                                new SimpleCondition<LoginEvent>() {
                                    @Override
                                    public boolean filter(LoginEvent loginEvent) throws Exception {
                                        return loginEvent.eventType.equals("fail");
                                    }
                                }
                        )
                        .followedBy("followedByAny_middle")
                        .where(new SimpleCondition<LoginEvent>() {
                            @Override
                            public boolean filter(LoginEvent loginEvent) throws Exception {
                                return loginEvent.eventType.equals("fail");
                            }
                        })).optional();


        Pattern.<LoginEvent>begin("a", AfterMatchSkipStrategy.skipToNext()).where(
                new SimpleCondition<LoginEvent>() {
                    @Override
                    public boolean filter(LoginEvent loginEvent) throws Exception {
                        return loginEvent.eventType.equals("fail");
                    }
                }
        );
        EventComparator<LoginEvent> comparator = new EventComparator<LoginEvent>() {
            @Override
            public int compare(LoginEvent o1, LoginEvent o2) {
                return 1;
            }
        };

        PatternStream<LoginEvent> patternStream = CEP.pattern(stream, pattern, comparator);

        patternStream.select(new PatternSelectFunction<LoginEvent, String>() {
            @Override
            public String select(Map<String, List<LoginEvent>> map) throws Exception {
                LoginEvent first = map.get("first").get(0);
                LoginEvent second = map.get("second").get(0);
                LoginEvent third = map.get("third").get(0);
                return first.userId + "连续三次登陆失败！登陆时间：" + first.timestamp + ", " + second.timestamp + ", "
                        + third.timestamp;
            }
        }).print("warning");
        OutputTag<String> timeout = new OutputTag<String>("timeout") {
        };
        SingleOutputStreamOperator<String> resultStream = patternStream.select(timeout,
                new PatternTimeoutFunction<LoginEvent, String>() {
                    @Override
                    public String timeout(Map<String, List<LoginEvent>> map, long l) throws Exception {
                        return null;
                    }
                }, new PatternSelectFunction<LoginEvent, String>() {
                    @Override
                    public String select(Map<String, List<LoginEvent>> map) throws Exception {
                        return null;
                    }
                });

        resultStream.print("matched");
        resultStream.getSideOutput(timeout).print("timeout");
        patternStream.process(new PatternProcessFunction<LoginEvent, String>() {
            @Override
            public void processMatch(Map<String, List<LoginEvent>> map, Context context, Collector<String> collector) throws Exception {
                collector.collect("new ");
            }
        }).print("warning");

        env.execute();
    }

    class MyPatternProcessFunction extends PatternProcessFunction<Event, String> implements TimedOutPartialMatchHandler {
        @Override
        public void processMatch(Map<String, List<Event>> map, Context context, Collector<String> collector) throws Exception {

        }

        @Override
        public void processTimedOutMatch(Map map, Context context) throws Exception {

        }
    }
}
