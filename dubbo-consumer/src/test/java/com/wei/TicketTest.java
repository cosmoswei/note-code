package com.wei;


import com.wei.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketTest {

    @Resource
    private UserService userService;

    @Test
    public void getTicket() {
        System.out.println("userService.byTicket() = " + userService.byTicket());
    }
}
