package com.example.algorithm.tujie;

public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {

    }
    public Node(int val) {
        this.val = val;
    }
    public Node(int val, Node left, Node right, Node _next) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.next = _next;
    }

    /**
     * 前序
     * @param root
     */
    public static void pre(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val);
        pre(root.left);
        pre(root.right);
    }

    /**
     * 中序
     * @param root
     */
    public static void cur(Node root) {
        if (root == null) {
            return;
        }
        pre(root.left);
        System.out.print(root.val);
        pre(root.right);
    }

    /**
     * 后序
     * @param root
     */
    public static void nxt(Node root) {
        if (root == null) {
            return;
        }
        pre(root.left);
        pre(root.right);
        System.out.print(root.val);
    }

    public static void main(String[] args) {
        Node root = new Node(4);
        Node temp1 = new Node(1);
        Node temp2 = new Node(2);
        Node temp3 = new Node(3);
        Node temp5 = new Node(5);
        root.left = temp2;
        root.right = temp5;
        temp2.right = temp3;
        temp2.left = temp1;
        pre(root);
    }
}
