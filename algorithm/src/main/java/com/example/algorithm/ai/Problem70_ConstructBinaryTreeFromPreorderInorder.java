package com.example.algorithm.ai;

import java.util.HashMap;
import java.util.Map;

/**
 * 70. 从前序与中序遍历序列构造二叉树
 * 给定两个整数数组 preorder 和 inorder，其中 preorder 是二叉树的先序遍历，
 * inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 * 
 * 示例:
 * 输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * 输出: [3,9,20,null,null,15,7]
 */
public class Problem70_ConstructBinaryTreeFromPreorderInorder {
    
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
    
    private Map<Integer, Integer> inorderMap;
    private int preorderIndex;
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        preorderIndex = 0;
        
        return build(preorder, 0, inorder.length - 1);
    }
    
    private TreeNode build(int[] preorder, int left, int right) {
        if (left > right) return null;
        
        int rootVal = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootVal);
        
        int inorderIndex = inorderMap.get(rootVal);
        
        root.left = build(preorder, left, inorderIndex - 1);
        root.right = build(preorder, inorderIndex + 1, right);
        
        return root;
    }
}
