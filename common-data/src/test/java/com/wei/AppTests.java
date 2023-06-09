package com.wei;

import com.wei.entity.DepartmentEmployees;
import com.wei.mapper.DepartmentEmployeesMapper;
import com.wei.mapper.DepartmentEmployeesRepository;
import com.wei.service.DepartmentEmployeesService;
import com.wei.service.impl.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static com.wei.mock.MockUtils.getMockDepartmentEmployees;

@SpringBootTest
@Slf4j
class AppTests {

    @Resource
    private ForeachXmlUpdate batchDemo;
    @Resource
    private ForeachUpdate foreachDemo;
    @Resource
    private CaseWhenUpdate caseWhenDemo;
    @Resource
    private BatchExecutorUpdate initialDemo;
    @Resource
    private SingleFieldUpdate batchUpdateSingle;
    @Resource
    private DepartmentEmployeesMapper departmentEmployeesMapper;
    @Resource
    private DepartmentEmployeesService departmentEmployeesService;
    @Resource
    private DepartmentEmployeesRepository departmentEmployeesRepository;

    private final static Integer batchSize = 1000000;

    @Test
    void queryEmployee() {
        long start = System.currentTimeMillis();
        List<Long> ids = LongStream.range(0, batchSize).boxed().collect(Collectors.toList());
        List<DepartmentEmployees> result = departmentEmployeesMapper.selectByIds(ids);
        System.out.println(result);
        long end = System.currentTimeMillis();
        System.out.println("=锚点=" + (end - start));
    }


    @Test
    void myBatisPluUpdate() {
        List<DepartmentEmployees> result = getMockDepartmentEmployees(batchSize);
        result.forEach(e -> e.setEmployeeName("myBatisPluUpdate 更新后的部门名字"));
        departmentEmployeesService.updateBatchById(result);
    }

    @Test
    void caseWhenUpdate() {
        List<DepartmentEmployees> result = getMockDepartmentEmployees(batchSize);
        result.forEach(e -> e.setEmployeeName("caseWhenUpdate 更新后的部门名字"));
        caseWhenDemo.caseWhenUpdate(result);
    }

    @Test
    void foreachXmlUpdate() {
        List<DepartmentEmployees> result = getMockDepartmentEmployees(batchSize);
        result.forEach(e -> e.setEmployeeName("foreachXmlUpdate 更新后的部门名字"));
        batchDemo.batchUpdate(result);
    }

    @Test
    void batchExecutorUpdate() {
        List<DepartmentEmployees> result = getMockDepartmentEmployees(batchSize);
        result.forEach(e -> e.setEmployeeName("batchExecutorUpdate 更新后的部门名字"));
        initialDemo.batchExecutorUpdate(result);
    }

    @Test
    void foreachUpdate() {
        List<DepartmentEmployees> result = getMockDepartmentEmployees(batchSize);
        result.forEach(e -> e.setEmployeeName("foreachUpdate 更新后的部门名字"));
        foreachDemo.foreachUpdate(result);
    }

    @Test
    void jpaUpdate() {
        List<DepartmentEmployees> result = getMockDepartmentEmployees(batchSize);
        result.forEach(e -> e.setEmployeeName("saveAllAndFlush 更新后的部门名字"));
        departmentEmployeesRepository.saveAllAndFlush(result);
    }

    @Test
    void batchUpdateSingle() {
        List<DepartmentEmployees> result = getMockDepartmentEmployees(batchSize);
        List<Long> ids = result.stream().map(DepartmentEmployees::getId).collect(Collectors.toList());
        batchUpdateSingle.batchUpdateSingle(ids, "batchUpdateSingle 更新后的部门名字");
    }


    @Test
    void test() {
        long start = System.currentTimeMillis();
//        foreachXmlUpdate();
        long mid = System.currentTimeMillis();
        batchExecutorUpdate();
        long end = System.currentTimeMillis();
        System.out.println("foreachXmlUpdate = " + (mid - start));
        System.out.println("batchExecutorUpdate = " + (end - mid));

    }
}
