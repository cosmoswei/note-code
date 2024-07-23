package com.wei.sigar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;

public class SystemCommandExample {

    static OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();

    public static void main(String[] args) {
        double systemLoadAverage = operatingSystemMXBean.getSystemLoadAverage();
        System.out.println("systemLoadAverage = " + systemLoadAverage);
        double systemCpuLoad = getSystemCpuLoad();
        System.out.println("systemCpuLoad = " + systemCpuLoad);
    }

    private static double getSystemCpuLoad()  {
        double invoke = 0;
        try {
        Method getSystemCpuLoad = operatingSystemMXBean.getClass().getMethod("getSystemCpuLoad");
            invoke = (double)getSystemCpuLoad.invoke(operatingSystemMXBean);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return invoke;
    }

    public void getCommand(String[] args) {
        try {
            // Example: Getting CPU usage using `top` command on Unix-based systems
            String[] cmd = {"/bin/sh", "-c", "top -bn1 | grep 'Cpu(s)'"};
            Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
