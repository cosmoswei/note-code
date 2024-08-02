package com.wei.oshi;

import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;

public class NetworkInterfaceExample {
    public static void main(String[] args) {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();

        for (NetworkIF net : hal.getNetworkIFs()) {
            System.out.println("Interface: " + net.getDisplayName());
            System.out.println("IP Addresses: " + String.join(", ", net.getIPv4addr()));
            System.out.println("IPv6 Addresses: " + String.join(", ", net.getIPv6addr()));
            System.out.println("MAC Address: " + net.getMacaddr());
            System.out.println("Traffic In: " + net.getBytesRecv() + " bytes");
            System.out.println("Traffic Out: " + net.getBytesSent() + " bytes");
            System.out.println("--------------");
        }
    }
}
