package com.example.algorithm.ai;

import java.util.*;

/**
 * 134. 二叉树的垂直遍历
 * 给你一个二叉树的根节点，返回其节点的垂直顺序遍历。
 * 
 * 示例:
 * 输入: root = [3,9,20,null,null,15,7]
 * 输出: [[9],[3,15],[20],[7]]
 */
public class Problem134_BinaryTreeVerticalOrderTraversal {
    
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
    
    class NodeWithCol {
        TreeNode node;
        int col;
        NodeWithCol(TreeNode node, int col) {
            this.node = node;
            this.col = col;
        }
    }
    
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        
        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<NodeWithCol> queue = new LinkedList<>();
        queue.offer(new NodeWithCol(root, 0));
        
        int minCol = 0, maxCol = 0;
        
        while (!queue.isEmpty()) {
            NodeWithCol current = queue.poll();
            
            map.computeIfAbsent(current.col, k -> new ArrayList<>()).add(current.node.val);
            minCol = Math.min(minCol, current.col);
            maxCol = Math.max(maxCol, current.col);
            
            if (current.node.left != null) {
                queue.offer(new NodeWithCol(current.node.left, current.col - 1));
            }
            if (current.node.right != null) {
                queue.offer(new NodeWithCol(current.node.right, current.col + 1));
            }
        }
        
        for (int i = minCol; i <= maxCol; i++) {
            result.add(map.get(i));
        }
        
        return result;
    }
}
