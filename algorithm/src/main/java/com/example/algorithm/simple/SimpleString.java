package com.example.algorithm.simple;

public class SimpleString {
    public static void main(String[] args) {
        String s = minWindow("ADOBECODEBANC", "ABC");
        System.out.println(s);
    }

    /**
     * 11. 盛最多水的容器
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        int i=0, j=height.length-1, res =0;
        while (i<j) {
            int area = (j-i) * Math.min(height[i], height[j]);
            res = Math.max(res, area);
            if (height[j]<height[i]) {
                i++;
            } else {
                j--;
            }
        }
        return res;
    }

    /**
     * leetcode76
     * 最小覆盖字串
     * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     * @return
     */
    public static String minWindow(String s , String t) {
        // 首先创建的是need数组表示每个字符在t中需要的数量，
        // 加入need[76] = 2,表明ASCII码为76的这个字符在目标字符串t中需要两个，如果是负数表明当前字符串在窗口中是多余的，需要过滤掉
        int[] need = new int[128];
        for (int i=0; i< t.length(); i++) {
            need[t.charAt(i)]++;
        }
        /**
         * l: 滑动窗口左边界
         * r：滑动窗口右边界
         * size: 窗口长度
         * count：当次遍历中还需要几个字符才能满足包含t中所有字符的条件，最大也就是t的长度
         * start：如果有效更新滑动窗口，记录这个窗口的起始位置，方便后续找子串
         */
        int l=0, r=0, size = Integer.MAX_VALUE, count=t.length(), start =0;
        while (r<s.length()) {
            char c = s.charAt(r);
            if (need[c] >0) {
                count--;
            }
            // 无论这个字符是否包含在t中，need[]数组中对应那个字符的计数都减1，利用正负区分这个字符是多余还是有用的
            need[c]--;
            // count==0 说明当前的窗口已经满足了条件
            if (count ==0) {
                // 如果左边界这个字符对应的值在need[]数组中小于0，说明他是这个多余元素，不包含在t内
                while (l < r && need[s.charAt(l)] <0) {
                    // 在need[]数组中维护更新这个值，增加1
                    need[s.charAt(l)]++;
                    l++;
                }
                //不能右移时候挑战最小窗口大小，更新最小窗口开始的start
                if (r-l +1 < size) {
                    size = r-l+1;
                    start =l;
                }
                //l向右移动后窗口肯定不能满足了 重新开始循环
                need[s.charAt(l)]++;
                l++;
                count++;
            }
            r++;
        }
        return size==Integer.MAX_VALUE ?"" : s.substring(start, start+ size);
    }

    public static String minWindow2(String s , String t) {
        int[] need = new int[128];//need[i]表示需要该元素的数量,0代表不需要, <0代表多余的数量
        for (int i = 0; i < t.length(); i++) {
            need[t.charAt(i)]++;
        }
        int i = 0, j = 0;//i滑动窗口左端，j右端
        int minSize = Integer.MAX_VALUE;//当前发现的满足要求的最小窗口长度
        int needCnt = t.length();//所需元素的总数量
        int start = 0;
        while(j < s.length()){
            //步骤一：不断增加j使滑动窗口增大，直到窗口包含了T的所有元素
            char jChar = s.charAt(j);
            if(need[jChar] > 0){//新加入的字符使我们需要的元素
                needCnt--;
            }
            need[jChar]--;

            //此时窗窗口包含了T的所有元素，进入步骤二，增加i，扔掉不需要的元素
            if(needCnt == 0){
                char iChar = s.charAt(i);
                while(need[iChar] < 0){
                    //向右移动一格
                    need[iChar]++;
                    i++;
                    iChar = s.charAt(i);
                }
                if(j-i+1 < minSize){
                    minSize = j-i+1;
                    start = i;//只有发现最小值时才更新start
                }
                //步骤三：让i再向右移动一个位置，使窗口不再满足条件，让其再次寻找，进入步骤一

                need[s.charAt(i)]++;
                i++;
                needCnt++;
            }
            j++;
        }
        return minSize == Integer.MAX_VALUE ? "" : s.substring(start, start+minSize);
    }
}
