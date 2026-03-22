package com.example.algorithm.ai;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 50. 二叉树的序列化与反序列化
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，
 * 同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * 
 * 示例:
 * 你可以将以下二叉树：
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 * 序列化为 "[1,2,3,null,null,4,5]"
 */
public class Problem50_SerializeDeserializeBinaryTree {
    
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    
    // BFS 序列化
    public String serialize(TreeNode root) {
        if (root == null) return "";
        
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                sb.append("null,");
            } else {
                sb.append(node.val).append(",");
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        
        return sb.toString();
    }
    
    // BFS 反序列化
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) return null;
        
        String[] values = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        int i = 1;
        while (!queue.isEmpty() && i < values.length) {
            TreeNode node = queue.poll();
            
            if (!"null".equals(values[i])) {
                node.left = new TreeNode(Integer.parseInt(values[i]));
                queue.offer(node.left);
            }
            i++;
            
            if (i < values.length && !"null".equals(values[i])) {
                node.right = new TreeNode(Integer.parseInt(values[i]));
                queue.offer(node.right);
            }
            i++;
        }
        
        return root;
    }
}
