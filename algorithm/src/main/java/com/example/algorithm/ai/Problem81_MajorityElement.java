package com.example.algorithm.ai;

import java.util.HashMap;
import java.util.Map;

/**
 * 81. 多数元素
 * 给定一个大小为 n 的数组 nums，返回其中的多数元素。
 * 多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * 
 * 示例:
 * 输入: nums = [3,2,3]
 * 输出: 3
 */
public class Problem81_MajorityElement {
    
    // 方法 1: 哈希表
    public int majorityElementHashMap(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>();
        int majority = nums.length / 2;
        
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
            if (count.get(num) > majority) {
                return num;
            }
        }
        
        return -1;
    }
    
    // 方法 2: Boyer-Moore 投票算法
    public int majorityElement(int[] nums) {
        int candidate = nums[0];
        int count = 1;
        
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                candidate = nums[i];
                count = 1;
            } else if (nums[i] == candidate) {
                count++;
            } else {
                count--;
            }
        }
        
        return candidate;
    }
}
