package com.wei.service.simple.impl;

import com.wei.entity.DepartmentEmployeesSimple;
import com.wei.mapper.DepartmentEmployeesSimpleMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Service("simpleForeachUpdate")
public class ForeachUpdate {

    @Resource
    private DepartmentEmployeesSimpleMapper departmentEmployeesSimpleMapper;

    public void foreachUpdate(List<DepartmentEmployeesSimple> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        list.forEach(departmentEmployeesSimpleMapper::updateByPrimaryKey);
    }
}
