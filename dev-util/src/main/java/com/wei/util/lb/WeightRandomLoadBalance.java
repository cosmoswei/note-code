package com.wei.util.lb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;


/**
 * 权重随机负载均衡算法
 */
public class WeightRandomLoadBalance {
    private List<Node> nodes;
    private Random random;

    public WeightRandomLoadBalance() {
        this.nodes = new ArrayList<>();
        this.random = new Random();
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public Node getRandomNode() {
        int totalWeight = 0;
        for (Node node : nodes) {
            totalWeight += node.getWeight();
        }
        int randomValue = random.nextInt(totalWeight);
        int cumulativeWeight = 0;
        for (Node node : nodes) {
            cumulativeWeight += node.getWeight();
            if (randomValue < cumulativeWeight) {
                return node;
            }
        }
        // 如果权重总和为0，或者发生了舍入误差，返回最后一个节点
        return nodes.get(nodes.size() - 1);
    }

    public static void main(String[] args) {
        WeightRandomLoadBalance loadBalanceV2 = new WeightRandomLoadBalance();
        // 添加3个节点，权重分别为2、3、5
        loadBalanceV2.addNode(new Node("A", 2));
        loadBalanceV2.addNode(new Node("B", 3));
        loadBalanceV2.addNode(new Node("C", 5));
        List<String> res = new ArrayList<>(1000000);
        // 模拟10次随机选择
        for (int i = 0; i < 10000; i++) {
            Node randomNode = loadBalanceV2.getRandomNode();
            res.add(randomNode.getIp());
        }
        Map<String, Long> fruitCountMap = res.stream()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        for (Map.Entry<String, Long> entry : fruitCountMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

