package com.wei.controller;

import com.wei.mock.BookManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class BookController {


    @Resource
    private BookManager bookManager;

    @RequestMapping("/book")
    public String test() throws InterruptedException {
        bookManager.test();
        return null;
    }
}
