package com.wei.jmx;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

public class JMXExample {
    public static void main(String[] args) {
        // 获取MemoryMXBean
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

        // 获取堆内存使用情况
        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
        System.out.println("Heap Memory Usage:");
        System.out.println("Init: " + heapMemoryUsage.getInit());
        System.out.println("Used: " + heapMemoryUsage.getUsed());
        System.out.println("Committed: " + heapMemoryUsage.getCommitted());
        System.out.println("Max: " + heapMemoryUsage.getMax());

        // 获取非堆内存使用情况
        MemoryUsage nonHeapMemoryUsage = memoryMXBean.getNonHeapMemoryUsage();
        System.out.println("Non-Heap Memory Usage:");
        System.out.println("Init: " + nonHeapMemoryUsage.getInit());
        System.out.println("Used: " + nonHeapMemoryUsage.getUsed());
        System.out.println("Committed: " + nonHeapMemoryUsage.getCommitted());
        System.out.println("Max: " + nonHeapMemoryUsage.getMax());
    }
}
