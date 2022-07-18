package com.wei.chapter3;

import com.wei.entity.User;
import com.wei.utils.UserInitializationUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class StreamOperate {
    public static void main(String[] args) {
        User oneUser = UserInitializationUtil.getOneUser();
        System.out.println("oneUser = " + oneUser);
        System.out.println("UserInitializationUtil.getUserList() = " + UserInitializationUtil.getUserList());
        List<User> userList = UserInitializationUtil.getUserList();
        userList.forEach(System.out::println);
        Set<String> stringStream = userList.stream().map(User::getGender).collect(Collectors.toSet());
        System.out.println(stringStream);
        List<User> users = userList.stream()
                .filter(user -> Integer.parseInt(user.getId()) > 25)
                .filter(user -> user.getLastName().contains("8"))
                .collect(Collectors.toList());
        log.debug(users.toString());
        users.forEach(System.out::println);
    }
}
