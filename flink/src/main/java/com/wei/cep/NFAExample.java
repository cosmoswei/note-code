package com.wei.cep;

import com.wei.pojo.LoginEvent;
import lombok.Data;
import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

import java.io.Serializable;

public class NFAExample {
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
        ).keyBy(r -> r.userId);
        DataStream<String> streamOperator = stream.flatMap(new StateMachineMapper());

        streamOperator.print("warning");


        env.execute();
    }

    @SuppressWarnings("serial")
    public static class StateMachineMapper extends RichFlatMapFunction<LoginEvent, String> {
        private ValueState<State> currentState;

        @Override
        public void open(Configuration parameters) throws Exception {
            currentState = getRuntimeContext().getState(new ValueStateDescriptor<>("state", State.class));
        }

        @Override
        public void flatMap(LoginEvent loginEvent, Collector<String> collector) throws Exception {
            State state = currentState.value();
            if (state == null) {
                state = State.Initial;
            }
            State nextState = state.transition(loginEvent.eventType);
            if (nextState == State.Matched) {
                collector.collect(loginEvent.getUserId() + "");
            } else if (nextState == State.Initial) {
                currentState.update(State.Initial);
            } else {
                currentState.update(nextState);
            }
        }
    }

    public enum State {
        Terminal,
        Matched,
        S2(new Transition("fail", Matched), new Transition("success", Terminal)),
        S1(new Transition("fail", S2), new Transition("success", Terminal)),
        Initial(new Transition("fail", S1), new Transition("success", Terminal));;

        private final Transition[] transitions;

        State(Transition... transitions) {
            this.transitions = transitions;
        }

        public State transition(String eventType) {
            for (Transition transition : transitions) {
                if (transition.eventType.equals(eventType)) {
                    return transition.getTargetState();
                }
            }
            return Initial;
        }
    }

    @Data
    private static class Transition implements Serializable {

        private static final long serializable = 1L;

        private final String eventType;

        private final State targetState;

        private Transition(String eventType, State targetState) {
            this.eventType = eventType;
            this.targetState = targetState;
        }

    }
}
