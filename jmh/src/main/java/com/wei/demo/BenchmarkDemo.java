package com.wei.demo;

import com.wei.DataAppRun;
import com.wei.entity.DepartmentEmployees;
import com.wei.mapper.DepartmentEmployeesMapper;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * Benchmark
 *
 * @author wangpenglei
 * @since 2019/11/27 13:54
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class BenchmarkDemo {

    public static void main(String[] args) throws Exception {
        // 使用一个单独进程执行测试，执行5遍warmup，然后执行5遍测试
        Options opt = new OptionsBuilder().include(BenchmarkDemo.class.getSimpleName()).forks(1).warmupIterations(5)
                .measurementIterations(5).build();
        new Runner(opt).run();
    }

    private ConfigurableApplicationContext context;

    @Setup
    public void init() {
        // 这里的WebApplication.class是项目里的spring boot启动类
        context = SpringApplication.run(DataAppRun.class);
        // 获取需要测试的bean
    }

    @TearDown
    public void down() {
        context.close();
    }

    @org.openjdk.jmh.annotations.Benchmark
    public void test() {
        DepartmentEmployeesMapper departmentEmployeesMapper = (DepartmentEmployeesMapper) context.getBean("departmentEmployeesMapper");
        DepartmentEmployees departmentEmployees = departmentEmployeesMapper.selectByPrimaryKey(1L);
        System.out.println(departmentEmployees);
    }

}
