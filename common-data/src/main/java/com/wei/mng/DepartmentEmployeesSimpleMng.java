package com.wei.mng;

import com.wei.datasource.DataSource;
import com.wei.entity.DepartmentEmployeesSimple;
import com.wei.mapper.DepartmentEmployeesSimpleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentEmployeesSimpleMng {

    @Resource
    private DepartmentEmployeesSimpleMapper departmentEmployeesSimpleMapper;

    @DataSource("ds1")
    public DepartmentEmployeesSimple getMasterById(Integer id) {
        return departmentEmployeesSimpleMapper.selectByPrimaryKey(Long.valueOf(id));
    }

    @DataSource("ds2")
    public DepartmentEmployeesSimple getSalveById(Integer id) {
        return departmentEmployeesSimpleMapper.selectByPrimaryKey(Long.valueOf(id));
    }

    @DataSource("ds1")
    public void updateMaster(Integer id) {
        DepartmentEmployeesSimple departmentEmployeesSimple = departmentEmployeesSimpleMapper.selectByPrimaryKey(Long.valueOf(id));
        departmentEmployeesSimple.setEmployeeName("updateMaster");
        departmentEmployeesSimpleMapper.updateByPrimaryKey(departmentEmployeesSimple);
    }

    @DataSource("ds2")
    public void updateSalve(Integer id) {
        DepartmentEmployeesSimple departmentEmployeesSimple = departmentEmployeesSimpleMapper.selectByPrimaryKey(Long.valueOf(id));
        departmentEmployeesSimple.setEmployeeName("updateSalve");
        departmentEmployeesSimpleMapper.updateByPrimaryKey(departmentEmployeesSimple);
    }


    public List<DepartmentEmployeesSimple> getAllById(Integer id) {
        DepartmentEmployeesSimple masterById = getMasterById(id);
        DepartmentEmployeesSimple salveById = getSalveById(id);
        List<DepartmentEmployeesSimple> list = new ArrayList<>();
        list.add(masterById);
        list.add(salveById);
        return list;
    }
}
