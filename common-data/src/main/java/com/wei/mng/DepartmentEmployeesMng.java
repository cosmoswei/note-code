package com.wei.mng;

import com.wei.datasource.DataSource;
import com.wei.entity.DepartmentEmployees;
import com.wei.mapper.DepartmentEmployeesMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentEmployeesMng {

    @Resource
    private DepartmentEmployeesMapper departmentEmployeesMapper;

    @DataSource("ds1")
    public DepartmentEmployees getMasterById(Integer id) {
        return departmentEmployeesMapper.selectByPrimaryKey(Long.valueOf(id));
    }

    @DataSource("ds2")
    public DepartmentEmployees getSalveById(Integer id) {
        return departmentEmployeesMapper.selectByPrimaryKey(Long.valueOf(id));
    }

    @DataSource("ds1")
    public void updateMaster(Integer id) {
        DepartmentEmployees departmentEmployees = departmentEmployeesMapper.selectByPrimaryKey(Long.valueOf(id));
        departmentEmployees.setEmployeeName("updateMaster");
        departmentEmployeesMapper.updateByPrimaryKey(departmentEmployees);
    }

    @DataSource("ds2")
    public void updateSalve(Integer id) {
        DepartmentEmployees departmentEmployees = departmentEmployeesMapper.selectByPrimaryKey(Long.valueOf(id));
        departmentEmployees.setEmployeeName("updateSalve");
        departmentEmployeesMapper.updateByPrimaryKey(departmentEmployees);
    }


    public List<DepartmentEmployees> getAllById(Integer id) {
        DepartmentEmployees masterById = getMasterById(id);
        DepartmentEmployees salveById = getSalveById(id);
        List<DepartmentEmployees> list = new ArrayList<>();
        list.add(masterById);
        list.add(salveById);
        return list;
    }
}
