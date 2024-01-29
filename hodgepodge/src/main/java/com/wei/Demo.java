package com.wei;

import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Demo {

    public static void main(String[] args) {
        Demo demo = new Demo();
        int start = 1;
        int end = 100_000_000;

        long startTm = System.currentTimeMillis();
        int[] array = IntStream.rangeClosed(start, end).toArray();
//        int[] ins = {4, 5, 6, 7, 8, 9, 7, 6, 5, 4, 3, 2};
        int[] sort = demo.sort(array);
//        for (int i = 0; i < sort.length; i++) {
//            System.out.println("sort[i] = " + sort[i]);
//        }
        System.out.println((System.currentTimeMillis() - startTm) );
    }

    public int[] sort(int[] src) {
        Map<Integer, List<Integer>> buckets = new HashMap<>(10000);
        int[] tgt = new int[src.length];
        int index = 0;
        for (int i = 0; i < 10000; i++) {
            buckets.put(i, new ArrayList<>());
        }
        for (int i = 0; i < src.length; i++) {
            List<Integer> integers = buckets.get(src[i]);
            if (null != integers) {
                integers.add(src[i]);
            }
        }
        for (int i = 1; i < 10000; i++) {
            List<Integer> integers = buckets.get(i);
            for (Integer integer : integers) {
                tgt[index] = integer;
                index++;
            }
        }
        return tgt;
    }

    @Data
    class Email {
        private String user;
        private String title;
        private Date TmSend;
    }

    public List<Email> search(List<Email> tgt, String user, String title, Date tmStart, Date tmEnd) {
        List<Email> res = new ArrayList();
        tgt.stream().filter(email -> email.getUser().equals(user)).filter(e -> e.getTitle().equals(title)).filter(e -> {
            if (e.getTmSend().getTime() > tmStart.getTime()
                    || e.getTmSend().getTime() <= tmEnd.getTime()) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        return res;
    }
}
