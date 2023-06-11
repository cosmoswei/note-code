package com.wei.service.simple.impl;

import com.wei.entity.DepartmentEmployeesSimple;
import com.wei.mapper.DepartmentEmployeesSimpleMapper;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("simpleForeachXmlUpdate")
public class ForeachXmlUpdate {

    @Resource
    private DepartmentEmployeesSimpleMapper departmentEmployeesSimpleMapper;

    public void batchUpdate(List<DepartmentEmployeesSimple> list) {
        List<List<DepartmentEmployeesSimple>> partition = ListUtils.partition(list, 10000);
        for (List<DepartmentEmployeesSimple> departmentEmployees : partition) {
            departmentEmployeesSimpleMapper.batchUpdate(departmentEmployees);
        }
    }
}
