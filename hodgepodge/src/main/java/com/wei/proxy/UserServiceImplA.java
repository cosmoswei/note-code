package com.wei.proxy;

// 实现类A
public class UserServiceImplA implements UserService {
    public void addUser(String username) {
        System.out.println("UserServiceImplA 添加用户：" + username);
    }
}