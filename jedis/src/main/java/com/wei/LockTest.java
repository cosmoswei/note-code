//package com.wei;
//
//
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//
//public class LockTest {
//
//    JedisPool jedisPool = new JedisPool();
//
//    public boolean setnx(String key, String val) {
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            if (jedis == null) {
//                return false;
//            }
//            return jedis.set(key, val, "NX", "PX", 1000 * 60).
//                    equalsIgnoreCase("ok");
//        } catch (Exception ex) {
//        } finally {
//            if (jedis != null) {
//                jedis.close();
//            }
//        }
//        return false;
//    }
//
//}
