package com.wei.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wei.entity.DepartmentEmployees;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huangxuwei
 * @description 针对表【department_employees】的数据库操作Mapper
 * @createDate 2023-03-30 11:05:24
 * @Entity com.wei.entity.DepartmentEmployees
 */
@Mapper
public interface DepartmentEmployeesMapper extends BaseMapper<DepartmentEmployees> {

    int deleteByPrimaryKey(Long id);

    int insert(DepartmentEmployees record);

    int insertSelective(DepartmentEmployees record);

    DepartmentEmployees selectByPrimaryKey(Long id);

    List<DepartmentEmployees> selectByIds(List<Long> ids);

    int updateByPrimaryKeySelective(DepartmentEmployees record);

    int updateByPrimaryKey(DepartmentEmployees record);

    int caseWhenUpdate(@Param("list") List<DepartmentEmployees> list);

    int batchUpdate(@Param("list") List<DepartmentEmployees> list);

    int batchUpdateSingle(@Param("ids") List<Long> ids, @Param("employeeName") String employeeName);

}
