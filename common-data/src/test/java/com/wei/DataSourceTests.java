package com.wei;

import com.wei.entity.DepartmentEmployees;
import com.wei.mng.DepartmentEmployeesMng;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
class DataSourceTests {

    @Resource
    private DepartmentEmployeesMng departmentEmployeesMng;

    /**
     * 情况1：没加 Transactional 注解，查询打印正常；中间抛出异常，事物没有失效。
     * 情况2：加了 @Transactional 注解，查询异常（全部查询主库），且没有异常却将事物回滚，还有造成了 sout 输出了更新后的值，数据库却没有更新的现象。
     */

    @Test
    void dataSourceTestOne() {
        DepartmentEmployees masterById = departmentEmployeesMng.getMasterById(1);
        DepartmentEmployees salveById = departmentEmployeesMng.getSalveById(1);
        List<DepartmentEmployees> list = new ArrayList<>();
        list.add(masterById);
        list.add(salveById);
        list.forEach(System.out::println);
        departmentEmployeesMng.updateMaster(1);
        departmentEmployeesMng.updateSalve(1);
        list.forEach(System.out::println);
    }

    @Test
    @Transactional(rollbackOn = Exception.class)
    void dataSourceTestTwo() {
        DepartmentEmployees masterById = departmentEmployeesMng.getMasterById(1);
        DepartmentEmployees salveById = departmentEmployeesMng.getSalveById(1);
        List<DepartmentEmployees> list = new ArrayList<>();
        list.add(masterById);
        list.add(salveById);
        list.forEach(System.out::println);
        departmentEmployeesMng.updateMaster(1);
        departmentEmployeesMng.updateSalve(1);
        list.forEach(System.out::println);
    }

    @Test
    void dataSourceTestThree() {
        departmentEmployeesMng.updateComposeById(1);
    }


    @Test
    void dataSourceTestFour() {
        departmentEmployeesMng.updateById(1);
    }
}
