package com.example.algorithm.ai;

/**
 * 67. 二叉树中的最大路径和
 * 路径被定义为一条从树中任意节点出发，沿父节点 - 子节点连接，
 * 达到任意节点的序列。同一个节点在一条路径序列中至多出现一次。
 * 该路径至少包含一个节点，且不一定经过根节点。
 * 
 * 示例:
 * 输入: root = [1,2,3]
 * 输出: 6
 * 解释: 最优路径是 2 -> 1 -> 3，路径和为 2 + 1 + 3 = 6
 */
public class Problem67_BinaryTreeMaximumPathSum {
    
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
    
    private int maxSum = Integer.MIN_VALUE;
    
    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }
    
    private int maxGain(TreeNode node) {
        if (node == null) return 0;
        
        // 递归计算左右子节点的最大贡献值
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);
        
        // 当前节点作为路径根节点时的最大路径和
        int currentPathSum = node.val + leftGain + rightGain;
        
        // 更新全局最大值
        maxSum = Math.max(maxSum, currentPathSum);
        
        // 返回当前节点的最大贡献值（只能选择一边）
        return node.val + Math.max(leftGain, rightGain);
    }
}
