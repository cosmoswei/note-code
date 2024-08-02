package com.wei.oshi;

import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;
import oshi.software.os.OperatingSystem;
import oshi.software.os.NetworkParams;

public class DefaultNetworkInfo {
    public static void main(String[] args) {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        OperatingSystem os = si.getOperatingSystem();
        NetworkParams networkParams = os.getNetworkParams();

        // 获取默认网关
        String defaultGateway = networkParams.getIpv4DefaultGateway();
        System.out.println("Default Gateway: " + defaultGateway);

        // 获取默认网口
        NetworkIF defaultInterface = null;
        for (NetworkIF net : hal.getNetworkIFs()) {
            if (net.getIPv4addr().length > 0) {
                // 假设第一个有IPv4地址的接口是默认接口
                defaultInterface = net;
                break;
            }
        }

        if (defaultInterface != null) {
            System.out.println("Default Interface: " + defaultInterface.getDisplayName());
            System.out.println("IP Addresses: " + String.join(", ", defaultInterface.getIPv4addr()));
        } else {
            System.out.println("No default interface found.");
        }
    }
}
