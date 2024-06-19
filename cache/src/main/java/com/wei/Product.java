package com.wei;

public class Product {
    private String name;
    private int weight;

    public Product(String s, int i) {
        name = s;
        weight = i;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return getName();
    }
}