package com.wei.virtual;

import java.time.Duration;

public final class VirtualThreads {
    static void say(String s) {
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(Duration.ofMillis(100));
                System.out.println(s);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        var worldThread = Thread.startVirtualThread(
                () -> say("world")
        );

        say("hello");

        // 等待虚拟线程结束
        worldThread.join();
    }
}