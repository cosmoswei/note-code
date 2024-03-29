package com.wei.orm;

import com.wei.DataAppRun;
import com.wei.entity.DepartmentEmployees;
import com.wei.service.DepartmentEmployeesService;
import com.wei.service.impl.*;
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

import static com.wei.mock.MockUtils.getMockDepartmentEmployees;

@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 10, time = 1)
@Threads(1)
@Fork(1)
@State(value = Scope.Benchmark)
@OutputTimeUnit(TimeUnit.SECONDS)
public class BatchUpdateBenchmark {

    private ConfigurableApplicationContext context;
    private ForeachUpdate foreachUpdate;
    private CaseWhenUpdate caseWhenUpdate;
    private ForeachXmlUpdate foreachXmlUpdate;
    private SingleFieldUpdate singleFieldUpdate;
    private BatchExecutorUpdate batchExecutorUpdate;
    private DepartmentEmployeesService departmentEmployeesService;
//    private DepartmentEmployeesRepository departmentEmployeesRepository;

    @Param(value = {"10", "100", "1000", "10000", "100000"})
    private int param;

    @Setup
    public void init() {
        // 这里的WebApplication.class是项目里的spring boot启动类
        context = SpringApplication.run(DataAppRun.class);
        // 获取需要测试的bean
        this.foreachXmlUpdate = (ForeachXmlUpdate) context.getBean("foreachXmlUpdate");
        this.foreachUpdate = (ForeachUpdate) context.getBean("foreachUpdate");
        this.batchExecutorUpdate = (BatchExecutorUpdate) context.getBean("batchExecutorUpdate");
        this.singleFieldUpdate = (SingleFieldUpdate) context.getBean("singleFieldUpdate");
        this.caseWhenUpdate = (CaseWhenUpdate) context.getBean("caseWhenUpdate");
        this.departmentEmployeesService = (DepartmentEmployeesService) context.getBean("departmentEmployeesService");
//        this.departmentEmployeesRepository = (DepartmentEmployeesRepository) context.getBean("departmentEmployeesRepository");
    }

    @TearDown
    public void down() {
        context.close();
    }

    @Benchmark
    public void batchExecutorUpdate() {
        List<DepartmentEmployees> mockData = getMockDepartmentEmployees(param);
        mockData.forEach(e -> e.setEmployeeName("batchExecutorUpdate 更新后的部门名字"));
        batchExecutorUpdate.batchExecutorUpdate(mockData);
    }

    @Benchmark
    public void caseWhenUpdate() {
        List<DepartmentEmployees> mockData = getMockDepartmentEmployees(param);
        mockData.forEach(e -> e.setEmployeeName("caseWhenUpdate 更新后的部门名字"));
        caseWhenUpdate.caseWhenUpdate(mockData);
    }

    @Benchmark
    public void foreachUpdate() {
        List<DepartmentEmployees> mockData = getMockDepartmentEmployees(param);
        mockData.forEach(e -> e.setEmployeeName("foreachUpdate 更新后的部门名字"));
        foreachUpdate.foreachUpdate(mockData);
    }

    @Benchmark
    public void foreachXmlUpdate() {
        List<DepartmentEmployees> mockData = getMockDepartmentEmployees(param);
        mockData.forEach(e -> e.setEmployeeName("foreachXmlUpdate 更新后的部门名字"));
        foreachXmlUpdate.batchUpdate(mockData);
    }

    @Benchmark
    public void myBatisPlusUpdate() {
        List<DepartmentEmployees> mockData = getMockDepartmentEmployees(param);
        mockData.forEach(e -> e.setEmployeeName("myBatisPlusUpdate 更新后的部门名字"));
        departmentEmployeesService.updateBatchById(mockData);
    }

    @Benchmark
    public void jpaUpdate() {
        List<DepartmentEmployees> mockData = getMockDepartmentEmployees(param);
        mockData.forEach(e -> e.setEmployeeName("jpaUpdate 更新后的部门名字"));
//        departmentEmployeesRepository.saveAllAndFlush(mockData);
    }

    @Benchmark
    public void singleFieldUpdate() {
        List<DepartmentEmployees> mockData = getMockDepartmentEmployees(param);
        List<Long> ids = mockData.stream().map(DepartmentEmployees::getId).collect(Collectors.toList());
        singleFieldUpdate.batchUpdateSingle(ids, "batchUpdateSingle 更新后的部门名字");

    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(BatchUpdateBenchmark.class.getSimpleName())
                .result(LocalDateTime.now() + "BatchUpdate_100000_LOCAL_V2.json")
                .resultFormat(ResultFormatType.JSON).build();
        new Runner(opt).run();
    }
}
