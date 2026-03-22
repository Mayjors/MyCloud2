package com.example.algorithm.ai;

/**
 * 18. 路径总和
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum。
 * 判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和 targetSum。
 * 
 * 示例:
 * 输入: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * 输出: true
 */
public class Problem18_PathSum {
    
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
    
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        
        // 叶子节点
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }
        
        int newSum = targetSum - root.val;
        return hasPathSum(root.left, newSum) || hasPathSum(root.right, newSum);
    }
}
