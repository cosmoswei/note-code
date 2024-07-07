package com.wei.export;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Slf4j
@Component
public class ExportQueue {


    private final int MAX_CAPACITY = 10; // 队列最大容量
    private LinkedList<ExportUser> queue; // 用户队列

    public ExportQueue(LinkedList<ExportUser> queue) {
        this.queue = new LinkedList<>();
    }

    /**
     * 排队队列添加
     *
     * @param sysUser
     */
    public synchronized LinkedList<ExportUser> add(ExportUser sysUser) {
        while (queue.size() >= MAX_CAPACITY) {
            try {
                log.info("当前排队人已满，请等待");
                wait();
            } catch (InterruptedException e) {
                e.getMessage();
            }
        }
        queue.add(sysUser);
        log.info("目前导出队列排队人数：" + queue.size());
        notifyAll();
        return queue;
    }


    /**
     * 获取排队队列下一个人
     *
     * @return
     */
    public synchronized ExportUser getNextSysUser() {
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ExportUser sysUser = queue.remove();
        notifyAll(); //唤醒
        return sysUser;
    }
}