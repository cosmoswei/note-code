package com.wei.mng;

import com.wei.entity.TestUser;
import com.wei.mapper.TestMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class TestManager {
    @Resource
    private TestMapper testMapper;

    public List<TestUser> queryUser(){
        return testMapper.queryUser();
    }
}
