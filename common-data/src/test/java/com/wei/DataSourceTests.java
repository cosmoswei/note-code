package com.wei;

import com.alibaba.fastjson.JSON;
import com.wei.entity.DepartmentEmployees;
import com.wei.mng.DepartmentEmployeesMng;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
class DataSourceTests {

    @Resource
    private DepartmentEmployeesMng departmentEmployeesMng;

    @Test
    void dataSourceTest() {
        DepartmentEmployees masterById = departmentEmployeesMng.getMasterById(1);
        DepartmentEmployees salveById = departmentEmployeesMng.getSalveById(1);
        List<DepartmentEmployees> list = new ArrayList<>();
        list.add(masterById);
        list.add(salveById);
        log.info(JSON.toJSONString(list));
        departmentEmployeesMng.updateMaster(1);
        departmentEmployeesMng.updateSalve(1);
        System.out.println("dataSourceTest===");
    }
}
