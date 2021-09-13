package main.java;

import java.util.HashMap;
import java.util.Map;

public class MaximumSizeSubarraySumEqualsK {

    public int maxSubArrayLen(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int maxLen = 0;
        for (int i = 0, sum = 0; i < nums.length; i++) {
            sum += nums[i];
            map.putIfAbsent(sum, i);

            int left = sum - k;
            Integer idx = map.get(left);
            if (idx != null) {
                int len = i - idx;
                maxLen = Math.max(maxLen, len);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        MaximumSizeSubarraySumEqualsK m = new MaximumSizeSubarraySumEqualsK();
        System.out.println(m.maxSubArrayLen(new int[]{1,-1,5,-2,3}, 3));
    }
}
