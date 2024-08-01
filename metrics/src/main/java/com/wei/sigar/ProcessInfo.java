package com.wei.sigar;

import org.hyperic.sigar.ProcCpu;
import org.hyperic.sigar.ProcMem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public class ProcessInfo {
    public static void main(String[] args) {
        Sigar sigar = new Sigar();

        try {
            long[] pids = sigar.getProcList();
            for (long pid : pids) {
                ProcCpu procCpu = sigar.getProcCpu(pid);
                ProcMem procMem = sigar.getProcMem(pid);

                System.out.println("PID: " + pid);
                System.out.println("CPU Time: " + procCpu.getTotal());
                System.out.println("Memory: " + procMem.getSize() / 1024L + " KB");
            }
        } catch (SigarException e) {
            e.printStackTrace();
        } finally {
            sigar.close();
        }
    }
}
