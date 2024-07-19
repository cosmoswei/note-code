package com.wei;

public class LockUpgradeDemo {


    //java -XX:+PrintFlagsFinal -XX:+PrintCompilation -XX:+UnlockDiagnosticVMOptions -XX:+LogCompilation -XX:+PrintSafepointStatistics -XX:PrintSafepointStatisticsCount=1 -XX:+PrintAssembly -XX:BiasedLockingStartupDelay=0 com.we.LockUpgradeDemo
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000); // 延迟启动，确保偏向锁激活
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    synchronized (lock) {
                        // 模拟工作
                    }
                }
            }).start();
        }

        // 等待线程完成
        Thread.sleep(10000);
    }
}
