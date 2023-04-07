package com.wei.orm;

import com.wei.AppRun;
import com.wei.entity.DepartmentEmployees;
import com.wei.mapper.DepartmentEmployeesMapper;
import com.wei.mapper.DepartmentEmployeesRepository;
import com.wei.service.DepartmentEmployeesService;
import com.wei.service.impl.BatchDemo;
import com.wei.service.impl.CaseWhenDemo;
import com.wei.service.impl.ForeachDemo;
import com.wei.service.impl.InitialDemo;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Threads(1)
@Fork(1)
@State(value = Scope.Benchmark)
@OutputTimeUnit(TimeUnit.SECONDS)
public class BatchUpdateBenchmark {

    private ConfigurableApplicationContext context;
    private BatchDemo batchDemo;
    private ForeachDemo foreachDemo;
    private InitialDemo initialDemo;
    private CaseWhenDemo caseThenDemo;
    private DepartmentEmployeesMapper departmentEmployeesMapper;
    private DepartmentEmployeesService departmentEmployeesService;
    private DepartmentEmployeesRepository departmentEmployeesRepository;

    @Param(value = {"10", "50", "100","1000","10000"})
    private int param;

    @Setup
    public void init() {
        // 这里的WebApplication.class是项目里的spring boot启动类
        context = SpringApplication.run(AppRun.class);
        // 获取需要测试的bean
        this.batchDemo = (BatchDemo) context.getBean("batchDemo");
        this.foreachDemo = (ForeachDemo) context.getBean("foreachDemo");
        this.initialDemo = (InitialDemo) context.getBean("initialDemo");
        this.caseThenDemo = (CaseWhenDemo) context.getBean("caseWhenDemo");
        this.departmentEmployeesMapper = (DepartmentEmployeesMapper) context.getBean("departmentEmployeesMapper");
        this.departmentEmployeesService = (DepartmentEmployeesService) context.getBean("departmentEmployeesService");
        this.departmentEmployeesRepository = (DepartmentEmployeesRepository) context.getBean("departmentEmployeesRepository");

    }

    @TearDown
    public void down() {
        context.close();
    }

    @Benchmark
    public void initialUpdate() {
        List<Long> ids = LongStream.range(0, param).boxed().collect(Collectors.toList());
        List<DepartmentEmployees> result = departmentEmployeesMapper.selectByIds(ids);
        result.forEach(e -> e.setEmployeeName("initialUpdate 更新后的部门名字"));
        initialDemo.initialUpdate(result);
    }

    @Benchmark
    public void caseWhenUpdate() {
        List<Long> ids = LongStream.range(0, param).boxed().collect(Collectors.toList());
        List<DepartmentEmployees> result = departmentEmployeesMapper.selectByIds(ids);
        result.forEach(e -> e.setEmployeeName("caseWhenUpdate 更新后的部门名字"));
        caseThenDemo.caseWhenUpdate(result);
    }

    @Benchmark
    public void foreachUpdate() {
        List<Long> ids = LongStream.range(0, param).boxed().collect(Collectors.toList());
        List<DepartmentEmployees> result = departmentEmployeesMapper.selectByIds(ids);
        result.forEach(e -> e.setEmployeeName("foreachUpdate 更新后的部门名字"));
        foreachDemo.foreachUpdate(result);
    }

    @Benchmark
    public void batchUpdate() {
        List<Long> ids = LongStream.range(0, param).boxed().collect(Collectors.toList());
        List<DepartmentEmployees> result = departmentEmployeesMapper.selectByIds(ids);
        result.forEach(e -> e.setEmployeeName("batchUpdate 更新后的部门名字"));
        batchDemo.batchUpdate(result);
    }

    @Benchmark
    public void updateBatchPlus() {
        List<Long> ids = LongStream.range(0, param).boxed().collect(Collectors.toList());
        List<DepartmentEmployees> result = departmentEmployeesMapper.selectByIds(ids);
        result.forEach(e -> e.setEmployeeName("updateBatchPlus 更新后的部门名字"));
        departmentEmployeesService.updateBatchById(result);
    }

    @Benchmark
    public void saveAllAndFlush() {
        List<Integer> ids = IntStream.range(0, param).boxed().collect(Collectors.toList());
        List<DepartmentEmployees> allById = departmentEmployeesRepository.findAllById(ids);
        allById.forEach(e -> e.setEmployeeName("saveAllAndFlush 更新后的部门名字"));
        departmentEmployeesRepository.saveAllAndFlush(allById);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(BatchUpdateBenchmark.class.getSimpleName())
                .result("BatchUpdateBenchmark_10000.json")
                .resultFormat(ResultFormatType.JSON).build();
        new Runner(opt).run();
    }
}
