package com.wei;

public class Test {

    private static ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();

    public static void getThreadLocal(String val) {
        stringThreadLocal.set(val);
        System.out.println("getThreadLocal in");
        System.out.println(stringThreadLocal.get());
    }

    public static void main(String[] args) throws InterruptedException {

        Thread testVT1 = Thread.ofVirtual().name("testVT1").unstarted(() -> Test.getThreadLocal("testVT1 local var"));

        Thread testVT2 = Thread.ofVirtual().name("testVT2").unstarted(() -> Test.getThreadLocal("testVT2 local var"));

        testVT1.start();
        testVT2.start();

        System.out.println(stringThreadLocal.get());
        stringThreadLocal.set("main local var");
        System.out.println(stringThreadLocal.get());
        testVT1.join();
        testVT2.join();
    }

}
