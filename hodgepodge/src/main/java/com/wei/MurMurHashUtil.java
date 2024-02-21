package com.wei;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * @Author: huangyibo
 * @Date: 2022/7/18 17:42
 * @Description: MurMurHash 一致性Hash的一种算法 高效低碰撞率
 */

public class MurMurHashUtil {

    /**
     * MurMurHash算法, 性能高, 碰撞率低
     * @param key byte[]
     * @return Long
     */
    public static Long hash(byte[] key) {

        ByteBuffer buf = ByteBuffer.wrap(key);
        int seed = 0x1234ABCD;

        ByteOrder byteOrder = buf.order();
        buf.order(ByteOrder.LITTLE_ENDIAN);

        long m = 0xc6a4a7935bd1e995L;
        int r = 47;

        long h = seed ^ (buf.remaining() * m);

        long k;
        while (buf.remaining() >= 8) {
            k = buf.getLong();

            k *= m;
            k ^= k >>> r;
            k *= m;

            h ^= k;
            h *= m;
        }

        if (buf.remaining() > 0) {
            ByteBuffer finish = ByteBuffer.allocate(8).order(
                    ByteOrder.LITTLE_ENDIAN);
            // for big-endian version, do this first:
            // finish.position(8-buf.remaining());
            finish.put(buf).rewind();
            h ^= finish.getLong();
            h *= m;
        }

        h ^= h >>> r;
        h *= m;
        h ^= h >>> r;

        buf.order(byteOrder);
        return h;
    }


    /**
     * 计算hash值
     * @param key String
     * @return Long
     */
    public static Long hash(String key) {
        return hash(key.getBytes());
    }


    /**
     * Long转换成无符号长整型（C中数据类型）
     * Java的数据类型long与C语言中无符号长整型uint64_t有区别，导致Java输出版本存在负数
     * @param value
     * @return
     */
    public static Long readUnsignedLong(long value) {
        if (value >= 0){
            return value;
        }
        return value & 0x7fffffffffffffffL;
    }


    /**
     * 返回无符号murmur hash值
     * @param key
     * @return
     */
    public static Long hashUnsigned(String key) {
        return readUnsignedLong(hash(key));
    }


    /**
     * 返回无符号murmur hash值
     * @param key
     * @return
     */
    public static Long hashUnsigned(byte[] key) {
        return readUnsignedLong(hash(key));
    }

}