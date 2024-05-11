package com.wei;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class WorkIdGenerator {

    public static int getHostWorkId() {
        byte[] ipAddress = getLocalIP();
        int lastByte = ipAddress[ipAddress.length - 1] & 0xFF; // 获取IP地址的最后一个字节，并转换为无符号整数
        return lastByte % 1024; // 取余操作，保证结果在0-1023之间
    }

    public static void main(String[] args) {
        System.out.println("getHostWorkId() = " + getHostWorkId());
    }

    private static byte[] getLocalIP() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = interfaces.nextElement();
                if (networkInterface.isUp() && !networkInterface.isLoopback()) {
                    Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        InetAddress address = addresses.nextElement();
                        if (!address.isLoopbackAddress() && address.getHostAddress().indexOf(':') == -1) {
                            return address.getAddress();
                        }
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return null;
    }
}