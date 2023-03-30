package com.wei.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wei.entity.DepartmentEmployees;
import com.wei.mapper.DepartmentEmployeesMapper;
import com.wei.service.DepartmentEmployeesService;
import org.springframework.stereotype.Service;

@Service("departmentEmployeesService")
public class DepartmentEmployeesServiceImpl
        extends ServiceImpl<DepartmentEmployeesMapper, DepartmentEmployees>
        implements DepartmentEmployeesService {

}
