package com.wei.reflect;

import com.wei.util.mock.People;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 10, time = 1)
@Threads(1)
@Fork(1)
@State(value = Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class ReflectBenchmark {

    @Param(value = {"1000", "100000", "10000000"})
    private int param;

    private static final People people = new People("xiaohuang", 1, "1");

    @Benchmark
    public void no() {
        ArrayList arrayList = new ArrayList(param);
        for (int i = 0; i < param; i++) {
            Integer id = people.getId();
            arrayList.add(id);
        }
        System.out.println(arrayList.size());
    }

    @Benchmark
    public void origin() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ArrayList arrayList = new ArrayList(param);
        for (int i = 0; i < param; i++) {
            Method method = people.getClass().getMethod("getId");
            Object invoke = method.invoke(people);
            arrayList.add(invoke);
        }
        System.out.println(arrayList.size());
    }

    @Benchmark
    public void origin2() throws Exception {
        ArrayList arrayList = new ArrayList(param);
        Method method = people.getClass().getMethod("getId");
        for (int i = 0; i < param; i++) {
            Object invoke = method.invoke(people);
            arrayList.add(invoke);
        }
        System.out.println(arrayList.size());
    }

    @Benchmark
    public void cache() throws Exception {
        ArrayList arrayList = new ArrayList(param);
        for (int i = 0; i < param; i++) {
            Method method = MethodCache.getMethod(People.class, "getId");
            Object invoke = method.invoke(people);
            arrayList.add(invoke);
        }
        System.out.println(arrayList.size());
    }

    @Benchmark
    public void cache2() throws Exception {
        ArrayList arrayList = new ArrayList(param);
        Method method = MethodCache.getMethod(People.class, "getId");
        for (int i = 0; i < param; i++) {
            Object invoke = method.invoke(people);
            arrayList.add(invoke);
        }
        System.out.println(arrayList.size());
    }

    @Benchmark
    public void methodHandle() throws Throwable {
        ArrayList arrayList = new ArrayList(param);
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        for (int i = 0; i < param; i++) {
            MethodHandle mh = lookup.findVirtual(People.class, "getId", MethodType.methodType(Integer.class));
            Object invoke = mh.invoke(people);
            arrayList.add(invoke);
        }
        System.out.println(arrayList.size());
    }

    @Benchmark
    public void methodHandle2() throws Throwable {
        ArrayList arrayList = new ArrayList(param);
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle mh = lookup.findVirtual(People.class, "getId", MethodType.methodType(Integer.class));
        for (int i = 0; i < param; i++) {
            Object invoke = mh.invoke(people);
            arrayList.add(invoke);
        }
        System.out.println(arrayList.size());
    }

//    @Benchmark
//    public void proxy() {
//        // TODO document why this method is empty
//    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(ReflectBenchmark.class.getSimpleName())
                .result(LocalDateTime.now() + "ReflectBenchmark.json")
                .resultFormat(ResultFormatType.JSON).build();
        new Runner(opt).run();
    }
}
