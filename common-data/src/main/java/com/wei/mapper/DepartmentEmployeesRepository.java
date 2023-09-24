package com.wei.mapper;

import com.wei.entity.DepartmentEmployees;
import org.springframework.data.jpa.repository.JpaRepository;

// 开启JPA需要关闭 DataSourceConfig 的 @Configuration
public interface DepartmentEmployeesRepository extends JpaRepository<DepartmentEmployees, Integer> {
}