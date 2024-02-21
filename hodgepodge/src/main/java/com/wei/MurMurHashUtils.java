package com.wei;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: huangyibo
 * @Date: 2022/7/18 17:34
 * @Description: MurMurHash 一致性Hash的一种算法 高效低碰撞率
 * <p>
 * MurMurHash2、MurMurHash3、MD5 的 benchmark：
 * https://github.com/spacewander/lua-resty-murmurhash3/blob/master/README.md#when-should-i-use-it
 * @see https://www.jianshu.com/p/5eca69c42c78
 */

public class MurMurHashUtils {

    /**
     * MurMurHash算法, 性能高, 碰撞率低
     *
     * @param str String
     * @return Long
     */
    public static Long hash(String str) {
        HashFunction hashFunction = Hashing.murmur3_128();
        return hashFunction.hashString(str, StandardCharsets.UTF_8).asLong();
    }

    /**
     * Long转换成无符号长整型（C中数据类型）
     * Java的数据类型long与C语言中无符号长整型uint64_t有区别，导致Java输出版本存在负数
     *
     * @param value long
     * @return Long
     */
    public static Long readUnsignedLong(long value) {
        if (value >= 0) {
            return value;
        }
        return value & Long.MAX_VALUE;
    }

    /**
     * 返回无符号murmur hash值
     *
     * @param key
     * @return
     */
    public static Long hashUnsigned(String key) {
        return readUnsignedLong(hash(key));
    }


    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("CHONGQING");
        list.add("CHANGSHA");
        list.add("GUANGZHOU");
        list.add("SHENZHEN");
        list.add("001c4becd89f49f7b1c52fe4fcd54397");
        list.add("002b320c0e0347a8bcea7663192d8303");
        list.add("0035420515a24d9d875e6c4399bec8e3");
        list.add("00701f4c12364bedb626dd136cbc998b");
        list.add("008d028903da483fbee8d721b2e73934");
        list.add("00b9dce4ec2747c0aea6eb0494e38717");
        for (String city : list) {
            Long hash = hashUnsigned(city);
            System.out.println(hash);
            System.out.println(hash % 10);
        }
    }
}