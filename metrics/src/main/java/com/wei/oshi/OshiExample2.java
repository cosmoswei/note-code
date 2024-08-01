package com.wei.oshi;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.software.os.OperatingSystem;

public class OshiExample2 {
    public static void main(String[] args) {
        SystemInfo systemInfo = new SystemInfo();
        
        // 获取操作系统信息
        OperatingSystem os = systemInfo.getOperatingSystem();
        System.out.println("Operating System: " + os);

        // 获取 CPU 信息
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        System.out.println("CPU: " + processor);

        // 获取内存信息
        GlobalMemory memory = systemInfo.getHardware().getMemory();
        System.out.println("Memory: " + memory);

        // 获取系统负载
        double[] loadAverage = processor.getSystemLoadAverage(3);
        System.out.println("System Load Average: " + (loadAverage[0] >= 0 ? loadAverage[0] : "N/A"));

        // 获取 CPU 使用率
        double cpuLoad = processor.getSystemCpuLoadBetweenTicks(new long[]{1,2,3,4,5,6,7,9});
        System.out.println("CPU Load: " + cpuLoad * 100 + "%");

        // 获取内存使用情况
        long availableMemory = memory.getAvailable();
        long totalMemory = memory.getTotal();
        System.out.println("Available Memory: " + availableMemory / 1024 / 1024 + " MB");
        System.out.println("Total Memory: " + totalMemory / 1024 / 1024 + " MB");
    }
}
