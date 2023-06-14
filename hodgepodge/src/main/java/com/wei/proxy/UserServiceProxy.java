package com.wei.proxy;

// 代理类
public class UserServiceProxy implements UserService {
    private UserService userService;

    public UserServiceProxy(UserService userService) {
        this.userService = userService;
    }

    public void addUser(String username) {
        System.out.println("代理类操作开始");
        userService.addUser(username);
        System.out.println("代理类操作结束");
    }
}