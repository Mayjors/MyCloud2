package com.example.algorithm.zcode.数据结构.线性表;

import java.util.*;

public class 数组 {
    public static void main(String[] args) {

    }

    /**
     * 1.两数之和
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i< nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[] {map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[] {-1, -1};
    }

    /**
     * nums是有序情况
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        int left = 0, right = nums.length -1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                return new int[] {left, right};
            } else if (sum < target) {
                left++;
            } else {
                right++;
            }
        }
        return new int[] {-1, -1};
    }

    /**
     * 有重复
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> twoSum3(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            int leftVal = nums[left], rightVal = nums[right];
            if (sum == target) {
                result.add(new ArrayList<>(Arrays.asList(nums[left], nums[right])));
                while (left < right && nums[left] == leftVal) {
                    left++;
                }
                while (left < right && nums[right] == rightVal) {
                    right--;
                }
            } else if (sum < target) {
                while (left < right && nums[left] == leftVal) {
                    left++;
                }
            } else {
                while (left < right && nums[right] == rightVal) {
                    right--;
                }
            }
        }
        return result;
    }

    /**
     * 26.删除有序数组中的重复项
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int p = 0, q = 1;
        while (q < nums.length) {
            if (nums[p] != nums[q]) {
                if (q - p > 1) {
                    nums[p + 1] = nums[q];
                }
                p++;
            }
            q++;
        }
        return p+1;
    }

    public int removeDuplicates2(int[] nums) {
        int len = nums.length;
        if (len <= 1) {
            return len;
        }
        // 定义快慢指针
        int slow = 0, fast = 0;
        while (slow < fast) {
            if (nums[slow] != nums[fast]) {
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }
        return slow+1;
    }

    /**
     * 27.移除元素
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
//        int slow = 0, fast = 0;
//        while (fast < nums.length) {
//            if (nums[fast] != val) {
//                nums[slow] = nums[fast];
//                slow++;
//            }
//            fast++;
//        }
//        return slow;
        int left = 0, right = nums.length;
        while (left < right) {
            if (nums[left] == val) {
                nums[left] = nums[right-1];
                right--;
            } else {
                left++;
            }
        }
        return left;
    }

    /**
     * 202 快乐数
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        int slow = n;
        int fast = getNext(slow);
        while (fast != 1 && slow != fast) {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        }
        return fast == 1;
    }

    private int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n /= 10;
            totalSum += d * d;
        }
        return totalSum;
    }


    public int[] plusOne(int[] digits) {
        int len = digits.length;
        if (digits[len - 1] != 9) {
            // 个位数上的数字不为9
            digits[len - 1]++;
            return digits;
        }
        digits[len - 1] = 0;
        len -= 2;
        while (len >= 0) {
            if (digits[len] == 9) {
                // 高位也为9，将其设置为0
                digits[len] = 0;
                len--;
            } else {
                // 高位不为9，将其加1，然后直接推出循环
                digits[len]++;
                break;
            }
        }
        if (len < 0) {
            // len < 9 说明数组中每个元素值都为9，即[9,9,9,9,9]
            // 该证书加1后等于10 000，存储该整数的数组长度应等于在原来数组digits的长度基础上再加1
            int[] res = new int[digits.length + 1];
            res[0] = 1;
            return res;
        } else {
            return digits;
        }
    }

    public int[] plusOne2(int[] digits) {
        int len = digits.length;
        for (int i = len - 1; i>= 0 ; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) {
                // 最后一个数不是10 则直接返回
                return digits;
            }
        }
        digits = new int[len +1];
        digits[0] = 1;
        return digits;
    }

}
