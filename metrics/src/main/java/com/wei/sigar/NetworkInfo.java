package com.wei.sigar;

import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.NetInterfaceStat;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public class NetworkInfo {
    public static void main(String[] args) {
        Sigar sigar = new Sigar();

        try {
            String[] netInterfaces = sigar.getNetInterfaceList();
            for (String netInterface : netInterfaces) {
                NetInterfaceConfig config = sigar.getNetInterfaceConfig(netInterface);
                NetInterfaceStat stat = sigar.getNetInterfaceStat(netInterface);

                System.out.println("Interface: " + netInterface);
                System.out.println("Address: " + config.getAddress());
                System.out.println("Received Packets: " + stat.getRxPackets());
                System.out.println("Transmitted Packets: " + stat.getTxPackets());
                System.out.println("Received Bytes: " + stat.getRxBytes());
                System.out.println("Transmitted Bytes: " + stat.getTxBytes());
            }
        } catch (SigarException e) {
            e.printStackTrace();
        } finally {
            sigar.close();
        }
    }
}
