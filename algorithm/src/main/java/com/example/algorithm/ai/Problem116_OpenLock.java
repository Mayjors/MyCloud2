package com.example.algorithm.ai;

import java.util.*;

/**
 * 116. 打开转盘锁
 * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有 10 个数字：'0' 到 '9'。
 * 每次旋转只能旋转一个拨轮的一位数字。
 * 初始数字为 '0000'。
 * deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，锁将被永久锁定。
 * 字符串 target 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数。
 * 
 * 示例:
 * 输入: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * 输出: 6
 */
public class Problem116_OpenLock {
    
    public int openLock(String[] deadends, String target) {
        Set<String> dead = new HashSet<>(Arrays.asList(deadends));
        if (dead.contains("0000")) return -1;
        
        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");
        
        Set<String> visited = new HashSet<>();
        visited.add("0000");
        
        int level = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                
                if (curr.equals(target)) {
                    return level;
                }
                
                for (int j = 0; j < 4; j++) {
                    for (int d = -1; d <= 1; d += 2) {
                        char[] chars = curr.toCharArray();
                        chars[j] = (char) ((chars[j] - '0' + d + 10) % 10 + '0');
                        String next = new String(chars);
                        
                        if (!visited.contains(next) && !dead.contains(next)) {
                            visited.add(next);
                            queue.offer(next);
                        }
                    }
                }
            }
            
            level++;
        }
        
        return -1;
    }
}
