package com.wei.utils;

import com.wei.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class UserInitializationUtil {
    public static User getOneUser() {
        return User.builder().
                firstName("黄").lastName("旭伟").gender("男").build();
    }

    public static List<User> getUserList() {
        List<User> userList = new ArrayList<>();
//        IntStream.rangeClosed(1, 100).parallel().forEach(i -> {
//            User oneUser = getOneUser();
//            oneUser.setLastName(String.valueOf(i));
//            userList.add(oneUser);
//        });
        for (int i = 0; i < 100; i++) {
            User oneUser = getOneUser();
            oneUser.setId(String.valueOf(i));
            String lastName = oneUser.getLastName();
            oneUser.setLastName(lastName + String.valueOf(i));
            userList.add(oneUser);
        }
        return userList;
    }
}
