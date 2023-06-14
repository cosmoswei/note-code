package com.wei.proxy;

// 实现类B
public class UserServiceImplB implements UserService {
    public void addUser(String username) {
        System.out.println("UserServiceImplB 添加用户：" + username);
    }
}