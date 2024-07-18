package com.wei.util.lb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class RandomLoadBalance {
    private List<String> servers;
    private Random random;

    public RandomLoadBalance(List<String> servers) {
        this.servers = new ArrayList<>(servers);
        this.random = new Random();
    }

    public String getNextServer() {
        int index = random.nextInt(servers.size());
        return servers.get(index);
    }

    public static void main(String[] args) {
        List<String> servers = new ArrayList<>();
        servers.add("Server A");
        servers.add("Server B");
        servers.add("Server C");
        RandomLoadBalance loadBalancer = new RandomLoadBalance(servers);
        List<String> res = new ArrayList<>(1000000);
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
