package com.example.algorithm.ai;

/**
 * 165. 打家劫舍 III
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。
 * 这个地区只有一个入口，我们称之为"根"。除了"根"之外，每栋房子有且只有一个"父"房子与之相连。
 * 
 * 示例:
 * 输入: root = [3,2,3,null,3,null,1]
 * 输出: 7
 */
public class Problem165_HouseRobber3 {
    
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
    
    public int rob(TreeNode root) {
        int[] result = robHelper(root);
        return Math.max(result[0], result[1]);
    }
    
    // 返回 [不偷当前节点的最大值，偷当前节点的最大值]
    private int[] robHelper(TreeNode node) {
        if (node == null) return new int[]{0, 0};
        
        int[] left = robHelper(node.left);
        int[] right = robHelper(node.right);
        
        // 不偷当前节点：左右子节点可以偷或不偷
        int notRob = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        
        // 偷当前节点：左右子节点都不能偷
        int rob = node.val + left[0] + right[0];
        
        return new int[]{notRob, rob};
    }
}
