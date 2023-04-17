package com.wei.redis;

import com.wei.entity.Student;
import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

public class RedissonDemo {

    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://huangxuwei.com:6379")
                .setPassword("huangxuwei")
                .setDatabase(0);
        //获取客户端
        RedissonClient redissonClient = Redisson.create(config);
        //获取所有的key
        redissonClient.getKeys().getKeys().forEach(key -> System.out.println(key));
        //字符串操作
        RBucket<String> rBucket = redissonClient.getBucket("strKey");
        // 设置value和key的有效期
        rBucket.set("张三", 30, TimeUnit.SECONDS);
        // 通过key获取value
        System.out.println(redissonClient.getBucket("strKey").get());

        //Student对象
        Student student = new Student();
        student.setId(1L);
        student.setName("张三");
        student.setAge(18);
        //对象操作
        RBucket<Student> rBucket2 = redissonClient.getBucket("objKey");
        // 设置value和key的有效期
        rBucket2.set(student, 30, TimeUnit.SECONDS);
        // 通过key获取value
        System.out.println(redissonClient.getBucket("objKey").get());

        //关闭客户端
        RBloomFilter rBloomFilter = redissonClient.getBloomFilter("seqId");
        // 初始化预期插入的数据量为10000和期望误差率为0.01
        rBloomFilter.tryInit(10000, 0.01);
        // 插入部分数据
        rBloomFilter.add("100");
        rBloomFilter.add("200");
        rBloomFilter.add("300");
        //设置过期时间
        rBloomFilter.expire(30, TimeUnit.SECONDS);
        // 判断是否存在
        System.out.println("=====");
        System.out.println(rBloomFilter.contains("300"));
        System.out.println(rBloomFilter.contains("200"));
        System.out.println(rBloomFilter.contains("999"));
        //哈希操作
        RMap<String, String> rMap = redissonClient.getMap("mapkey");
        // 设置map中key-value
        rMap.put("id", "123");
        rMap.put("name", "赵四");
        rMap.put("age", "50");

        //设置过期时间
        rMap.expire(30, TimeUnit.SECONDS);
        // 通过key获取value
        System.out.println(redissonClient.getMap("mapkey").get("name"));

        //有序集合操作
        RSortedSet<Student> sortSetkey = redissonClient.getSortedSet("sortSetkey");

        Student student1 = new Student();
        student1.setId(1L);
        student1.setName("张三");
        student1.setAge(18);
        sortSetkey.add(student1);

        Student student2 = new Student();
        student2.setId(2L);
        student2.setName("李四");
        student2.setAge(19);
        sortSetkey.add(student2);

        // 通过key获取value
        System.out.println(redissonClient.getSortedSet("sortSetkey"));

        final String lockKey = "aaaa";
        //通过redis的自增获取序号
        RAtomicLong atomicLong = redissonClient.getAtomicLong(lockKey);
        //设置过期时间
        atomicLong.expire(30, TimeUnit.SECONDS);
        // 获取值
        System.out.println(atomicLong.incrementAndGet());

        //获取锁对象实例
        final String lockKey2 = "abc";
        RLock rLock = redissonClient.getLock(lockKey2);

        try {
            //尝试5秒内获取锁，如果获取到了，最长60秒自动释放
            boolean res = rLock.tryLock(5L, 60L, TimeUnit.SECONDS);
            if (res) {
                //成功获得锁，在这里处理业务
                System.out.println("获取锁成功");
            }
        } catch (Exception e) {
            System.out.println("获取锁失败，失败原因：" + e.getMessage());
        } finally {
            //无论如何, 最后都要解锁
            rLock.unlock();
        }

        redissonClient.shutdown();
    }
}