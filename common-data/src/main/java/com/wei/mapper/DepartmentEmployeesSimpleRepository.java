package com.wei.mapper;

import com.wei.entity.DepartmentEmployeesSimple;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentEmployeesSimpleRepository extends JpaRepository<DepartmentEmployeesSimple, Integer> {
}