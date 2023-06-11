package com.wei.service.impl;

import com.wei.entity.DepartmentEmployees;
import com.wei.mapper.DepartmentEmployeesMapper;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CaseWhenUpdate {
    @Resource
    private DepartmentEmployeesMapper departmentEmployeesMapper;

    public void caseWhenUpdate(List<DepartmentEmployees> list) {
        List<List<DepartmentEmployees>> partition = ListUtils.partition(list, 10000);
        for (List<DepartmentEmployees> departmentEmployees : partition) {
            departmentEmployeesMapper.caseWhenUpdate(departmentEmployees);
        }
    }
}
