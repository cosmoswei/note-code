package com.wei.util.rr;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;


/**
 * 权重随机负载均衡算法
 */
public class WeightedRandomLoadBalancerV0 {
    private List<Node> nodes;
    private Random random;

    public WeightedRandomLoadBalancerV0() {
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
        WeightedRandomLoadBalancerV0 loadBalancer = new WeightedRandomLoadBalancerV0();

        // 添加3个节点，权重分别为2、3、5
        loadBalancer.addNode(new Node("A", 2));
        loadBalancer.addNode(new Node("B", 3));
        loadBalancer.addNode(new Node("C", 5));

        List<String> res = new ArrayList<>(1000000);
        // 模拟10次随机选择
        for (int i = 0; i < 1000000; i++) {
            Node randomNode = loadBalancer.getRandomNode();
//            System.out.println("Randomly selected node: " + randomNode.getName());
            res.add(randomNode.getName());
        }
        Map<String, Long> fruitCountMap = res.stream()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));

        for (Map.Entry<String, Long> entry : fruitCountMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

class Node {
    private String name;
    private int weight;

    public Node(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

}
