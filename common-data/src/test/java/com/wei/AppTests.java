package com.wei;

import com.alibaba.fastjson.JSON;
import com.wei.entity.DepartmentEmployees;
import com.wei.mapper.DepartmentEmployeesMapper;
import com.wei.mapper.DepartmentEmployeesRepository;
import com.wei.service.DepartmentEmployeesService;
import com.wei.service.impl.BatchDemo;
import com.wei.service.impl.CaseWhenDemo;
import com.wei.service.impl.ForeachDemo;
import com.wei.service.impl.InitialDemo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

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
    private DepartmentEmployeesMapper departmentEmployeesMapper;
    @Resource
    private DepartmentEmployeesService departmentEmployeesService;
    @Resource
    private DepartmentEmployeesRepository departmentEmployeesRepository;

    private final static Long batchSize = 10L;

    @Test
    void queryEmployee() {
        List<Long> ids = Stream.of(1L, 2L, 4L, 5L, 6L).collect(Collectors.toList());
        List<DepartmentEmployees> result = departmentEmployeesMapper.selectByIds(ids);
        System.out.println(result);
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
        List<Long> ids = LongStream.range(1, batchSize).boxed().collect(Collectors.toList());
        List<DepartmentEmployees> result = departmentEmployeesMapper.selectByIds(ids);
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
}
