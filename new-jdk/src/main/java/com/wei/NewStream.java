package com.wei;

import java.util.List;
import java.util.stream.Collectors;

public class NewStream {
    public static void main(String[] args) {
        List<String> alphabets = List.of("a", "b", "c", "d", "e", "f", "g", "h", "i");
        List<String> subset1 = alphabets
                .stream()
                .takeWhile(s -> !s.equals("d"))
                .collect(Collectors.toList());
        //打印出：[a, b, c]
        List<String> alphabets2 = List.of("a", "b", "c", "d", "e", "f", "g", "h", "i");
        List<String> subset2 = alphabets2
                .stream()
                .dropWhile(s -> !s.equals("d"))
                .collect(Collectors.toList());
        //打印出：[d, e, f, g, h, i]
        System.out.println(subset2);
    }
}
