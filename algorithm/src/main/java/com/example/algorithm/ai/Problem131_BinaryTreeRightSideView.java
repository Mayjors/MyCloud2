package com.example.algorithm.ai;

import java.util.*;

/**
 * 131. 二叉树的右视图
 * 给定一个二叉树的根节点 root，想象自己站在它的右侧，
 * 按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * 
 * 示例:
 * 输入: root = [1,2,3,null,5,null,4]
 * 输出: [1,3,4]
 */
public class Problem131_BinaryTreeRightSideView {
    
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
    
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                
                // 每层的最后一个节点
                if (i == size - 1) {
                    result.add(node.val);
                }
                
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        
        return result;
    }
    
    // DFS 解法
    public List<Integer> rightSideViewDFS(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        rightSideViewHelper(root, result, 0);
        return result;
    }
    
    private void rightSideViewHelper(TreeNode node, List<Integer> result, int level) {
        if (node == null) return;
        
        if (level == result.size()) {
            result.add(node.val);
        }
        
        rightSideViewHelper(node.right, result, level + 1);
        rightSideViewHelper(node.left, result, level + 1);
    }
}
