package com.wei.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public int value;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val, List<Node> _children) {
        value = _val;
        children = _children;
    }

    public Node(Integer value) {
        this.value = value;
        this.children = new ArrayList<>();
    }
};