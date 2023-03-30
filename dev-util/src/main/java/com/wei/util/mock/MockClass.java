package com.wei.util.mock;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MockClass {
    public static List<People> mockPeopleList() {
        return Stream.of(new People("小黄", 1),
                        new People("小张", 2),
                        new People("小李", 3),
                        new People("小宋", 4),
                        new People("小旭", 5),
                        new People("小伟", 6),
                        new People("小大", 7),
                        new People("小大", 8))
                .collect(Collectors.toList());
    }
}
