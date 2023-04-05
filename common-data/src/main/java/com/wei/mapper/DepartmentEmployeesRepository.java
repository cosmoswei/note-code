package com.wei.mapper;

import com.wei.entity.DepartmentEmployees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentEmployeesRepository extends JpaRepository<DepartmentEmployees, Integer> {
}