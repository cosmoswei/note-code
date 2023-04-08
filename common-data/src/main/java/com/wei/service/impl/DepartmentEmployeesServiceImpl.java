package com.wei.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wei.datasource.DataSource;
import com.wei.entity.DepartmentEmployees;
import com.wei.mapper.DepartmentEmployeesMapper;
import com.wei.service.DepartmentEmployeesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("departmentEmployeesService")
public class DepartmentEmployeesServiceImpl
        extends ServiceImpl<DepartmentEmployeesMapper, DepartmentEmployees>
        implements DepartmentEmployeesService {

    @Resource
    private DepartmentEmployeesMapper departmentEmployeesMapper;

    @Override
    @DataSource("ds2")
    public DepartmentEmployees getById(Integer id) {
        return departmentEmployeesMapper.selectByPrimaryKey(Long.valueOf(id));
    }
}
