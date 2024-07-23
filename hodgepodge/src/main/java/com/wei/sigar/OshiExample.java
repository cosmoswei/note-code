package com.wei.sigar;


import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.software.os.OperatingSystem;

public class OshiExample {
    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        SystemInfo si = new SystemInfo();
        CentralProcessor cpu = si.getHardware().getProcessor();
        GlobalMemory memory = si.getHardware().getMemory();
        OperatingSystem operatingSystem = si.getOperatingSystem();

        // CPU Information
        System.out.println("CPU: " + cpu);
        System.out.println("CPU Cores: " + cpu.getPhysicalProcessorCount());
        System.out.println("CPU Logical Cores: " + cpu.getLogicalProcessorCount());
        System.out.println("CPU System Load: " + cpu.getSystemLoadAverage(3));

        // Memory Information
        System.out.println("Total Memory: " + memory.getTotal());
        System.out.println("Available Memory: " + memory.getAvailable());

        // OS Information
        System.out.println("OS: " + operatingSystem);
        System.out.println("OS Manufacturer: " + operatingSystem.getManufacturer());
        System.out.println("OS Version: " + operatingSystem.getVersionInfo());
        long l1 = System.currentTimeMillis() - l;
        System.out.println("l1 = " + l1);
    }
}
