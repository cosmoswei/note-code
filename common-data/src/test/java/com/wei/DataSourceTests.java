package com.wei;

import com.wei.entity.DepartmentEmployees;
import com.wei.mng.DepartmentEmployeesMng;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
class DataSourceTests {

    @Resource
    private DepartmentEmployeesMng departmentEmployeesMng;

    /**
     * 情况1：没加 @Transactional 注解，未抛异常；数据源切换生效。
     * 情况2：没加 @Transactional 注解，抛出异常；事物生效，数据源切换生效。，
     * 情况3：加了 @Transactional 注解，未抛异常；数据源切换失效。
     * 情况4：加了 @Transactional 注解，抛出异常；事物生效；数据源切换失效，查询异常（全部查询主库），且没有异常却将事物回滚，还有造成了 sout 输出了更新后的值，数据库却没有更新的现象。
     * <p>
     * 解答：就是在在使用事物时切换数据源会导致事物内全部的SQL请求走默认的数据库连接？不是说好的加了事物也能切换数据源吗？
     * </p>
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
