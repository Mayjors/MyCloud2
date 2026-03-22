package com.example.algorithm.ai;

import java.util.*;

/**
 * 110. 连通分量个数
 * 给定编号从 0 到 n - 1 的 n 个结点和一个无向边列表，计算并返回图中的连通分量个数。
 * 
 * 示例:
 * 输入: n = 5, edges = [[0,1],[1,2],[3,4]]
 * 输出: 2
 */
public class Problem110_NumberOfConnectedComponents {
    
    // 方法 1: DFS
    public int countComponents(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        
        boolean[] visited = new boolean[n];
        int count = 0;
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(graph, visited, i);
                count++;
            }
        }
        
        return count;
    }
    
    private void dfs(List<List<Integer>> graph, boolean[] visited, int node) {
        visited[node] = true;
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfs(graph, visited, neighbor);
            }
        }
    }
    
    // 方法 2: 并查集
    public int countComponentsUnionFind(int n, int[][] edges) {
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        int components = n;
        
        for (int[] edge : edges) {
            int root1 = find(parent, edge[0]);
            int root2 = find(parent, edge[1]);
            
            if (root1 != root2) {
                parent[root1] = root2;
                components--;
            }
        }
        
        return components;
    }
    
    private int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }
}
