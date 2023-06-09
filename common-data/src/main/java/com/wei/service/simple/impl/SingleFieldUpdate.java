package com.wei.service.simple.impl;

import com.wei.mapper.DepartmentEmployeesSimpleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("simpleSingleFieldUpdate")
public class SingleFieldUpdate {


    @Resource
    private DepartmentEmployeesSimpleMapper departmentEmployeesSimpleMapper;

    public void batchUpdateSingle(List<Long> ids, String employeeName) {
        departmentEmployeesSimpleMapper.batchUpdateSingle(ids, employeeName);
    }
}
