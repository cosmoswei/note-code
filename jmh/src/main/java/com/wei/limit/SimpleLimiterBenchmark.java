package com.wei.limit;

import com.wei.LimiterAppRun;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 2, time = 1)
@Measurement(iterations = 5, time = 1)
@Threads(1)
@Fork(1)
@State(value = Scope.Benchmark)
@OutputTimeUnit(TimeUnit.SECONDS)
public class SimpleLimiterBenchmark {

    private ConfigurableApplicationContext context;
    private SimpleLimiterTest simpleLimiterTest;

    @Param(value = {"10", "100", "1000", "10000"})
    private int param;

    @Setup
    public void init() {
        // 这里的WebApplication.class是项目里的spring boot启动类
        context = SpringApplication.run(LimiterAppRun.class);
        // 获取需要测试的bean
        this.simpleLimiterTest = (SimpleLimiterTest) context.getBean("simpleLimiterTest");
    }

    @TearDown
    public void down() {
        context.close();
    }

    @Benchmark
    public void prueTest() {
        for (int i = 0; i < param; i++) {
            simpleLimiterTest.prueTest();
        }
    }

    @Benchmark
    public void counterTest() {
        for (int i = 0; i < param; i++) {
            simpleLimiterTest.counterTest();
        }
    }

    @Benchmark
    public void slidingWindowV1Test() {
        for (int i = 0; i < param; i++) {
            simpleLimiterTest.slidingWindowV1Test();
        }
    }

    @Benchmark
    public void slidingWindowV2Test() {
        for (int i = 0; i < param; i++) {
            simpleLimiterTest.slidingWindowV2Test();
        }
    }

    @Benchmark
    public void sentinelTest() {
        for (int i = 0; i < param; i++) {
            simpleLimiterTest.counterTest();
        }
    }

    @Benchmark
    public void resilience4jTest() {
        for (int i = 0; i < param; i++) {
            simpleLimiterTest.resilience4jTest();
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SimpleLimiterBenchmark.class.getSimpleName())
                .result(LocalDateTime.now() + "SimpleLimiterBenchmark_10000_10.json")
                .resultFormat(ResultFormatType.JSON).build();
        new Runner(opt).run();
    }
}
