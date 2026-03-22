package com.example.algorithm.ai;

/**
 * 191. 重新安排行程
 * 给你一份航线列表 tickets，其中 tickets[i] = [fromi, toi] 表示飞机出发和降落的机场地点。
 * 请你对该行程进行重新规划排序。所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，
 * 所以该行程必须从 JFK 开始。如果存在多种有效的行程，请你按字典排序返回最小的行程组合。
 * 
 * 示例:
 * 输入: tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
 * 输出: ["JFK","MUC","LHR","SFO","SJC"]
 */
public class Problem191_ReconstructItinerary {
    
    public java.util.List<String> findItinerary(
            java.util.List<java.util.List<String>> tickets) {
        java.util.Map<String, java.util.PriorityQueue<String>> graph = 
            new java.util.HashMap<>();
        
        for (java.util.List<String> ticket : tickets) {
            graph.computeIfAbsent(ticket.get(0), k -> new java.util.PriorityQueue<>())
                 .offer(ticket.get(1));
        }
        
        java.util.List<String> result = new java.util.ArrayList<>();
        dfs(graph, "JFK", result);
        java.util.Collections.reverse(result);
        
        return result;
    }
    
    private void dfs(java.util.Map<String, java.util.PriorityQueue<String>> graph,
                     String airport, java.util.List<String> result) {
        java.util.PriorityQueue<String> destinations = graph.get(airport);
        
        while (destinations != null && !destinations.isEmpty()) {
            String next = destinations.poll();
            dfs(graph, next, result);
        }
        
        result.add(airport);
    }
}
