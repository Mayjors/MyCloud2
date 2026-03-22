package com.example.algorithm.ai;

/**
 * 71. 二叉树展开为链表
 * 给你二叉树的根结点 root，请你将它展开为一个单链表：
 * - 展开后的单链表应该同样使用 TreeNode，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null。
 * - 展开后的单链表应该与二叉树先序遍历顺序相同。
 * 
 * 示例:
 * 输入: root = [1,2,5,3,4,null,6]
 * 输出: [1,null,2,null,3,null,4,null,5,null,6]
 */
public class Problem71_FlattenBinaryTreeToLinkedList {
    
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
    
    // 方法 1: 前序遍历
    public void flatten(TreeNode root) {
        if (root == null) return;
        
        flatten(root.left);
        flatten(root.right);
        
        TreeNode left = root.left;
        TreeNode right = root.right;
        
        root.left = null;
        root.right = left;
        
        TreeNode current = root;
        while (current.right != null) {
            current = current.right;
        }
        current.right = right;
    }
    
    // 方法 2: 寻找前驱节点（原地算法）
    public void flattenOptimized(TreeNode root) {
        TreeNode current = root;
        
        while (current != null) {
            if (current.left != null) {
                TreeNode predecessor = current.left;
                while (predecessor.right != null) {
                    predecessor = predecessor.right;
                }
                predecessor.right = current.right;
                current.right = current.left;
                current.left = null;
            }
            current = current.right;
        }
    }
}
