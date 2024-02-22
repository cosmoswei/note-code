package com.wei.service;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @DubboReference
    TicketService ticketService;

    public String byTicket() {
        System.out.println("在注册中心拿到票=》》》》" + ticketService.getTicket());
        return ticketService.getTicket();
    }
}
