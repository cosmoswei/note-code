package com.wei.util;

import com.wei.entity.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UserInitializationUtil {

    private static final Integer LOOP_COUNT = 120;

    public static User getOneUser() {
        return User.builder().
                firstName("黄").lastName("旭伟").gender("男").build();
    }

    public static List<User> getUserList() {
        List<User> userList = new ArrayList<>();
//        IntStream.rangeClosed(1, LOOP_COUNT).parallel().forEach(i -> {
//            User oneUser = getOneUser();
//            oneUser.setLastName(String.valueOf(i));
//            userList.add(oneUser);
//        });

        for (int i = 0; i < LOOP_COUNT; i++) {
            User oneUser = getOneUser();
            oneUser.setId(String.valueOf(i));
            String lastName = oneUser.getLastName();
            oneUser.setLastName(lastName + String.valueOf(i));
            userList.add(oneUser);
        }
        return userList;
    }

    public static List<User> getUserList2() {
        List<User> userList = getUserList();
        Collections.reverse(userList);
        return userList.stream().filter(user -> Integer.parseInt(user.getId()) > 50).collect(Collectors.toList());
    }
}
