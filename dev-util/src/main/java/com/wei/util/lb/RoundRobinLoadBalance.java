package com.wei.util.lb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RoundRobinLoadBalance {
    private List<String> servers;
    private int currentIndex;

    public RoundRobinLoadBalance(List<String> servers) {
        this.servers = new ArrayList<>(servers);
        this.currentIndex = 0;
    }

    public String getNextServer() {
        String server = servers.get(currentIndex);
        currentIndex = (currentIndex + 1) % servers.size();
        return server;
    }

    public static void main(String[] args) {
        List<String> servers = new ArrayList<>();
        servers.add("Server A");
        servers.add("Server B");
        servers.add("Server C");

        RoundRobinLoadBalance loadBalancer = new RoundRobinLoadBalance(servers);

        // 模拟10次请求
//        for (int i = 0; i < 1000000; i++) {
//            String server = loadBalancer.getNextServer();
//            System.out.println("Request " + (i + 1) + " routed to " + server);
//        }

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
