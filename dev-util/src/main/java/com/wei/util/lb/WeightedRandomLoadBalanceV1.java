package com.wei.util.lb;

import java.util.*;
import java.util.stream.Collectors;

public class WeightedRandomLoadBalanceV1 {
    private final Map<String, Integer> services;
    private final Random random;
    int totalWeight;

    public WeightedRandomLoadBalanceV1(Map<String, Integer> services) {
        this.services = new HashMap<>(services);
        this.random = new Random();
        totalWeight = services.values().stream().mapToInt(Integer::intValue).sum();
    }

    public String getNextServer() {
        int weightSum = 0;
        int randomWeight = random.nextInt(totalWeight);
        for (String server : services.keySet()) {
            weightSum += services.get(server);
            if (randomWeight < weightSum) {
                return server;
            }
        }
        // 如果没有找到合适的服务器，则返回最后一个服务器
        return services.keySet().iterator().next();
    }

    public static void main(String[] args) {
        Map<String, Integer> weights = new HashMap<>();
        weights.put("Server A", 2);
        weights.put("Server B", 1);
        weights.put("Server C", 1);
        WeightedRandomLoadBalanceV1 loadBalancer = new WeightedRandomLoadBalanceV1(weights);
        // 模拟10次请求
        List<String> res = new ArrayList<>(1000000);
        // 模拟10次随机选择
        for (int i = 0; i < 1000000; i++) {
            String server = loadBalancer.getNextServer();
            res.add(server);
        }
        Map<String, Long> fruitCountMap = res.stream()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        for (Map.Entry<String, Long> entry : fruitCountMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
