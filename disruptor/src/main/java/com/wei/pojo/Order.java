package com.wei.pojo;

public class Order {
    //订单ID
    private long id;
    //订单信息
    private String info;
    //订单价格
    private double price;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}