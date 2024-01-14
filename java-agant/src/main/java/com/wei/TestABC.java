package com.wei;

public class TestABC {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("TestABC main:" + String.join(",", args));
        new TestABC().abc();
    }

    public void abc() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("abc in method");
    }
}