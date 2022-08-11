package com.wei.commonmistakes;

import com.wei.entity.User;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.OptionalDouble;

public class OptionalExplore {
    public static void main(String[] args) {
        User user = null;
        boolean present = OptionalDouble.empty().isPresent();
        System.out.println(present);
//        System.out.println("Optional.of(1).get() = " + Optional.ofNullable(user.getId()).orElse("1"));
        User user1 = Optional.ofNullable(user).orElse(User.builder()
                .id("1")
                .gender("male")
                .lastName("wei")
                .firstName("cosmos")
                .nationality("china")
                .build());
        System.out.println("user = " + user1);
    }
}
