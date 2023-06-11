package com.wei.service.impl;

import com.wei.entity.DepartmentEmployees;
import com.wei.mapper.DepartmentEmployeesMapper;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ForeachXmlUpdate {

    @Resource
    private DepartmentEmployeesMapper departmentEmployeesMapper;

    public void batchUpdate(List<DepartmentEmployees> list) {
        List<List<DepartmentEmployees>> partition = ListUtils.partition(list, 10000);
        for (List<DepartmentEmployees> departmentEmployees : partition) {
            departmentEmployeesMapper.batchUpdate(departmentEmployees);
        }
    }
}
