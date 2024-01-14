package com.wei;

import lombok.Data;

public class TestUser {
    private String userCode;
    private String userName;

    public String sayHello() {
        return "old sayHello";
    }
}
