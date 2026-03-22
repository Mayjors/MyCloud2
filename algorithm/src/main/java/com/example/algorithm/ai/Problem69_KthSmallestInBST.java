package com.example.algorithm.ai;

import java.util.Stack;

/**
 * 69. 二叉搜索树中第 K 小的元素
 * 给定一个二叉搜索树的根节点 root，和一个整数 k，
 * 请你设计一个算法查找其中第 k 小的元素（从 1 开始计数）。
 * 
 * 示例:
 * 输入: root = [3,1,4,null,2], k = 1
 * 输出: 1
 */
public class Problem69_KthSmallestInBST {
    
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    
    // 方法 1: 中序遍历
    private int count = 0;
    private int result = -1;
    
    public int kthSmallest(TreeNode root, int k) {
        count = 0;
        result = -1;
        inorder(root, k);
        return result;
    }
    
    private void inorder(TreeNode node, int k) {
        if (node == null || result != -1) return;
        
        inorder(node.left, k);
        count++;
        if (count == k) {
            result = node.val;
            return;
        }
        inorder(node.right, k);
    }
    
    // 方法 2: 迭代中序遍历
    public int kthSmallestIterative(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            k--;
            if (k == 0) return current.val;
            current = current.right;
        }
        
        return -1;
    }
}
