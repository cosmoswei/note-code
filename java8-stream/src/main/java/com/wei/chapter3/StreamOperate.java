package com.wei.chapter3;

import com.wei.entity.Person;
import com.wei.entity.User;
import com.wei.utils.UserInitializationUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;

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
                .filter(user -> Integer.parseInt(user.getId()) > 100)
                .filter(user -> user.getLastName().contains("8"))
                .map(user -> user.setLastName("虚伪" + user.getId()))
                .filter(user -> user.getLastName().contains("1"))
                .map(user -> user.setLastName("虚伪" + user.getId() + "X"))
                .collect(Collectors.toList());
        log.debug(users.toString());
        users.forEach(System.out::println);
        userList.forEach(System.out::println);
        log.info("");
        List<Person> personList = userList.stream().map(user -> {
            Person person = new Person();
            person.setId(user.getId());
            person.setFullName(user.getFirstName() + user.getLastName());
            person.setGender(user.getGender());
            return person;
        }).collect(Collectors.toList());
        personList.forEach(System.out::println);
    }
}
