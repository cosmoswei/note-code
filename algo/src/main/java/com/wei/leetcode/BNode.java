package com.wei.leetcode;

public class BNode {
    public int val;
    public BNode left;
    public BNode right;
    public BNode next;

    public BNode() {}
    
    public BNode(int _val) {
        val = _val;
    }

    public BNode(int _val, BNode _left, BNode _right, BNode _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};