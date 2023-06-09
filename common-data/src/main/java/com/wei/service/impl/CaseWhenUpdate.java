package com.wei.service.impl;

import com.wei.entity.DepartmentEmployees;
import com.wei.mapper.DepartmentEmployeesMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CaseWhenUpdate {
    @Resource
    private DepartmentEmployeesMapper departmentEmployeesMapper;

    public void caseWhenUpdate(List<DepartmentEmployees> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        departmentEmployeesMapper.caseWhenUpdate(list);
    }
}
