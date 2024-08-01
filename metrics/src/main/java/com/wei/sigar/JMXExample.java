package com.wei.sigar;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;

public class JMXExample {
    public static void main(String[] args) {
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);

        // CPU Load
        System.out.println("System CPU Load: " + osBean.getSystemCpuLoad() * 100);
        System.out.println("Process CPU Load: " + osBean.getProcessCpuLoad() * 100);

        // Memory Usage
        System.out.println("Total Physical Memory: " + osBean.getTotalPhysicalMemorySize());
        System.out.println("Free Physical Memory: " + osBean.getFreePhysicalMemorySize());
        System.out.println("Total Swap Space: " + osBean.getTotalSwapSpaceSize());
        System.out.println("Free Swap Space: " + osBean.getFreeSwapSpaceSize());
    }
}
