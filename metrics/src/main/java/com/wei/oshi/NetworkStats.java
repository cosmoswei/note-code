package com.wei.oshi;

import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;

public class NetworkStats {
    public static void main(String[] args) throws InterruptedException {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        for (NetworkIF net : hal.getNetworkIFs()) {
            System.out.println("Interface: " + net.getDisplayName());
            net.updateAttributes();
            long rxBytesStart = net.getBytesRecv();
            long txBytesStart = net.getBytesSent();
            net.updateAttributes();
            long rxBytesEnd = net.getBytesRecv();
            long txBytesEnd = net.getBytesSent();
            long rxRate = (rxBytesEnd - rxBytesStart);
            long txRate = (txBytesEnd - txBytesStart);
            System.out.println("Receive Rate: " + rxRate + " B/s");
            System.out.println("Transmit Rate: " + txRate + " B/s");
        }
    }
}
