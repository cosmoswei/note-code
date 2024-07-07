package com.wei.controller;

import com.wei.export.ExportImpl;
import com.wei.export.ExportUser;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/export")
@Slf4j
public class ExportController {

    @Resource
    private ExportImpl export;

    @PostMapping("/exportFile")
    public void exportFile() {
            new Thread(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    Thread thread1 = Thread.currentThread();
                    ExportUser sysUser =new ExportUser();
                    sysUser.setUserName(thread1.getName());
                    export.export(sysUser);
                }
            }).start();
        }
}