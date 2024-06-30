package com.wei.service;

import com.wei.SeataIdWorker;
import com.wei.WorkIdGenerator;
import org.apache.dubbo.config.annotation.DubboService;


@DubboService
public class TicketServiceImpl implements TicketService {

    @Override
    public String queryTicketById(Long ticketId) {
        return "满3减1";
    }

    @Override
    public Long getTicketId() {
        SeataIdWorker seataIdWorker = new SeataIdWorker((long) WorkIdGenerator.getHostWorkId());
        return seataIdWorker.nextId();
    }
}
