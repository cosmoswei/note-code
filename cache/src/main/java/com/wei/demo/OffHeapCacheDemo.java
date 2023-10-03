package com.wei.demo;

import org.caffinitas.ohc.Eviction;
import org.caffinitas.ohc.OHCache;
import org.caffinitas.ohc.OHCacheBuilder;

public class OffHeapCacheDemo {

    public static void main(String[] args) {
        OHCache<String, String> ohCache = OHCacheBuilder.<String, String>newBuilder()
                .keySerializer(new StringSerializer())
                .valueSerializer(new StringSerializer())
                .throwOOME(false)
                .eviction(Eviction.LRU)
                .build();

        // Exception in thread "main" java.lang.UnsatisfiedLinkError: /private/var/folders/6j/v8xspyb915v1_zm7h8ddk5j00000gn/T/jna--823046735/jna6005173551442097856.tmp: dlopen(/private/var/folders/6j/v8xspyb915v1_zm7h8ddk5j00000gn/T/jna--823046735/jna6005173551442097856.tmp, 0x0001): tried: '/private/var/folders/6j/v8xspyb915v1_zm7h8ddk5j00000gn/T/jna--823046735/jna6005173551442097856.tmp' (fat file, but missing compatible architecture (have 'i386,x86_64', need 'arm64')), '/System/Volumes/Preboot/Cryptexes/OS/private/var/folders/6j/v8xspyb915v1_zm7h8ddk5j00000gn/T/jna--823046735/jna6005173551442097856.tmp' (no such file), '/private/var/folders/6j/v8xspyb915v1_zm7h8ddk5j00000gn/T/jna--823046735/jna6005173551442097856.tmp' (fat file, but missing compatible architecture (have 'i386,x86_64', need 'arm64'))
        //  麻了，这个框架不支持M1芯片
        ohCache.put("hello", "world");
        System.out.println(ohCache.get("hello")); // world
    }
}