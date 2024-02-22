package com.wei.service;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;


@DubboService
@Component
public class TicketServiceImpl implements TicketService {
    @Override
    public String getTicket() {
        return "宇宙伟大";
    }
}
