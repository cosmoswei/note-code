package com.wei.micrometer;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.jvm.JvmGcMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmMemoryMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmThreadMetrics;
import io.micrometer.core.instrument.binder.system.ProcessorMetrics;
import io.micrometer.core.instrument.logging.LoggingMeterRegistry;
import io.micrometer.core.instrument.logging.LoggingRegistryConfig;

import java.time.Duration;

public class JvmMetricsExample {
    public static void main(String[] args) {
        // 配置LoggingMeterRegistry
        LoggingRegistryConfig config = new LoggingRegistryConfig() {
            @Override
            public String get(String key) {
                return null;
            }

            @Override
            public Duration step() {
                return Duration.ofSeconds(5); // 每5秒输出一次指标
            }
        };

        // 创建LoggingMeterRegistry
        MeterRegistry registry = new LoggingMeterRegistry(config, Clock.SYSTEM);

        // 绑定JVM指标到LoggingMeterRegistry
        new JvmMemoryMetrics().bindTo(registry);
        new JvmGcMetrics().bindTo(registry);
        new JvmThreadMetrics().bindTo(registry);
        new ProcessorMetrics().bindTo(registry);

        // 保持程序运行以便观察指标输出
        while (true) {
            try {
                System.gc(); // 手动触发GC以便观察相关指标
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
