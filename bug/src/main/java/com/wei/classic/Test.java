package com.wei.classic;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        test2(args);
    }

    public static void test2(String[] args) {
        List<Long> list = new ArrayList<>();
        list.add(null);
        list.add(null);
        list.add(1L);
        Set<Long> collect1 = list.stream().collect(Collectors.toSet());
        System.out.println(collect1);
        List<Long> collect = list.stream().filter(Objects::nonNull).collect(Collectors.toList());
        System.out.println(collect);
    }

    public static void test1(String[] args) {
        List<String> collect1 = Stream.of("1", "2", "3", "4").collect(Collectors.toList());
        List<String> collect2 = Stream.of("1", "2", "3", "4").collect(Collectors.toList());
        List<String> collect3 = Stream.of("1", "2", "3", null).collect(Collectors.toList());
        System.out.println(collect1);
        System.out.println(collect2);
        collect1.addAll(collect2);
        System.out.println(collect1);
        collect1.addAll(collect3);
        System.out.println(collect1);

        List<String> list = new ArrayList<>();

        List<Long> collect = list.stream().map(Long::valueOf).collect(Collectors.toList());
        System.out.println(collect);


        List<User> users = new ArrayList<>();
        users.add(new User("huang", "null", null));
        users.add(new User("huang", null, null));
        Set<Long> collect4 = users.stream().map(User::getCode)
                .filter(StringUtils::isNotBlank).map(Long::parseLong)
                .collect(Collectors.toSet());
        System.out.println(collect4);
        System.out.println(CollectionUtils.isEmpty(collect4));


    }
}
