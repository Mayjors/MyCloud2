package com.example.algorithm.zijie;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {

    }
    public TreeNode(int val) {
        this.val = val;
    }
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    /**
     * 前序
     * @param root
     */
    public static void pre(TreeNode root) {
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
    public static void cur(TreeNode root) {
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
    public static void nxt(TreeNode root) {
        if (root == null) {
            return;
        }
        pre(root.left);
        pre(root.right);
        System.out.print(root.val);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode temp1 = new TreeNode(1);
        TreeNode temp2 = new TreeNode(2);
        TreeNode temp3 = new TreeNode(3);
        TreeNode temp5 = new TreeNode(5);
        root.left = temp2;
        root.right = temp5;
        temp2.right = temp3;
        temp2.left = temp1;
        pre(root);
    }
}
