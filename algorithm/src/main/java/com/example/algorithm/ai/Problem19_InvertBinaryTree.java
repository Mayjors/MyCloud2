package com.example.algorithm.ai;

/**
 * 19. 翻转二叉树
 * 给你一棵二叉树的根节点 root，翻转这棵二叉树，并返回其根节点。
 * 
 * 示例:
 * 输入: root = [4,2,7,1,3,6,9]
 * 输出: [4,7,2,9,6,3,1]
 */
public class Problem19_InvertBinaryTree {
    
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
    
    // 递归法
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        
        root.left = right;
        root.right = left;
        
        return root;
    }
    
    // 迭代法 (BFS)
    public TreeNode invertTreeIterative(TreeNode root) {
        if (root == null) return null;
        
        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            
            // 交换左右子树
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        
        return root;
    }
}
