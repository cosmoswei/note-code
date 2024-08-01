package com.wei.jmx;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class JMXThreadExample {
    public static void main(String[] args) {
        // 获取ThreadMXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

        // 获取线程信息
        int threadCount = threadMXBean.getThreadCount();
        int peakThreadCount = threadMXBean.getPeakThreadCount();
        long totalStartedThreadCount = threadMXBean.getTotalStartedThreadCount();
        int daemonThreadCount = threadMXBean.getDaemonThreadCount();

        System.out.println("Thread Count: " + threadCount);
        System.out.println("Peak Thread Count: " + peakThreadCount);
        System.out.println("Total Started Thread Count: " + totalStartedThreadCount);
        System.out.println("Daemon Thread Count: " + daemonThreadCount);
    }
}
