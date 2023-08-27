package com.wei;


import com.wei.util.OrderUtils;
import com.wei.util.mock.MockClass;
import com.wei.util.mock.People;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 10, time = 1)
@Threads(1)
@Fork(1)
@State(value = Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class OrderBenchmark {

    @Param(value = {"10", "100", "1000", "10000", "100000"})
    private int param;

    @Benchmark
    public void orderWorst() throws Throwable {
        List<People> peopleList = MockClass.mockManyPeople(param);
        List<String> str = MockClass.mockManyPeopleStr(param);
        Collections.reverse(str);
        OrderUtils.orderBySeq(str, peopleList, "code");
        System.out.println(peopleList.size());
    }

    @Benchmark
    public void orderBest() throws Throwable {
        List<People> peopleList = MockClass.mockManyPeople(param);
        List<String> str = MockClass.mockManyPeopleStr(param);
        OrderUtils.orderBySeq(str, peopleList, "code");
        System.out.println(peopleList.size());
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(OrderBenchmark.class.getSimpleName())
                .result(LocalDateTime.now() + "OrderBenchmark.json")
                .resultFormat(ResultFormatType.JSON).build();
        new Runner(opt).run();
    }
}
