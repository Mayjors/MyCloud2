package com.example.algorithm.offer;

import java.util.*;

public class 树 {
    public static void main(String[] args) {

    }


    /**
     * 124.二叉树中的最大路径和
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        return 0;
    }

    /**
     * 104. 二叉树的最大深度
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) +1;
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i=0; i<size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            res.add(level);
        }
        return res;
    }

    public static List<List<Integer>> levelOrder2(TreeNode root) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        dfs(root, map, 0);
        return new ArrayList<>(map.values());
    }

    private static void dfs(TreeNode node, Map<Integer, List<Integer>> map, int level) {
        if (node == null) {
            return;
        }
        List<Integer> list = map.getOrDefault(level, new ArrayList<>());
        list.add(node.val);
        map.put(level, list);
        dfs(node.left, map, level+1);
        dfs(node.right, map, level+1);
    }


}
