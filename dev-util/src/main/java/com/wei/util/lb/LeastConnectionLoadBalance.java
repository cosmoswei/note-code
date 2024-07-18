package com.wei.util.lb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeastConnectionLoadBalance {
    private List<String> servers;
    private Map<String, Integer> connections;

    public LeastConnectionLoadBalance(List<String> servers) {
        this.servers = new ArrayList<>(servers);
        this.connections = new HashMap<>();
        for (String server : servers) {
            connections.put(server, 0);
        }
    }

    public String getNextServer() {
        String selectedServer = null;
        int minConnections = Integer.MAX_VALUE;

        for (String server : servers) {
            int currentConnections = connections.get(server);
            if (currentConnections < minConnections) {
                selectedServer = server;
                minConnections = currentConnections;
            }
        }

        if (selectedServer != null) {
            int currentConnections = connections.get(selectedServer);
            connections.put(selectedServer, currentConnections + 1);
        }

        return selectedServer;
    }

    public static void main(String[] args) {
        List<String> servers = new ArrayList<>();
        servers.add("Server A");
        servers.add("Server B");
        servers.add("Server C");

        LeastConnectionLoadBalance loadBalancer = new LeastConnectionLoadBalance(servers);

        // 模拟10次请求
        for (int i = 0; i < 10; i++) {
            String server = loadBalancer.getNextServer();
            System.out.println("Request " + (i + 1) + " routed to " + server);
        }
    }
}
