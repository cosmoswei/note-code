package com.wei.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wei.entity.Rule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author huangxuwei
 */
@Mapper
public interface RuleMapper extends BaseMapper<Rule> {

    Rule getByName(@Param("name") String name);

    void save(@Param("rule")Rule rule);
}
