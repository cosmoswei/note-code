package com.wei;

import com.wei.pojo.Event;
import com.wei.source.ClickSource;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;

public class SplitStreamByOutputTag {

    private static OutputTag<Tuple3<String, String, Long>> MARY_TAG = new OutputTag<Tuple3<String, String, Long>>("Mary-pv"){};
    private static OutputTag<Tuple3<String, String, Long>> BOB_TAG = new OutputTag<Tuple3<String, String, Long>>("Bob-pv"){};

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.setParallelism(1);

        DataStreamSource<Event> stream = env.addSource(new ClickSource());

        SingleOutputStreamOperator<Event> process = stream.process(new ProcessFunction<Event, Event>() {
            @Override
            public void processElement(Event event, ProcessFunction<Event, Event>.Context context, Collector<Event> collector) throws Exception {
                if (event.user.equals("Mary")) {
                    context.output(MARY_TAG, new Tuple3<>(event.user, event.url, event.timestamp));
                } else if (event.user.equals("Bob")) {
                    context.output(BOB_TAG, new Tuple3<>(event.user, event.url, event.timestamp));
                } else {
                    collector.collect(event);
                }
            }
        });

        process.getSideOutput(MARY_TAG).print("mary_tag");
        process.getSideOutput(BOB_TAG).print("bob_tag");
        process.print("else");

        env.execute();
    }
}
