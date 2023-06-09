package com.wei.service.simple.impl;

import com.wei.entity.DepartmentEmployeesSimple;
import com.wei.mapper.DepartmentEmployeesSimpleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("simpleForeachXmlUpdate")
public class ForeachXmlUpdate {

    @Resource
    private DepartmentEmployeesSimpleMapper departmentEmployeesSimpleMapper;

    public void batchUpdate(List<DepartmentEmployeesSimple> list) {
        departmentEmployeesSimpleMapper.batchUpdate(list);
    }
}
