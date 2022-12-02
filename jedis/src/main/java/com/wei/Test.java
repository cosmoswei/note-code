//package com.wei;
//
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//
//public class Test {
//
//    JedisPool jedisPool = new JedisPool();
//
//    public int delnx(String key, String val) {
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            if (jedis == null) {
//                return 0;
//            }
//
//            //if redis.call('get','orderkey')=='1111' then return redis.call('del','orderkey') else return 0 end
//            StringBuilder sbScript = new StringBuilder();
//            sbScript.append("if redis.call('get','").append(key).append("')").append("=='").append(val).append("'").
//                    append(" then ").
//                    append("    return redis.call('del','").append(key).append("')").
//                    append(" else ").
//                    append("    return 0").
//                    append(" end");
//
//            return Integer.valueOf(jedis.eval(sbScript.toString()).toString());
//        } catch (Exception ex) {
//        } finally {
//            if (jedis != null) {
//                jedis.close();
//            }
//        }
//        return 0;
//    }
//}
