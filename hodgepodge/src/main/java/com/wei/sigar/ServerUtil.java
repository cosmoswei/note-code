package com.wei.sigar;

import lombok.SneakyThrows;
import org.hyperic.sigar.*;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Component
public class ServerUtil {

    @SneakyThrows
    public static ServerInfos getServerInfo() {
        //服务器信息
        Properties properties = System.getProperties();
        Map<String, String> getenv = System.getenv();
        InetAddress localHost = InetAddress.getLocalHost();
        ServerInfos.ServerInfo serverInfo = ServerInfos.ServerInfo.builder().userName(getenv.get("USERNAME")).computerName(getenv.get("COMPUTERNAME")).computerDomain(getenv.get("USERDOMAIN")).serverIp(localHost.getHostAddress()).hostName(localHost.getHostName()).userHome(properties.getProperty("user.home")).userDir(properties.getProperty("user.dir")).build();
        //系统信息
        OperatingSystem instance = OperatingSystem.getInstance();
        ServerInfos.SystemInfo systemInfo = ServerInfos.SystemInfo.builder().vendorName(instance.getVendorName()).arch(instance.getArch()).description(instance.getDescription()).version(instance.getVersion()).build();
        //CPU信息
        Sigar sigar = new Sigar();
        List<ServerInfos.CpuInfo> cpuInfoList = new ArrayList<>();
        CpuInfo[] infoList = sigar.getCpuInfoList();
        CpuPerc[] cpuList = sigar.getCpuPercList();
        for (int i = 0; i < infoList.length; i++) {
            CpuInfo cpuInfo = infoList[i];
            CpuPerc cpu = cpuList[i];
            cpuInfoList.add(ServerInfos.CpuInfo.builder().mhz(cpuInfo.getMhz()).vendor(cpuInfo.getVendor()).cacheSize(cpuInfo.getCacheSize()).freqUser(cpu.getUser()).freqSys(cpu.getSys()).freqWait(cpu.getWait()).freqNice(cpu.getNice()).freqIdle(cpu.getIdle()).freqCombined(cpu.getCombined()).build());
        }
        //JVM信息
        Runtime runtime = Runtime.getRuntime();
        ServerInfos.JvmInfo jvmInfo = ServerInfos.JvmInfo.builder().totalMemory(runtime.totalMemory()).freeMemory(runtime.freeMemory()).availableProcessors(runtime.availableProcessors()).version(properties.getProperty("version")).vendor(properties.getProperty("java.vendor")).home(properties.getProperty("java.home")).specificationVersion(properties.getProperty("java.specification.version")).classPath(properties.getProperty("java.class.path")).libraryPath(properties.getProperty("java.library.path")).tmpdir(properties.getProperty("java.io.tmpdir")).dirs(properties.getProperty("java.ext.dirs")).build();
        //内存信息
        Mem mem = sigar.getMem();
        Swap swap = sigar.getSwap();
        ServerInfos.MemoryInfo memoryInfo = ServerInfos.MemoryInfo.builder().memoryTotal(mem.getTotal() / (1024 * 1024L)).memoryUsed(mem.getUsed() / (1024 * 1024L)).memoryFree(mem.getFree() / (1024 * 1024L)).swapTotal(swap.getTotal() / (1024 * 1024L)).swapUsed(swap.getUsed() / (1024 * 1024L)).swapFree(swap.getFree() / (1024 * 1024L)).build();
        return ServerInfos.builder().serverInfo(serverInfo).systemInfo(systemInfo).cpuInfo(cpuInfoList).jvmInfo(jvmInfo).memoryInfo(memoryInfo).build();
    }
}