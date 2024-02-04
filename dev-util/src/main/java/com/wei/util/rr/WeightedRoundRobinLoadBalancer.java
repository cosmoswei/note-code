package com.wei.util.rr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Deprecated
public class WeightedRoundRobinLoadBalancer {
    private List<String> servers;
    private Map<String, Integer> weights;
    private int currentIndex;

    public WeightedRoundRobinLoadBalancer(List<String> servers, Map<String, Integer> weights) {
        this.servers = new ArrayList<>(servers);
        this.weights = new HashMap<>(weights);
        this.currentIndex = 0;
    }

    public String getNextServer() {
        int totalWeight = weights.values().stream().mapToInt(Integer::intValue).sum();
        int maxWeight = weights.values().stream().mapToInt(Integer::intValue).max().orElse(0);

        while (true) {
            currentIndex = (currentIndex + 1) % servers.size();
            String server = servers.get(currentIndex);
            int currentWeight = weights.get(server);

            if (currentWeight > 0) {
                weights.put(server, currentWeight - totalWeight);
                return server;
            }

            if (currentWeight >= maxWeight) {
                return server;
            }
        }
    }

    public static void main(String[] args) {
        List<String> servers = new ArrayList<>();
        servers.add("Server A");
        servers.add("Server B");
        servers.add("Server C");

        Map<String, Integer> weights = new HashMap<>();
        weights.put("Server A", 3);
        weights.put("Server B", 2);
        weights.put("Server C", 1);

        WeightedRoundRobinLoadBalancer loadBalancer = new WeightedRoundRobinLoadBalancer(servers, weights);

        // 模拟10次请求
        for (int i = 0; i < 10; i++) {
            String server = loadBalancer.getNextServer();
            System.out.println("Request " + (i + 1) + " routed to " + server);
        }
    }
}
