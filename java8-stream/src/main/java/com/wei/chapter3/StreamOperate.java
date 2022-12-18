package com.wei.chapter3;

import com.wei.entity.Person;
import com.wei.entity.User;
import com.wei.utils.UserInitializationUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Slf4j
public class StreamOperate {
    public static void main(String[] args) {
        flatMap();
    }

    private static void mapOperate() {
        List<Integer> integers1 = Arrays.asList(1, 2, 3);
        List<Integer> integers2 = Arrays.asList(3, 4, 5, 6);

        List<int[]> collect = integers1.stream().flatMap(i ->
                integers2.stream().filter(j -> (i * j) % 3 == 0).map(j -> new int[]{i, j})
        ).collect(toList());

        collect.forEach(i -> Arrays.stream(i).asLongStream().forEachOrdered(System.out::println));
    }

    private static void init() {
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
                .collect(toList());
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
        }).collect(toList());
        personList.forEach(System.out::println);
    }

    private static void flatMap() {

        List<User> userList = UserInitializationUtil.getUserList();
        List<User> userList2 = UserInitializationUtil.getUserList2();
        List<List<User>> userList3 = new ArrayList<>();
        userList3.add(userList);
        userList3.add(userList2);

        userList3.stream().flatMap(Collection::stream).forEach(System.out::println);
        Stream<User> userStream = userList3.stream().flatMap(Collection::stream);
        List<User> userList4 = userStream.collect(toList());
        List<Person> personList = new ArrayList<>();
        userList4.stream()
                .filter(user -> user.getLastName().startsWith("5"))
                .filter(user -> user.getLastName().endsWith("9")).collect(Collectors.toSet())
                .forEach(user -> personList.add(buildPerson(user)));
        System.out.println("personList = " + personList);
        System.out.println("userList4: " + userList4);


        Map<String, List<User>> collect1 = userList4.stream().collect(groupingBy(User::getId));

        Map<String, Long> collect2 = collect1.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> (long) e.getValue().size()));
        Long asd = collect2.get("88");
        System.out.println(collect1);
        System.out.println("asd" + asd);
        long count = userList.stream().map(User::getId).filter(id -> id.startsWith("5")).count();
        System.out.println(count);

        List<Integer> collect = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4)).flatMap(Collection::stream).collect(toList());
        collect.forEach(System.out::println);
    }

    private static void minAndMax() {
        List<User> userList = UserInitializationUtil.getUserList();
        User minIdUser = userList.stream().min(Comparator.comparing(User::getId)).get();
        User maxIdUser1 = userList.stream().max(Comparator.comparing(User::getId)).get();
        User maxIdUser2 = userList.stream().max(Comparator.comparing(user -> Integer.parseInt(user.getId()))).get();
        log.info(minIdUser.toString());
        log.info(maxIdUser1.toString());
        log.info(maxIdUser2.toString());
    }

    private static void universalMode() {

    }

    private static void reduce() {
//        Integer reduce = Stream.of(1, 2, 3, 4, 5, 6).reduce(0, (acc, element) -> acc + element);
        Integer reduce = Stream.of(1, 2, 3, 4, 5, 6).reduce(0, Integer::sum);
        log.debug(reduce.toString());
        assertEquals(reduce.toString(), "21");
        assertEquals(reduce.toString(), "22");
    }

    private static void assertEquals(String a, String b) {
        assert !Objects.nonNull(a) || !Objects.nonNull(b) || a.equals(b);
    }

    private static Person buildPerson(User user) {
        return Person.builder().id(user.getId()).fullName(user.getFirstName() + user.getLastName()).gender(user.getGender()).build();
    }
}
