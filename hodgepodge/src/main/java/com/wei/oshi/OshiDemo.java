package com.wei.oshi;

import cn.hutool.core.date.DateUtil;
import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author 瓦力
 * @description oshi基本用法
 * @date 2022-02-23 20:49
 */
public class OshiDemo {
    public static void main(String[] args) throws UnknownHostException, InterruptedException {
        SystemInfo si = new SystemInfo();

        OperatingSystem operatingSystem = si.getOperatingSystem();
        HardwareAbstractionLayer hardware = si.getHardware();
        Sensors sensors = hardware.getSensors();
        CentralProcessor processor = hardware.getProcessor();
        // CPU信息
        printCpuInfo(processor, sensors);
        // 磁盘
        printSysFiles(operatingSystem);
        // 运行内存
        printMem(hardware.getMemory());
        // 操作系统
        printSystemInfo(operatingSystem);
        // JVM
        printJVM();
        printNetWork( hardware);

    }

    public static void printCpuInfo(CentralProcessor processor, Sensors sensors) throws InterruptedException {
        System.out.println("CPU基本信息");
        long[] startTicks = processor.getSystemCpuLoadTicks();
        TimeUnit.MILLISECONDS.sleep(2000);
        long[] endTicks = processor.getSystemCpuLoadTicks();
        long user = endTicks[CentralProcessor.TickType.USER.getIndex()] - startTicks[CentralProcessor.TickType.USER.getIndex()];
        long nice = endTicks[CentralProcessor.TickType.NICE.getIndex()] - startTicks[CentralProcessor.TickType.NICE.getIndex()];
        long sys = endTicks[CentralProcessor.TickType.SYSTEM.getIndex()] - startTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
        long idle = endTicks[CentralProcessor.TickType.IDLE.getIndex()] - startTicks[CentralProcessor.TickType.IDLE.getIndex()];
        long ioWait = endTicks[CentralProcessor.TickType.IOWAIT.getIndex()] - startTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
        long irq = endTicks[CentralProcessor.TickType.IRQ.getIndex()] - startTicks[CentralProcessor.TickType.IRQ.getIndex()];
        long softIrq = endTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()] - startTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
        long steal = endTicks[CentralProcessor.TickType.STEAL.getIndex()] - startTicks[CentralProcessor.TickType.STEAL.getIndex()];
        long totalCpu = user + nice + sys + idle + ioWait + irq + softIrq + steal;
        // CPU 温度（以摄氏度为单位）（如果有）
        System.out.println(String.format("CPU型号:%s\tCPU核心数:%d\tCPU温度:%s\t总使用率:%s\t系统使用率:%s\t当前空闲率:%s\t用户使用率:%s",
                        processor.getProcessorIdentifier().getName(),
                        processor.getLogicalProcessorCount(),
                        sensors.getCpuTemperature(),
                        Arith.round(Arith.mul(totalCpu, 100), 2),
                        Arith.round(Arith.mul(Arith.div(sys, totalCpu, 2), 100), 2),
                        Arith.round(Arith.mul(Arith.div(idle, totalCpu, 2), 100), 2),
                        Arith.round(Arith.mul(Arith.div(user, totalCpu, 2), 100), 2)
                )
        );


    }

    public static void printSysFiles(OperatingSystem os) {
        System.out.println("磁盘内存");
        FileSystem fileSystem = os.getFileSystem();
        List<OSFileStore> fileStores = fileSystem.getFileStores();
        for (OSFileStore fs : fileStores) {
            long free = fs.getUsableSpace();
            long total = fs.getTotalSpace();
            long used = total - free;
            System.out.println(String.format("盘符:%s\t类型:%s\t盘名:%s\t总量:%s\t剩余:%s\t使用:%s\t使用率:%s", fs.getMount(), fs.getType(), fs.getName()
                    , convertFileSize(total), convertFileSize(free), convertFileSize(used), Arith.mul(Arith.div(used, total, 2), 100)));

        }
    }

    private static void printSystemInfo(OperatingSystem operatingSystem) throws UnknownHostException {
        System.out.println("系统信息");
        OperatingSystem.OSVersionInfo versionInfo = operatingSystem.getVersionInfo();
        InetAddress ip = Inet4Address.getLocalHost();
        Properties properties = System.getProperties();
        System.out.println("主机名:" + ip.getHostName());
        /*  厂家 + 家族 + 版本 + 构建版号*/
        System.out.println("系统描述:" + operatingSystem.getManufacturer() + " " + operatingSystem.getFamily() + versionInfo.getVersion() + " Build " + versionInfo.getBuildNumber());
        System.out.println("进程运行数量:" + operatingSystem.getProcessCount());
        System.out.println("线程运行数量:" + operatingSystem.getThreadCount());
        System.out.println("系统位数:" + properties.getProperty("os.arch"));
        System.out.println("系统版本:" + properties.getProperty("os.version"));
        System.out.println("系统部署目录:" + properties.getProperty("user.dir"));
        System.out.println("系统IP:" + ip.getHostAddress());
        System.out.println("系统支持位数" + operatingSystem.getBitness());
        /**
         * 系统启动的大致时间，以秒为单位。
         */
        Date systemBootTime = new Date(operatingSystem.getSystemBootTime() * 1000);
        System.out.println("系统启动时间:" + DateUtil.format(systemBootTime, "yyyy-MM-dd HH:mm:ss"));
        /**
         * 自启动以来的秒数,运行时长
         */
        System.out.println("运行时长:" + secondToTime(operatingSystem.getSystemUptime()));
    }

    public static void printMem(GlobalMemory m) {
        System.out.println("内存信息");
        System.out.println("总内存:" + convertFileSize(m.getTotal()));
        System.out.println("已用内存:" + convertFileSize(m.getTotal() - m.getAvailable()));
        System.out.println("剩余内存:" + convertFileSize(m.getAvailable()));
    }

    public static void printJVM() {
        System.out.println("JVM");
        Properties props = System.getProperties();
        // 当前可用的内存总量MB
        long totalMemory = Runtime.getRuntime().totalMemory();
        System.out.println("JVM当前可用的内存总量:" + convertFileSize(totalMemory) );
        // 当前内存总量的近似值
        long freeMemory = Runtime.getRuntime().freeMemory();
        System.out.println("JVM当前内存总量的近似值:" + convertFileSize(freeMemory ));
        // 虚拟机的最大内存容量
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("JVM最大内存容量" + convertFileSize(maxMemory) );
        System.out.println("版本:" + props.getProperty("java.version"));
        System.out.println("JAVA_HOME:" + props.getProperty("java.home"));
    }

    public static void printNetWork(HardwareAbstractionLayer hardware) {
        System.out.println("网络带宽");
        List<NetworkIF> networkIFs = hardware.getNetworkIFs();
        for (NetworkIF networkIF : networkIFs) {
            System.out.println(String.format("IPV4:%s\t网络接收:%s\t网络发送:%s\t显示名称:%s\tMAC地址:%s\t",
                    Arrays.toString(networkIF.getIPv4addr()),
                    networkIF.getBytesRecv(),
                    networkIF.getBytesSent(),
                    networkIF.getDisplayName(),
                    networkIF.getMacaddr()));
        }
    }



    /**
     * 字节转换
     *
     * @param size 字节大小
     * @return 转换后值
     */
    public static String convertFileSize(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;
        if (size >= gb) {
            return String.format("%.1f GB", (float) size / gb);
        } else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
        } else {
            return String.format("%d B", size);
        }
    }

    /**
     * 将秒数转换为日时分秒
     *
     * @param second
     * @return
     */
    public static String secondToTime(long second) {
        long days = second / 86400;            //转换天数
        second = second % 86400;            //剩余秒数
        long hours = second / 3600;            //转换小时
        second = second % 3600;                //剩余秒数
        long minutes = second / 60;            //转换分钟
        second = second % 60;                //剩余秒数
        if (days > 0) {
            return days + "天" + hours + "小时" + minutes + "分" + second + "秒";
        } else {
            return hours + "小时" + minutes + "分" + second + "秒";
        }
    }
}
