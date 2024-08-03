package com.wei.oshi;

import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;

import java.util.List;

public class NetworkBandwidth {
    public static void main(String[] args) throws InterruptedException {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        List<NetworkIF> networkIFs = hal.getNetworkIFs();

        for (NetworkIF net : networkIFs) {
            System.out.println("Interface: " + net.getName());
            System.out.println("Speed: " + net.getSpeed() + " bps");
            System.out.println("Bytes Received: " + net.getBytesRecv());
            System.out.println("Bytes Sent: " + net.getBytesSent());
            System.out.println("Packets Received: " + net.getPacketsRecv());
            System.out.println("Packets Sent: " + net.getPacketsSent());
            System.out.println("-------------");
            long bytesRecv1 = net.getBytesRecv();
            long bytesSent1 = net.getBytesSent();
            Thread.sleep(1000);  // 等待一秒
            long bytesRecv2 = net.getBytesRecv();
            long bytesSent2 = net.getBytesSent();

            long bytesReceivedPerSecond = bytesRecv2 - bytesRecv1;
            long bytesSentPerSecond = bytesSent2 - bytesSent1;

            long currentBandwidthUsage = (bytesReceivedPerSecond + bytesSentPerSecond) * 8;  // 转换为比特

            long remainingBandwidth = net.getSpeed() - currentBandwidthUsage;
            System.out.println("Remaining Bandwidth: " + remainingBandwidth / 8 + " bps");

        }
    }
}
