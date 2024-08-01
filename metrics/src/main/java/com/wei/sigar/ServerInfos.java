package com.wei.sigar;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Accessors(chain = true)
public class ServerInfos {

    private ServerInfo serverInfo;

    private SystemInfo systemInfo;

    private List<CpuInfo> cpuInfo;

    private JvmInfo jvmInfo;

    private MemoryInfo memoryInfo;

    /**
     * Title:  服务器信息
     *
     * @author Fking<fsyvip666 @ gmail.com>
     * @since 2022/1/7
     */
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    @Accessors(chain = true)
    public static class ServerInfo {
        //用户名
        private String userName;
        //计算机名
        private String computerName;
        //计算机域名
        private String computerDomain;
        //本机ip
        private String serverIp;
        //本机主机名
        private String hostName;
        //用户的主目录
        private String userHome;
        //用户的当前工作目录
        private String userDir;
    }

    /**
     * Title:  系统信息
     *
     * @author Fking<fsyvip666 @ gmail.com>
     * @since 2022/1/7
     */
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    @Accessors(chain = true)
    public static class SystemInfo {
        //操作系统名称
        private String vendorName;
        //内核构架
        private String arch;
        //操作系统的描述
        private String description;
        //操作系统的版本号
        private String version;
    }

    /**
     * Title:  CPU信息
     *
     * @author Fking<fsyvip666 @ gmail.com>
     * @since 2022/1/7
     */
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    @Accessors(chain = true)
    public static class CpuInfo {
        //CPU的总量MHz
        private int mhz;
        //CPU的厂商
        private String vendor;
        //CPU型号类别
        private String model;
        //缓冲缓存数量
        private long cacheSize;
        //CPU的用户使用率
        private double freqUser;
        //CPU的系统使用率
        private double freqSys;
        //CPU的当前等待率
        private double freqWait;
        //CPU的当前错误率
        private double freqNice;
        //CPU的当前空闲率
        private double freqIdle;
        //CPU总的使用率
        private double freqCombined;
    }

    /**
     * Title:  JVM信息
     *
     * @author Fking<fsyvip666 @ gmail.com>
     * @since 2022/1/7
     */
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    @Accessors(chain = true)
    public static class JvmInfo {
        //JVM可以使用的总内存
        private long totalMemory;
        //JVM可以使用的剩余内存
        private long freeMemory;
        //JVM可以使用的处理器个数
        private int availableProcessors;
        //Java的运行环境版本
        private String version;
        //Java的运行环境供应商
        private String vendor;
        //Java的安装路径
        private String home;
        //Java运行时环境规范版本
        private String specificationVersion;
        //Java的类路径
        private String classPath;
        //Java加载库时搜索的路径列表
        private String libraryPath;
        //默认的临时文件路径
        private String tmpdir;
        //扩展目录的路径
        private String dirs;
    }

    /**
     * Title:  内存信息
     *
     * @author Fking<fsyvip666 @ gmail.com>
     * @since 2022/1/7
     */
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    @Accessors(chain = true)
    public static class MemoryInfo {
        //内存总量
        private long memoryTotal;
        //当前内存使用量
        private long memoryUsed;
        //当前内存剩余量
        private long memoryFree;
        //交换区总量
        private long swapTotal;
        //当前交换区使用量
        private long swapUsed;
        //当前交换区剩余量
        private long swapFree;
    }
}
