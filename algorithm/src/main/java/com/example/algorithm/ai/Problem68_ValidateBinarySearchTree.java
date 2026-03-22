package com.example.algorithm.ai;

/**
 * 68. 验证二叉搜索树
 * 给你一个二叉树的根节点 root，判断其是否是一个有效的二叉搜索树。
 * 有效二叉搜索树定义如下：
 * - 节点的左子树只包含小于当前节点的数。
 * - 节点的右子树只包含大于当前节点的数。
 * - 所有左子树和右子树自身必须也是二叉搜索树。
 * 
 * 示例:
 * 输入: root = [2,1,3]
 * 输出: true
 */
public class Problem68_ValidateBinarySearchTree {
    
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
    
    // 方法 1: 递归验证范围
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    private boolean isValidBST(TreeNode node, long min, long max) {
        if (node == null) return true;
        
        if (node.val <= min || node.val >= max) return false;
        
        return isValidBST(node.left, min, node.val) 
            && isValidBST(node.right, node.val, max);
    }
    
    // 方法 2: 中序遍历
    private long prev = Long.MIN_VALUE;
    
    public boolean isValidBSTInorder(TreeNode root) {
        if (root == null) return true;
        
        if (!isValidBSTInorder(root.left)) return false;
        
        if (root.val <= prev) return false;
        prev = root.val;
        
        return isValidBSTInorder(root.right);
    }
}
