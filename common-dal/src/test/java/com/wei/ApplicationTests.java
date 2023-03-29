package com.wei;

import com.wei.entity.TestUser;
import com.wei.mng.TestManager;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class ApplicationTests {

    @Resource
    private TestManager testManager;

    @Test
    void contextLoads() {
        List<TestUser> testUsers = testManager.queryUser();
        System.out.println(testUsers);
    }

}
