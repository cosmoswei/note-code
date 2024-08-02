package com.wei.oshi;

import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;

public class PhysicalNetworkInterfaceExample {
    public static void main(String[] args) {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();

        for (NetworkIF net : hal.getNetworkIFs()) {
            // Check if the interface is a physical interface
            if (isPhysicalInterface(net)) {
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

    private static boolean isPhysicalInterface(NetworkIF net) {
        String name = net.getName().toLowerCase();
        String displayName = net.getDisplayName().toLowerCase();

        // Add conditions to filter out known virtual interface names and those without IPv4 addresses
        return !(name.contains("virtual") || name.contains("docker") || name.contains("vmnet") || name.contains("vbox")
                || name.contains("vnic") || displayName.contains("virtual") || displayName.contains("docker")
                || displayName.contains("vmnet") || displayName.contains("vbox") || displayName.contains("vnic"))
                && !(net.getIPv4addr().length ==0);
    }
}
