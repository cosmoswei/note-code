package com.wei.demo;

import com.wei.util.ThreadUtils;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 20, time = 1)
@Threads(5)
@Fork(1)
@State(value = Scope.Benchmark)
@OutputTimeUnit(TimeUnit.SECONDS)
public class JmhTimeTest {

    @Param(value = {"1", "2", "3", "4", "5","6"})
    private long length;

    @Benchmark
    public void sleepOnceSecond() {
        ThreadUtils.safeSleep(length * 100);
    }

    @Benchmark
    public void sleepTwiceSecond() {
        ThreadUtils.safeSleep(length * 200L);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JmhTimeTest.class.getSimpleName())
                .result("JmhTimeTest.json")
                .resultFormat(ResultFormatType.JSON).build();
        new Runner(opt).run();
    }
}