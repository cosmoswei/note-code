package com.wei.service.impl;

import com.wei.entity.DepartmentEmployees;
import com.wei.mapper.DepartmentEmployeesMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ForeachXmlUpdate {

    @Resource
    private DepartmentEmployeesMapper departmentEmployeesMapper;

    public void batchUpdate(List<DepartmentEmployees> list) {
        departmentEmployeesMapper.batchUpdate(list);
    }
}
