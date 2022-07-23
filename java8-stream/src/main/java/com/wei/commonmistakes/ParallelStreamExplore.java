package com.wei.commonmistakes;

import com.wei.entity.User;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ParallelStreamExplore {
    public static void main(String[] args) {
        IntStream.rangeClosed(1, 100).parallel().forEach(i -> {
            System.out.println(LocalDateTime.now() + " : " + i);
            System.out.println(User.builder().id(String.valueOf(i))
                    .gender("male")
                    .lastName("wei")
                    .firstName("cosmos")
                    .nationality("china")
                    .build());
            try {
                Thread.sleep(1000);
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ignored) {
            }
        });
    }
}
