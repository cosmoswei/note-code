package com.wei;

import com.wei.entity.DepartmentEmployees;
import com.wei.mapper.DepartmentEmployeesMapper;
import com.wei.service.DepartmentEmployeesService;
import com.wei.service.impl.BatchDemo;
import com.wei.service.impl.CaseThenDemo;
import com.wei.service.impl.ForeachDemo;
import com.wei.service.impl.InitialDemo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@SpringBootTest
class AppTests {

    @Resource
    private BatchDemo batchDemo;
    @Resource
    private ForeachDemo foreachDemo;
    @Resource
    private InitialDemo initialDemo;
    @Resource
    private CaseThenDemo caseThenDemo;
    @Resource
    private DepartmentEmployeesMapper departmentEmployeesMapper;
    @Resource
    private DepartmentEmployeesService departmentEmployeesService;

    @Test
    void queryEmployee() {
        List<Long> ids = Stream.of(1L, 2L, 4L, 5L, 6L).collect(Collectors.toList());
        List<DepartmentEmployees> result = departmentEmployeesMapper.selectByIds(ids);
        System.out.println(result);
    }

    @Test
    void updateEmployeePlus() {
        List<Long> ids = LongStream.range(0, 1000).boxed().collect(Collectors.toList());
        List<DepartmentEmployees> result = departmentEmployeesMapper.selectByIds(ids);
        result.forEach(e -> e.setEmployeeName("updateEmployeePlus 更新后的部门名字"));
        boolean b = departmentEmployeesService.updateBatchById(result);
        System.out.println(b);
    }

    @Test
    void updateEmployeeCaseWhen() {
        List<Long> ids = LongStream.range(0, 1000).boxed().collect(Collectors.toList());
        List<DepartmentEmployees> result = departmentEmployeesMapper.selectByIds(ids);
        result.forEach(e -> e.setEmployeeName("updateEmployeeCaseThen 更新后的部门名字"));
        caseThenDemo.caseWhenUpdate(result);
    }

    @Test
    void updateEmployeeBatch() {
        List<Long> ids = LongStream.range(0, 1000).boxed().collect(Collectors.toList());
        List<DepartmentEmployees> result = departmentEmployeesMapper.selectByIds(ids);
        result.forEach(e -> e.setEmployeeName("updateEmployeeBatch 更新后的部门名字"));
        batchDemo.batchUpdate(result);
    }

    @Test
    void updateEmployeeInitial() {
        List<Long> ids = LongStream.range(0, 1000).boxed().collect(Collectors.toList());
        List<DepartmentEmployees> result = departmentEmployeesMapper.selectByIds(ids);
        result.forEach(e -> e.setEmployeeName("updateEmployeeInitial 更新后的部门名字"));
        initialDemo.initialUpdate(result);
    }

    @Test
    void updateEmployeeForeach() {
        List<Long> ids = LongStream.range(0, 1000).boxed().collect(Collectors.toList());
        List<DepartmentEmployees> result = departmentEmployeesMapper.selectByIds(ids);
        result.forEach(e -> e.setEmployeeName("updateEmployeeBatch 更新后的部门名字"));
        foreachDemo.foreachUpdate(result);
    }
}
