package com.wei.service.simple;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wei.entity.DepartmentEmployeesSimple;

public interface DepartmentEmployeesSimpleService extends IService<DepartmentEmployeesSimple> {


    DepartmentEmployeesSimple getById(Integer id);
}
