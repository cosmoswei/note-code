package com.wei;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
public class RedisTest {
    @Resource
    RedissonClient redissonClient;

    @Test
    void redisTest() {
        RBucket<Object> bucket = redissonClient.getBucket("city", new StringCodec("utf-8"));
        bucket.set("长沙");
    }

    @Test
    void lock() {
        //获取一把锁
        RLock lock = redissonClient.getLock("my-lock");

        //加锁
        lock.lock();
        //锁的自动续期，如果业务执行时间超长，运行期间会自动给锁续期30秒时间，不用担心业务时间长，锁自动过期
        //加锁的业务只要运行完成，就不会给当前锁续期，即使不手动解锁，锁默认在30秒后也会自动删除
        try {
            System.out.println("加锁成功，执行业务.... " + Thread.currentThread().getId());
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //手动解锁
            System.out.println("解锁..." + Thread.currentThread().getId());
            lock.unlock();
        }
    }

}
