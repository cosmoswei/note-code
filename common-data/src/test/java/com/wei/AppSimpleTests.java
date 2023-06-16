package com.wei;


import com.wei.entity.DepartmentEmployeesSimple;
import com.wei.mapper.DepartmentEmployeesSimpleMapper;
import com.wei.service.simple.DepartmentEmployeesSimpleService;
import com.wei.service.simple.impl.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static com.wei.mock.MockUtils.getMockDepartmentEmployeesSimple;

@SpringBootTest
@Slf4j
class AppSimpleTests {

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
    private DepartmentEmployeesSimpleMapper DepartmentEmployeesSimpleMapper;
    @Resource
    private DepartmentEmployeesSimpleService departmentEmployeesSimpleService;
//    @Resource
//    private DepartmentEmployeesSimpleRepository departmentEmployeesSimpleRepository;

    private final static Integer batchSize = 300000;

    @Test
    void queryEmployee() {
        long start = System.currentTimeMillis();
        List<Long> ids = LongStream.range(0, batchSize).boxed().collect(Collectors.toList());
        List<DepartmentEmployeesSimple> result = DepartmentEmployeesSimpleMapper.selectByIds(ids);
        System.out.println(result);
        long end = System.currentTimeMillis();
        System.out.println("=锚点=" + (end - start));
    }


    @Test
    void myBatisPluUpdate() {
        List<DepartmentEmployeesSimple> result = getMockDepartmentEmployeesSimple(batchSize);
        result.forEach(e -> e.setEmployeeName("myBatisPluUpdate 更新后的部门名字"));
        departmentEmployeesSimpleService.updateBatchById(result);
    }

    @Test
    void caseWhenUpdate() {
        List<DepartmentEmployeesSimple> result = getMockDepartmentEmployeesSimple(batchSize);
        result.forEach(e -> e.setEmployeeName("caseWhenUpdate 更新后的部门名字"));
        caseWhenDemo.caseWhenUpdate(result);
    }

    @Test
    void foreachXmlUpdate() {
        List<DepartmentEmployeesSimple> result = getMockDepartmentEmployeesSimple(batchSize);
        result.forEach(e -> e.setEmployeeName("foreachXmlUpdate 更新后的部门名字"));
        batchDemo.batchUpdate(result);
    }

    @Test
    void batchExecutorUpdate() {
        List<DepartmentEmployeesSimple> result = getMockDepartmentEmployeesSimple(batchSize);
        result.forEach(e -> e.setEmployeeName("batchExecutorUpdate 更新后的部门名字"));
        initialDemo.batchExecutorUpdate(result);
    }

    @Test
    void foreachUpdate() {
        List<DepartmentEmployeesSimple> result = getMockDepartmentEmployeesSimple(batchSize);
        result.forEach(e -> e.setEmployeeName("foreachUpdate 更新后的部门名字"));
        foreachDemo.foreachUpdate(result);
    }

    @Test
    void jpaUpdate() {
        List<DepartmentEmployeesSimple> result = getMockDepartmentEmployeesSimple(batchSize);
        result.forEach(e -> e.setEmployeeName("saveAllAndFlush 更新后的部门名字"));
//        departmentEmployeesSimpleRepository.saveAllAndFlush(result);
    }

    @Test
    void singleFieldUpdate() {
        List<DepartmentEmployeesSimple> result = getMockDepartmentEmployeesSimple(batchSize);
        List<Long> ids = result.stream().map(DepartmentEmployeesSimple::getId).collect(Collectors.toList());
        batchUpdateSingle.batchUpdateSingle(ids, "batchUpdateSingle 更新后的部门名字");
    }


    @Test
    void test() {
        long start = System.currentTimeMillis();
        myBatisPluUpdate();
        long step1 = System.currentTimeMillis();
        caseWhenUpdate();
        long step2 = System.currentTimeMillis();
        foreachXmlUpdate();
        long step3 = System.currentTimeMillis();
        batchExecutorUpdate();
        long step4 = System.currentTimeMillis();
        foreachUpdate();
        long step5 = System.currentTimeMillis();
        jpaUpdate();
        long step6 = System.currentTimeMillis();
        singleFieldUpdate();
        long step7 = System.currentTimeMillis();
        System.out.println(batchSize + " || myBatisPluUpdate = " + (step1 - start));
        System.out.println(batchSize + " || caseWhenUpdate = " + (step2 - step1));
        System.out.println(batchSize + " || foreachXmlUpdate = " + (step3 - step2));
        System.out.println(batchSize + " || batchExecutorUpdate = " + (step4 - step3));
        System.out.println(batchSize + " || foreachUpdate = " + (step5 - step4));
        System.out.println(batchSize + " || jpaUpdate = " + (step6 - step5));
        System.out.println(batchSize + " || singleFieldUpdate = " + (step7 - step6));
    }
}
