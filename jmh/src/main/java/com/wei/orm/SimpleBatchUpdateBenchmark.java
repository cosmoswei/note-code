package com.wei.orm;

import com.wei.DataAppRun;
import com.wei.entity.DepartmentEmployeesSimple;
import com.wei.mapper.DepartmentEmployeesSimpleRepository;
import com.wei.service.simple.DepartmentEmployeesSimpleService;
import com.wei.service.simple.impl.*;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.wei.mock.MockUtils.getMockDepartmentEmployeesSimple;

@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 10, time = 1)
@Threads(1)
@Fork(1)
@State(value = Scope.Benchmark)
@OutputTimeUnit(TimeUnit.SECONDS)
public class SimpleBatchUpdateBenchmark {

    private ConfigurableApplicationContext context;
    private ForeachUpdate foreachUpdate;
    private CaseWhenUpdate caseWhenUpdate;
    private ForeachXmlUpdate foreachXmlUpdate;
    private SingleFieldUpdate singleFieldUpdate;
    private BatchExecutorUpdate batchExecutorUpdate;
    private DepartmentEmployeesSimpleService departmentEmployeesSimpleService;
    private DepartmentEmployeesSimpleRepository departmentEmployeesSimpleRepository;

    @Param(value = {"10", "100", "1000", "10000", "100000"})
    private int param;

    @Setup
    public void init() {
        // 这里的WebApplication.class是项目里的spring boot启动类
        context = SpringApplication.run(DataAppRun.class);
        // 获取需要测试的bean
        this.foreachXmlUpdate = (ForeachXmlUpdate) context.getBean("simpleForeachXmlUpdate");
        this.foreachUpdate = (ForeachUpdate) context.getBean("simpleForeachUpdate");
        this.batchExecutorUpdate = (BatchExecutorUpdate) context.getBean("simpleBatchExecutorUpdate");
        this.singleFieldUpdate = (SingleFieldUpdate) context.getBean("simpleSingleFieldUpdate");
        this.caseWhenUpdate = (CaseWhenUpdate) context.getBean("simpleCaseWhenUpdate");
        this.departmentEmployeesSimpleService = (DepartmentEmployeesSimpleService) context.getBean("departmentEmployeesSimpleService");
        this.departmentEmployeesSimpleRepository = (DepartmentEmployeesSimpleRepository) context.getBean("departmentEmployeesSimpleRepository");
    }

    @TearDown
    public void down() {
        context.close();
    }

    @Benchmark
    public void batchExecutorUpdate() {
        List<DepartmentEmployeesSimple> mockData = getMockDepartmentEmployeesSimple(param);
        mockData.forEach(e -> e.setEmployeeName("batchExecutorUpdate 更新后的部门名字"));
        batchExecutorUpdate.batchExecutorUpdate(mockData);
    }

    @Benchmark
    public void caseWhenUpdate() {
        List<DepartmentEmployeesSimple> mockData = getMockDepartmentEmployeesSimple(param);
        mockData.forEach(e -> e.setEmployeeName("caseWhenUpdate 更新后的部门名字"));
        caseWhenUpdate.caseWhenUpdate(mockData);
    }

    @Benchmark
    public void foreachUpdate() {
        List<DepartmentEmployeesSimple> mockData = getMockDepartmentEmployeesSimple(param);
        mockData.forEach(e -> e.setEmployeeName("foreachUpdate 更新后的部门名字"));
        foreachUpdate.foreachUpdate(mockData);
    }

    @Benchmark
    public void foreachXmlUpdate() {
        List<DepartmentEmployeesSimple> mockData = getMockDepartmentEmployeesSimple(param);
        mockData.forEach(e -> e.setEmployeeName("foreachXmlUpdate 更新后的部门名字"));
        foreachXmlUpdate.batchUpdate(mockData);
    }

    @Benchmark
    public void myBatisPlusUpdate() {
        List<DepartmentEmployeesSimple> mockData = getMockDepartmentEmployeesSimple(param);
        mockData.forEach(e -> e.setEmployeeName("myBatisPlusUpdate 更新后的部门名字"));
        departmentEmployeesSimpleService.updateBatchById(mockData);
    }

    @Benchmark
    public void jpaUpdate() {
        List<DepartmentEmployeesSimple> mockData = getMockDepartmentEmployeesSimple(param);
        mockData.forEach(e -> e.setEmployeeName("jpaUpdate 更新后的部门名字"));
        departmentEmployeesSimpleRepository.saveAllAndFlush(mockData);
    }

    @Benchmark
    public void singleFieldUpdate() {
        List<DepartmentEmployeesSimple> mockData = getMockDepartmentEmployeesSimple(param);
        List<Long> ids = mockData.stream().map(DepartmentEmployeesSimple::getId).collect(Collectors.toList());
        singleFieldUpdate.batchUpdateSingle(ids, "singleFieldUpdate 更新后的部门名字");

    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SimpleBatchUpdateBenchmark.class.getSimpleName())
                .result(LocalDateTime.now() + "Simple_BatchUpdateBenchmark_10000_10_new_RC.csv")
                .resultFormat(ResultFormatType.CSV).build();
        new Runner(opt).run();
    }
}
