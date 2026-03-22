package com.example.algorithm.ai;

/**
 * 16. 二叉树的最大深度
 * 给定一个二叉树 root，返回其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 
 * 示例:
 * 输入: root = [3,9,20,null,null,15,7]
 * 输出: 3
 */
public class Problem16_MaxDepthOfBinaryTree {
    
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
    
    // DFS 递归
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
    
    // BFS 迭代
    public int maxDepthBFS(TreeNode root) {
        if (root == null) return 0;
        
        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.offer(root);
        int depth = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        
        return depth;
    }
}
