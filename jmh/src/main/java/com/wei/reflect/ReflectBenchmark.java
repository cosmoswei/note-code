package com.wei.reflect;


import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 10, time = 1)
@Threads(1)
@Fork(1)
@State(value = Scope.Benchmark)
@OutputTimeUnit(TimeUnit.SECONDS)
public class ReflectBenchmark {

    @Param(value = {"10", "100", "1000", "10000", "100000"})
    private int param;

    @Benchmark
    public void origin() {
        // TODO document why this method is empty
    }

    @Benchmark
    public void cache() {
        // TODO document why this method is empty
    }

    @Benchmark
    public void methodHandle() {
        // TODO document why this method is empty
    }

    @Benchmark
    public void proxy() {
        // TODO document why this method is empty
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(ReflectBenchmark.class.getSimpleName())
                .result(LocalDateTime.now() + "ReflectBenchmark.json")
                .resultFormat(ResultFormatType.JSON).build();
        new Runner(opt).run();
    }
}
