package com.wei;

public class SyncExample {
    public synchronized void method1() {
        System.out.println("Method 1 start");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 1 end");
    }

    public synchronized void method2() {
        System.out.println("Method 2 start");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 2 end");
    }

    public synchronized static void method3() {
        System.out.println("Method 3 start");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("method 3 end");
    }

    public static void main(String[] args) {
        SyncExample example = new SyncExample();
        Thread t1 = new Thread(example::method1);
        Thread t2 = new Thread(example::method2);
        Thread t3 = new Thread(SyncExample::method3);
        t1.start();
        t2.start();
        t3.start();
    }
}
