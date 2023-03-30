package com.wei.service.impl;

import com.wei.entity.DepartmentEmployees;
import com.wei.mapper.DepartmentEmployeesMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ForeachDemo {

    @Resource
    DepartmentEmployeesMapper departmentEmployeesMapper;

    public void foreachUpdate(List<DepartmentEmployees> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        list.forEach(departmentEmployeesMapper::updateByPrimaryKey);
    }
}
