package com.wei;

public class Main {
    public static void main(String[] args) {
        String s1 = new String("1") + new String("1");
        s1.intern();
        String s2 = "11";
        System.out.println(s1 == s2);
    }
}