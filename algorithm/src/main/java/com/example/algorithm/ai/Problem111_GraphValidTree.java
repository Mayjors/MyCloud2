package com.example.algorithm.ai;

import java.util.*;

/**
 * 111. 图是否是树
 * 给定编号从 0 到 n - 1 的 n 个结点和一个无向边列表，判断这些边是否能组成一棵有效的树。
 * 
 * 示例:
 * 输入: n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
 * 输出: true
 */
public class Problem111_GraphValidTree {
    
    // 方法 1: DFS 检测环
    public boolean validTree(int n, int[][] edges) {
        if (edges.length != n - 1) return false;
        
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        
        Set<Integer> visited = new HashSet<>();
        if (hasCycle(graph, visited, 0, -1)) {
            return false;
        }
        
        return visited.size() == n;
    }
    
    private boolean hasCycle(List<List<Integer>> graph, Set<Integer> visited, 
                            int node, int parent) {
        visited.add(node);
        
        for (int neighbor : graph.get(node)) {
            if (neighbor == parent) continue;
            if (visited.contains(neighbor)) return true;
            if (hasCycle(graph, visited, neighbor, node)) return true;
        }
        
        return false;
    }
    
    // 方法 2: 并查集
    public boolean validTreeUnionFind(int n, int[][] edges) {
        if (edges.length != n - 1) return false;
        
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        for (int[] edge : edges) {
            int root1 = find(parent, edge[0]);
            int root2 = find(parent, edge[1]);
            
            if (root1 == root2) return false; // 有环
            parent[root1] = root2;
        }
        
        return true;
    }
    
    private int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }
}
