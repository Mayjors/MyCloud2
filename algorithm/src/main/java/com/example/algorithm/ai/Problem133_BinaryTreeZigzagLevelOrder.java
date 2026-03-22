package com.example.algorithm.ai;

import java.util.*;

/**
 * 133. 二叉树的锯齿形层序遍历
 * 给你二叉树的根节点 root，返回其节点值的锯齿形层序遍历。
 * 即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行。
 * 
 * 示例:
 * 输入: root = [3,9,20,null,null,15,7]
 * 输出: [[3],[20,9],[15,7]]
 */
public class Problem133_BinaryTreeZigzagLevelOrder {
    
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
    
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> level = new LinkedList<>();
            
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                
                if (leftToRight) {
                    level.addLast(node.val);
                } else {
                    level.addFirst(node.val);
                }
                
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            
            result.add(level);
            leftToRight = !leftToRight;
        }
        
        return result;
    }
}
