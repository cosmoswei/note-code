package com.wei.service.impl;

import com.wei.mapper.DepartmentEmployeesMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BatchUpdateSingleDemo {


    @Resource
    private DepartmentEmployeesMapper departmentEmployeesMapper;

    public void batchUpdateSingle(List<Long> ids, String employeeName) {
        departmentEmployeesMapper.batchUpdateSingle(ids, employeeName);
    }
}
