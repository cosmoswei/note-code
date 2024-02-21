package com.wei.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class TreePrinter {
    public void printTree(Node root) {
        if (root == null) {
            return;
        }

        System.out.println(root.value);
        for (Node child : root.children) {
            printTree(child);
        }
    }

    public void printTreeByLevel(Node root) {
        if (root == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                Node currentNode = queue.poll();
                System.out.print(currentNode.value + " ");
                for (Node child : currentNode.children) {
                    queue.offer(child);
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);

        root.children.add(node2);
        root.children.add(node3);
        root.children.add(node4);
        node3.children.add(node5);
        node4.children.add(node6);

        TreePrinter printer = new TreePrinter();
        printer.printTree(root);
        System.out.println("---------");
        printer.printTreeByLevel(root);
    }
}
