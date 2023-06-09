package com.wei.mock;

import com.wei.entity.DepartmentEmployees;
import com.wei.entity.DepartmentEmployeesSimple;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class MockUtils {

    private MockUtils() {
    }

    public static List<DepartmentEmployees> getMockDepartmentEmployees(Integer batchSize) {
        return LongStream.range(0, batchSize).boxed().map(e -> {
            DepartmentEmployees departmentEmployees = new DepartmentEmployees();
            departmentEmployees.setId(e);
            departmentEmployees.setDepartmentId(0);
            departmentEmployees.setEmployeeName(e + "111");
            departmentEmployees.setEmployeeTitle(e + "222");
            departmentEmployees.setEmployeeSalary(new BigDecimal("9999"));
            departmentEmployees.setEmployeeAge(0);
            departmentEmployees.setEmployeeGender(e + "333");
            departmentEmployees.setEmployeeAddress(e + "444");
            departmentEmployees.setEmployeePhone(e + "555");
            departmentEmployees.setEmployeeEmail(e + "666");
            departmentEmployees.setEmployeeStartDate(new Date());
            departmentEmployees.setEmployeeEndDate(new Date());
            departmentEmployees.setEmployeeStatus(e + "777");
            departmentEmployees.setEmployeePerformance(e + "888");
            departmentEmployees.setEmployeeNotes(e + "999");
            return departmentEmployees;
        }).collect(Collectors.toList());
    }

    public static List<DepartmentEmployeesSimple> getMockDepartmentEmployeesSimple(Integer batchSize) {
        return LongStream.range(0, batchSize).boxed().map(e -> {
            DepartmentEmployeesSimple departmentEmployeesSimple = new DepartmentEmployeesSimple();
            departmentEmployeesSimple.setId(e);
            departmentEmployeesSimple.setDepartmentId(0);
            departmentEmployeesSimple.setEmployeeName(e + "111");
            return departmentEmployeesSimple;
        }).collect(Collectors.toList());
    }
}
