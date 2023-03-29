package com.wei.virtual;

public class TestLoom {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Current jdk version:"+System.getProperty("java.version") );
        System.out.println("1-Run in main thread, Current ThreadName:"+Thread.currentThread().getName());
        Thread thread1 = Thread.ofVirtual().start(new Runnable() {
            @Override
            public void run() {
                System.out.println("Run in virtual thread, Current ThreadName:"+Thread.currentThread());
            }
        });
        System.out.println("2-Run in main thread, Current ThreadName:"+Thread.currentThread().getName());
        thread1.join();

        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                System.out.println("Run in classic thread, Current ThreadName:"+Thread.currentThread().getName());
            }
        });
        thread.start();
        thread.join();
    }
}