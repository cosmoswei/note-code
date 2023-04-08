package com.wei.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wei.entity.DepartmentEmployees;

public interface DepartmentEmployeesService extends IService<DepartmentEmployees> {


    DepartmentEmployees getById(Integer id);
}
