package com.wei.util.lb;

/**
 * String ip：负载IP
 * final Integer weight：权重，保存配置的权重
 * Integer effectiveWeight：有效权重，轮询的过程权重可能变化
 * Integer currentWeight：当前权重，比对该值大小获取节点
 *   第一次加权轮询时：currentWeight = weight = effectiveWeight
 *   后面每次加权轮询时：currentWeight 的值都会不断变化，其他权重不变
 * Boolean isAvailable：是否存活
 */
public class ServerNode implements Comparable<ServerNode>{
    private String ip;
    private final Integer weight;
    private Integer effectiveWeight;
    private Integer currentWeight;
    private Boolean isAvailable;

    public ServerNode(String ip, Integer weight){
        this(ip,weight,true);
    }
    public ServerNode(String ip, Integer weight,Boolean isAvailable){
        this.ip = ip;
        this.weight = weight;
        this.effectiveWeight = weight;
        this.currentWeight = weight;
        this.isAvailable = isAvailable;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getEffectiveWeight() {
        return effectiveWeight;
    }

    public void setEffectiveWeight(Integer effectiveWeight) {
        this.effectiveWeight = effectiveWeight;
    }

    public Integer getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(Integer currentWeight) {
        this.currentWeight = currentWeight;
    }

    public Boolean isAvailable() {
        return isAvailable;
    }
    public void setIsAvailable(Boolean isAvailable){
        this.isAvailable = isAvailable;
    }

    // 每成功一次，恢复有效权重1，不超过配置的起始权重
    public void onInvokeSuccess(){
        if(effectiveWeight < weight) effectiveWeight++;
    }
    // 每失败一次，有效权重减少1，无底线的减少
    public void onInvokeFault(){
        effectiveWeight--;
    }

    @Override
    public int compareTo(ServerNode node) {
        return currentWeight > node.currentWeight ? 1 : (currentWeight.equals(node.currentWeight) ? 0 : -1);
    }

    @Override
    public String toString() {
        return "{ip='" + ip + "', weight=" + weight + ", effectiveWeight=" + effectiveWeight
                + ", currentWeight=" + currentWeight + ", isAvailable=" + isAvailable + "}";
    }
}