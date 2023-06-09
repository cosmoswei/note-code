package com.wei.service.simple.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wei.datasource.DataSource;
import com.wei.entity.DepartmentEmployeesSimple;
import com.wei.mapper.DepartmentEmployeesSimpleMapper;
import com.wei.service.simple.DepartmentEmployeesSimpleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("departmentEmployeesSimpleService")
public class DepartmentEmployeesSimpleServiceImpl
        extends ServiceImpl<DepartmentEmployeesSimpleMapper, DepartmentEmployeesSimple>
        implements DepartmentEmployeesSimpleService {

    @Resource
    private DepartmentEmployeesSimpleMapper departmentEmployeesSimpleMapper;

    @Override
    @DataSource("ds2")
    public DepartmentEmployeesSimple getById(Integer id) {
        return departmentEmployeesSimpleMapper.selectByPrimaryKey(Long.valueOf(id));
    }
}
