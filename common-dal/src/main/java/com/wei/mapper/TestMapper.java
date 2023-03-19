package com.wei.mapper;

import com.wei.entity.TestUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {
    List<TestUser> queryUser();
}
