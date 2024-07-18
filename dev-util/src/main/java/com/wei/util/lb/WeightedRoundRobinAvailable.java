package com.wei.util.lb;

import java.util.ArrayList;
import java.util.List;

/**
 * 加权轮询算法：加入存活状态，降权使宕机权重降低，从而不会被选中
 */
public class WeightedRoundRobinAvailable {

    private static List<ServerNode> serverNodes = new ArrayList<>();

    // 准备模拟数据
    static {
        serverNodes.add(new ServerNode("192.168.1.101", 1));// 默认为true
        serverNodes.add(new ServerNode("192.168.1.102", 3, false));
        serverNodes.add(new ServerNode("192.168.1.103", 2));
    }

    /**
     * 按照当前权重（currentWeight）最大值获取IP
     *
     * @return ServerNode
     */
    public ServerNode selectNode() {
        if (serverNodes.size() <= 0) return null;
        if (serverNodes.size() == 1)
            return (serverNodes.get(0).isAvailable()) ? serverNodes.get(0) : null;

        // 权重之和
        Integer totalWeight = 0;
        ServerNode nodeOfMaxWeight = null; // 保存轮询选中的节点信息
        synchronized (serverNodes) {
            StringBuffer sb1 = new StringBuffer();
            StringBuffer sb2 = new StringBuffer();
            sb1.append(Thread.currentThread().getName() + "==加权轮询--[当前权重]值的变化：" + printCurrentWeight(serverNodes));
            // 有限权重总和可能发生变化
            for (ServerNode serverNode : serverNodes) {
                totalWeight += serverNode.getEffectiveWeight();
            }

            // 选出当前权重最大的节点
            ServerNode tempNodeOfMaxWeight = serverNodes.get(0);
            for (ServerNode serverNode : serverNodes) {
                if (serverNode.isAvailable()) {
                    serverNode.onInvokeSuccess();//提权
                    sb2.append(Thread.currentThread().getName() + "==[正常节点]：" + serverNode + "\n");
                } else {
                    serverNode.onInvokeFault();//降权
                    sb2.append(Thread.currentThread().getName() + "==[宕机节点]：" + serverNode + "\n");
                }

                tempNodeOfMaxWeight = tempNodeOfMaxWeight.compareTo(serverNode) > 0 ? tempNodeOfMaxWeight : serverNode;
            }
            // 必须new个新的节点实例来保存信息，否则引用指向同一个堆实例，后面的set操作将会修改节点信息
            nodeOfMaxWeight = new ServerNode(tempNodeOfMaxWeight.getIp(), tempNodeOfMaxWeight.getWeight(), tempNodeOfMaxWeight.isAvailable());
            nodeOfMaxWeight.setEffectiveWeight(tempNodeOfMaxWeight.getEffectiveWeight());
            nodeOfMaxWeight.setCurrentWeight(tempNodeOfMaxWeight.getCurrentWeight());

            // 调整当前权重比：按权重（effectiveWeight）的比例进行调整，确保请求分发合理。
            tempNodeOfMaxWeight.setCurrentWeight(tempNodeOfMaxWeight.getCurrentWeight() - totalWeight);
            sb1.append(" -> " + printCurrentWeight(serverNodes));

            serverNodes.forEach(serverNode -> serverNode.setCurrentWeight(serverNode.getCurrentWeight() + serverNode.getEffectiveWeight()));

            sb1.append(" -> " + printCurrentWeight(serverNodes));
            System.out.print(sb2);  //所有节点的当前信息
            System.out.println(sb1); //打印当前权重变化过程
        }
        return nodeOfMaxWeight;
    }

    // 格式化打印信息
    private String printCurrentWeight(List<ServerNode> serverNodes) {
        StringBuffer stringBuffer = new StringBuffer("[");
        serverNodes.forEach(node -> stringBuffer.append(node.getCurrentWeight() + ","));
        return stringBuffer.substring(0, stringBuffer.length() - 1) + "]";
    }

    // 并发测试：两个线程循环获取节点
    public static void main(String[] args) throws InterruptedException {
        // 循环次数
        int loop = 18;

        new Thread(() -> {
            WeightedRoundRobinAvailable weightedRoundRobin1 = new WeightedRoundRobinAvailable();
            for (int i = 1; i <= loop; i++) {
                ServerNode serverNode = weightedRoundRobin1.selectNode();
                System.out.println(Thread.currentThread().getName() + "==第" + i + "次轮询选中[当前权重最大]的节点：" + serverNode + "\n");
            }
        }).start();
        //
        new Thread(() -> {
            WeightedRoundRobinAvailable weightedRoundRobin2 = new WeightedRoundRobinAvailable();
            for (int i = 1; i <= loop; i++) {
                ServerNode serverNode = weightedRoundRobin2.selectNode();
                System.out.println(Thread.currentThread().getName() + "==第" + i + "次轮询选中[当前权重最大]的节点：" + serverNode + "\n");
            }
        }).start();

        //main 线程睡了一下，再偷偷把 所有宕机 拉起来：模拟服务器恢复正常
        Thread.sleep(5);
        for (ServerNode serverNode : serverNodes) {
            if (!serverNode.isAvailable())
                serverNode.setIsAvailable(true);
        }
    }
}