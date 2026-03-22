package com.example.algorithm.ai;

/**
 * 20. 二叉树的最近公共祖先
 * 给定一个二叉树，找到该树中两个指定节点的最近公共祖先。
 * 
 * 示例:
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出: 3
 */
public class Problem20_LowestCommonAncestor {
    
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        if (left == null) return right;
        if (right == null) return left;
        
        return root; // p 和 q 分别在左右子树
    }
}
