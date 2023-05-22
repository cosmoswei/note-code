package com.wei;

import com.alibaba.fastjson.JSON;
import com.wei.entity.DepartmentEmployees;
import com.wei.mapper.DepartmentEmployeesMapper;
import com.wei.mapper.DepartmentEmployeesRepository;
import com.wei.service.DepartmentEmployeesService;
import com.wei.service.impl.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@SpringBootTest
@Slf4j
class AppTests {

    @Resource
    private BatchDemo batchDemo;
    @Resource
    private ForeachDemo foreachDemo;
    @Resource
    private InitialDemo initialDemo;
    @Resource
    private CaseWhenDemo caseWhenDemo;
    @Resource
    private BatchUpdateSingleDemo batchUpdateSingle;
    @Resource
    private DepartmentEmployeesMapper departmentEmployeesMapper;
    @Resource
    private DepartmentEmployeesService departmentEmployeesService;
    @Resource
    private DepartmentEmployeesRepository departmentEmployeesRepository;

    private final static Long batchSize = 10000L;

    @Test
    void queryEmployee() {
        long start = System.currentTimeMillis();
        List<Long> ids = LongStream.range(0, batchSize).boxed().collect(Collectors.toList());
        List<DepartmentEmployees> result = departmentEmployeesMapper.selectByIds(ids);
        System.out.println(result);
        long end = System.currentTimeMillis();
        System.out.println("=锚点=" + (end - start));
    }

    private static List<DepartmentEmployees> getMockData(Long batchSize) {
        return LongStream.range(0, batchSize).boxed().map(String::valueOf).map(Integer::valueOf).map(e -> {
            DepartmentEmployees departmentEmployees = new DepartmentEmployees();
            departmentEmployees.setId(e);
            departmentEmployees.setDepartmentId(0);
            departmentEmployees.setEmployeeName("");
            departmentEmployees.setEmployeeTitle("");
            departmentEmployees.setEmployeeSalary(new BigDecimal("0"));
            departmentEmployees.setEmployeeAge(0);
            departmentEmployees.setEmployeeGender("");
            departmentEmployees.setEmployeeAddress("");
            departmentEmployees.setEmployeePhone("");
            departmentEmployees.setEmployeeEmail("");
            departmentEmployees.setEmployeeStartDate(new Date());
            departmentEmployees.setEmployeeEndDate(new Date());
            departmentEmployees.setEmployeeStatus("");
            departmentEmployees.setEmployeePerformance("");
            departmentEmployees.setEmployeeNotes("");
            departmentEmployees.setId(e);
            return departmentEmployees;
        }).collect(Collectors.toList());
    }

    @Test
    void updateEmployeePlus() {
        List<Long> ids = LongStream.range(0, batchSize).boxed().collect(Collectors.toList());
        List<DepartmentEmployees> result = departmentEmployeesMapper.selectByIds(ids);
        result.forEach(e -> e.setEmployeeName("updateEmployeePlus 更新后的部门名字"));
        departmentEmployeesService.updateBatchById(result);
    }

    @Test
    void updateEmployeeCaseWhen() {
        List<DepartmentEmployees> result = getMockData(100L);
        result.forEach(e -> e.setEmployeeName("updateEmployeeCaseWhen 更新后的部门名字"));
        caseWhenDemo.caseWhenUpdate(result);
    }

    @Test
    void updateEmployeeBatch() {
        List<Long> ids = LongStream.range(0, batchSize).boxed().collect(Collectors.toList());
        List<DepartmentEmployees> result = departmentEmployeesMapper.selectByIds(ids);
        result.forEach(e -> e.setEmployeeName("updateEmployeeBatch 更新后的部门名字"));
        batchDemo.batchUpdate(result);
    }

    @Test
    void updateEmployeeInitial() {
        List<Long> ids = LongStream.range(0, batchSize).boxed().collect(Collectors.toList());
        List<DepartmentEmployees> result = departmentEmployeesMapper.selectByIds(ids);
        result.forEach(e -> e.setEmployeeName("updateEmployeeInitial 更新后的部门名字"));
        initialDemo.initialUpdate(result);
    }

    @Test
    void updateEmployeeForeach() {
        List<Long> ids = LongStream.range(0, batchSize).boxed().collect(Collectors.toList());
        List<DepartmentEmployees> result = departmentEmployeesMapper.selectByIds(ids);
        result.forEach(e -> e.setEmployeeName("updateEmployeeBatch 更新后的部门名字"));
        foreachDemo.foreachUpdate(result);
    }

    @Test
    void saveAllAndFlush() {
        List<Long> ids = LongStream.range(1, batchSize).boxed().collect(Collectors.toList());
        List<DepartmentEmployees> allById = departmentEmployeesMapper.selectByIds(ids);
        allById.forEach(e -> e.setEmployeeName("saveAllAndFlush 更新后的部门名字"));
        List<DepartmentEmployees> departmentEmployees = departmentEmployeesRepository.saveAllAndFlush(allById);
        log.info("banner exists: {}, banner info: {}", departmentEmployees.size(), JSON.toJSON(departmentEmployees));
    }

    @Test
    void batchUpdateSingle() {
        List<Long> ids = LongStream.range(1, 100000L).boxed().collect(Collectors.toList());
        batchUpdateSingle.batchUpdateSingle(ids, "batchUpdateSingle 更新后的部门名字");
    }
}
