package com.wei.classic;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        List<String> collect1 = Stream.of("1", "2", "3", "4").collect(Collectors.toList());
        List<String> collect2 = Stream.of("1", "2", "3", "4").collect(Collectors.toList());
        List<String> collect3 = Stream.of("1", "2", "3",null).collect(Collectors.toList());
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
        users.add(new User("huang","null",null));
        users.add(new User("huang",null,null));
        Set<Long> collect4 = users.stream().map(User::getCode)
                .filter(e->StringUtils.isNotBlank(e)).map(Long::parseLong)
                .collect(Collectors.toSet());
        System.out.println(collect4);
        System.out.println(CollectionUtils.isEmpty(collect4));


    }
}
