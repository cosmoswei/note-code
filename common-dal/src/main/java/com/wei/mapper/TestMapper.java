package com.wei.mapper;

import com.wei.entity.TestUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestMapper {
    List<TestUser> queryUser();
}
