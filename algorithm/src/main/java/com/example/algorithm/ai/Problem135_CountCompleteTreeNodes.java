package com.example.algorithm.ai;

/**
 * 135. 完全二叉树的节点个数
 * 给你一棵完全二叉树的根节点 root，求出该树的节点个数。
 * 
 * 示例:
 * 输入: root = [1,2,3,4,5,6]
 * 输出: 6
 */
public class Problem135_CountCompleteTreeNodes {
    
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
    
    // 方法 1: 普通遍历 O(n)
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
    
    // 方法 2: 利用完全二叉树性质 O(log^2 n)
    public int countNodesOptimized(TreeNode root) {
        if (root == null) return 0;
        
        int leftHeight = getLeftHeight(root);
        int rightHeight = getRightHeight(root);
        
        if (leftHeight == rightHeight) {
            // 满二叉树
            return (1 << leftHeight) - 1;
        } else {
            return 1 + countNodesOptimized(root.left) + countNodesOptimized(root.right);
        }
    }
    
    private int getLeftHeight(TreeNode node) {
        int height = 0;
        while (node != null) {
            height++;
            node = node.left;
        }
        return height;
    }
    
    private int getRightHeight(TreeNode node) {
        int height = 0;
        while (node != null) {
            height++;
            node = node.right;
        }
        return height;
    }
}
