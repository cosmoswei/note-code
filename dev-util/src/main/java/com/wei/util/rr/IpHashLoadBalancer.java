package com.wei.util.rr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IpHashLoadBalancer {
    private List<String> servers;
    private Map<String, Integer> serverIpHashes;

    public IpHashLoadBalancer(List<String> servers) {
        this.servers = new ArrayList<>(servers);
        this.serverIpHashes = new HashMap<>();
        for (String server : servers) {
            int hash = server.hashCode();
            serverIpHashes.put(server, hash);
        }
    }

    public String getServer(String clientIp) {
        int clientIpHash = clientIp.hashCode();
        int index = clientIpHash % servers.size();
        return servers.get(index);
    }

    public static void main(String[] args) {
        List<String> servers = new ArrayList<>();
        servers.add("Server A");
        servers.add("Server B");
        servers.add("Server C");

        IpHashLoadBalancer loadBalancer = new IpHashLoadBalancer(servers);

        // 模拟5个客户端IP
        String[] clientIps = {"192.168.0.1", "192.168.0.2", "192.168.0.3", "192.168.0.4", "192.168.0.5"};

        // 模拟5次请求
        for (int i = 0; i < 5; i++) {
            String clientIp = clientIps[i];
            String server = loadBalancer.getServer(clientIp);
            System.out.println("Request from " + clientIp + " routed to " + server);
        }
    }
}
