package com.wei.util.lb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeastBandwidthLoadBalance {
    private List<String> servers;
    private Map<String, Integer> bandwidths;

    public LeastBandwidthLoadBalance(List<String> servers) {
        this.servers = new ArrayList<>(servers);
        this.bandwidths = new HashMap<>();
        for (String server : servers) {
            bandwidths.put(server, 0);
        }
    }

    public String getNextServer() {
        String selectedServer = null;
        int minBandwidth = Integer.MAX_VALUE;
        for (String server : servers) {
            int currentBandwidth = bandwidths.get(server);
            if (currentBandwidth < minBandwidth) {
                selectedServer = server;
                minBandwidth = currentBandwidth;
            }
        }

        if (selectedServer != null) {
            int currentBandwidth = bandwidths.get(selectedServer);
            bandwidths.put(selectedServer, currentBandwidth + 1);
        }

        return selectedServer;
    }

    public static void main(String[] args) {
        List<String> servers = new ArrayList<>();
        servers.add("Server A");
        servers.add("Server B");
        servers.add("Server C");

        LeastBandwidthLoadBalance loadBalancer = new LeastBandwidthLoadBalance(servers);

        // 模拟10次请求
        for (int i = 0; i < 10; i++) {
            String server = loadBalancer.getNextServer();
            System.out.println("Request " + (i + 1) + " routed to " + server);
        }
    }
}
