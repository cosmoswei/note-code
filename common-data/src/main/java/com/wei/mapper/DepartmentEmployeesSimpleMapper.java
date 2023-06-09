package com.wei.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wei.entity.DepartmentEmployeesSimple;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huangxuwei
 * @description 针对表【department_employees_simple】的数据库操作Mapper
 * @createDate 2023-03-30 11:05:24
 * @Entity com.wei.entity.DepartmentEmployeesSimple
 */
@Mapper
public interface DepartmentEmployeesSimpleMapper extends BaseMapper<DepartmentEmployeesSimple> {

    DepartmentEmployeesSimple selectByPrimaryKey(Long id);

    List<DepartmentEmployeesSimple> selectByIds(List<Long> ids);

    int updateByPrimaryKey(DepartmentEmployeesSimple record);

    int caseWhenUpdate(@Param("list") List<DepartmentEmployeesSimple> list);

    int batchUpdate(@Param("list") List<DepartmentEmployeesSimple> list);

    int batchUpdateSingle(@Param("ids") List<Long> ids, @Param("employeeName") String employeeName);

}
