package com.wei.util.rr;

import java.util.*;

/**
 * 简单版的加权轮询
 */
public class WeightedRoundRobinSimple {
    private static Integer index = 0;
    private static Map<String, Integer> mapNodes = new HashMap<>();
    // 记录轮询输出结果
    private static StringBuffer stringBuffer = new StringBuffer();

    // 准备模拟数据
    static {
        mapNodes.put("192.168.1.101", 1);
        mapNodes.put("192.168.1.102", 3);
        mapNodes.put("192.168.1.103", 2);
        /* -- 以下代码只为了方便查看所有节点，删除不影响 -- S */
        List<String> nodes = new ArrayList<>();
        Iterator<Map.Entry<String, Integer>> iterator = mapNodes.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            String key = entry.getKey();
            for (int i = 0; i < entry.getValue(); i++) {
                nodes.add(key);
            }
        }
        System.out.println("简单版的加权轮询：" + nodes);//打印所有节点
        /* -- 以上代码只为了方便查看所有节点，删除不影响-- E */
    }

    // 关键代码：类似于二维数组 降维成 一维数组，然后使用普通轮询
    public String selectNode() {
        List<String> nodes = new ArrayList<>();
        Iterator<Map.Entry<String, Integer>> iterator = mapNodes.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            String key = entry.getKey();
            for (int i = 0; i < entry.getValue(); i++) {
                nodes.add(key);
            }
        }
        String ip = null;
//      之前写错的代码
//      synchronized (index){
        synchronized (WeightedRoundRobinSimple.class) {
            // 下标复位
            if (index >= nodes.size()) index = 0;
            ip = nodes.get(index);
            stringBuffer.append(Thread.currentThread().getName() + "==获取节点：" + ip + "\n");
            index++;
        }
        return ip;
    }

    // 并发测试：两个线程循环获取节点
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            WeightedRoundRobinSimple roundRobin1 = new WeightedRoundRobinSimple();
            for (int i = 1; i <= 6; i++) {
                roundRobin1.selectNode();
                try {
                    // 模拟业务处理耗时
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            WeightedRoundRobinSimple roundRobin1 = new WeightedRoundRobinSimple();
            for (int i = 1; i <= 6; i++) {
                roundRobin1.selectNode();
                try {
                    // 模拟业务处理耗时
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // 等待轮询完成，统一输出
        Thread.sleep(3000);
        System.out.println(stringBuffer.toString());
    }
}