package com.wei.sigar;

import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;

public class SigarExample {
    public static void main(String[] args) {
        Sigar sigar = new Sigar();

        try {
            // 获取 CPU 信息
            CpuPerc cpu = sigar.getCpuPerc();
            System.out.println("CPU User Time: " + CpuPerc.format(cpu.getUser()));
            System.out.println("CPU Sys Time: " + CpuPerc.format(cpu.getSys()));
            System.out.println("CPU Idle Time: " + CpuPerc.format(cpu.getIdle()));

            // 获取内存信息
            Mem mem = sigar.getMem();
            System.out.println("Total Memory: " + mem.getTotal() / 1024L / 1024L + " MB");
            System.out.println("Used Memory: " + mem.getUsed() / 1024L / 1024L + " MB");
            System.out.println("Free Memory: " + mem.getFree() / 1024L / 1024L + " MB");

        } catch (SigarException e) {
            e.printStackTrace();
        } finally {
            sigar.close();
        }
    }
}
