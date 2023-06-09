package com.wei.orm;

import com.wei.DataAppRun;
import com.wei.entity.DepartmentEmployees;
import com.wei.mapper.DepartmentEmployeesRepository;
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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

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
    private DepartmentEmployeesRepository departmentEmployeesRepository;

    @Param(value = {"10", "100", "1000", "10000"})
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
        this.departmentEmployeesRepository = (DepartmentEmployeesRepository) context.getBean("departmentEmployeesRepository");
    }

    @TearDown
    public void down() {
        context.close();
    }

    @Benchmark
    public void batchExecutorUpdate() {
        List<DepartmentEmployees> mockData = getMockData(param);
        mockData.forEach(e -> e.setEmployeeName("batchExecutorUpdate 更新后的部门名字"));
        batchExecutorUpdate.initialUpdate(mockData);
    }

    @Benchmark
    public void caseWhenUpdate() {
        List<DepartmentEmployees> mockData = getMockData(param);
        mockData.forEach(e -> e.setEmployeeName("caseWhenUpdate 更新后的部门名字"));
        caseWhenUpdate.caseWhenUpdate(mockData);
    }

    @Benchmark
    public void foreachUpdate() {
        List<DepartmentEmployees> mockData = getMockData(param);
        mockData.forEach(e -> e.setEmployeeName("foreachUpdate 更新后的部门名字"));
        foreachUpdate.foreachUpdate(mockData);
    }

    @Benchmark
    public void foreachXmlUpdate() {
        List<DepartmentEmployees> mockData = getMockData(param);
        mockData.forEach(e -> e.setEmployeeName("foreachXmlUpdate 更新后的部门名字"));
        foreachXmlUpdate.batchUpdate(mockData);
    }

    @Benchmark
    public void myBatisPlusUpdate() {
        List<DepartmentEmployees> mockData = getMockData(param);
        mockData.forEach(e -> e.setEmployeeName("myBatisPlusUpdate 更新后的部门名字"));
        departmentEmployeesService.updateBatchById(mockData);
    }

    @Benchmark
    public void jpaUpdate() {
        List<DepartmentEmployees> mockData = getMockData(param);
        mockData.forEach(e -> e.setEmployeeName("jpaUpdate 更新后的部门名字"));
        departmentEmployeesRepository.saveAllAndFlush(mockData);
    }

    @Benchmark
    public void singleFieldUpdate() {
        List<DepartmentEmployees> mockData = getMockData(param);
        List<Long> ids = mockData.stream().map(DepartmentEmployees::getId).collect(Collectors.toList());
        singleFieldUpdate.batchUpdateSingle(ids, "batchUpdateSingle 更新后的部门名字");

    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(BatchUpdateBenchmark.class.getSimpleName())
                .result(LocalDateTime.now() + "BatchUpdateBenchmark_100000_10_new_RC.json")
                .resultFormat(ResultFormatType.JSON).build();
        new Runner(opt).run();
    }


    private static List<DepartmentEmployees> getMockData(Integer batchSize) {
        return LongStream.range(0, batchSize).boxed().map(e -> {
            DepartmentEmployees departmentEmployees = new DepartmentEmployees();
            departmentEmployees.setId(e);
            departmentEmployees.setDepartmentId(0);
            departmentEmployees.setEmployeeName(e + "111");
            departmentEmployees.setEmployeeTitle(e + "222");
            departmentEmployees.setEmployeeSalary(new BigDecimal("9999"));
            departmentEmployees.setEmployeeAge(0);
            departmentEmployees.setEmployeeGender(e + "333");
            departmentEmployees.setEmployeeAddress(e + "444");
            departmentEmployees.setEmployeePhone(e + "555");
            departmentEmployees.setEmployeeEmail(e + "666");
            departmentEmployees.setEmployeeStartDate(new Date());
            departmentEmployees.setEmployeeEndDate(new Date());
            departmentEmployees.setEmployeeStatus(e + "777");
            departmentEmployees.setEmployeePerformance(e + "888");
            departmentEmployees.setEmployeeNotes(e + "999");
            return departmentEmployees;
        }).collect(Collectors.toList());
    }
}
