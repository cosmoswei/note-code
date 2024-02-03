package com.wei.util.rr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class WeightedRandomLoadBalancer {
    private List<String> servers;
    private Map<String, Integer> weights;
    private Random random;

    public WeightedRandomLoadBalancer(List<String> servers, Map<String, Integer> weights) {
        this.servers = new ArrayList<>(servers);
        this.weights = new HashMap<>(weights);
        this.random = new Random();
    }

    public String getNextServer() {
        int totalWeight = weights.values().stream().mapToInt(Integer::intValue).sum();
        int weightSum = 0;
        int randomWeight = random.nextInt(totalWeight);

        for (String server : servers) {
            weightSum += weights.get(server);

            if (randomWeight < weightSum) {
                return server;
            }
        }

        // 如果没有找到合适的服务器，则返回最后一个服务器
        return servers.get(servers.size() - 1);
    }

    public static void main(String[] args) {
        List<String> servers = new ArrayList<>();
        servers.add("Server A");
        servers.add("Server B");
        servers.add("Server C");

        Map<String, Integer> weights = new HashMap<>();
        weights.put("Server A", 2);
        weights.put("Server B", 1);
        weights.put("Server C", 1);

        WeightedRandomLoadBalancer loadBalancer = new WeightedRandomLoadBalancer(servers, weights);

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
