package com.wei.util.rr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeastResponseTimeLoadBalancer {
    private List<String> servers;
    private Map<String, Integer> responseTimes;

    public LeastResponseTimeLoadBalancer(List<String> servers) {
        this.servers = new ArrayList<>(servers);
        this.responseTimes = new HashMap<>();
        for (String server : servers) {
            responseTimes.put(server, 0);
        }
    }

    public String getNextServer() {
        String selectedServer = null;
        int minResponseTime = Integer.MAX_VALUE;

        for (String server : servers) {
            int currentResponseTime = responseTimes.get(server);
            if (currentResponseTime < minResponseTime) {
                selectedServer = server;
                minResponseTime = currentResponseTime;
            }
        }

        if (selectedServer != null) {
            int currentResponseTime = responseTimes.get(selectedServer);
            responseTimes.put(selectedServer, currentResponseTime + 1);
        }

        return selectedServer;
    }

    public static void main(String[] args) {
        List<String> servers = new ArrayList<>();
        servers.add("Server A");
        servers.add("Server B");
        servers.add("Server C");

        LeastResponseTimeLoadBalancer loadBalancer = new LeastResponseTimeLoadBalancer(servers);

        // 模拟10次请求
        for (int i = 0; i < 10; i++) {
            String server = loadBalancer.getNextServer();
            System.out.println("Request " + (i + 1) + " routed to " + server);
        }
    }
}
