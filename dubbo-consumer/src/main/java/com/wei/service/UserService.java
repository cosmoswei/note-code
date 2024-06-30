package com.wei.service;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @DubboReference
    private TicketService ticketService;

    public String byTicket() {
        Long ticketId = ticketService.getTicketId();
        System.out.println("在注册中心拿到票 = " + ticketService.queryTicketById(ticketId));
        return "ticketId=" + ticketId + ", ticketInfo = " + ticketService.queryTicketById(ticketId);
    }
}
