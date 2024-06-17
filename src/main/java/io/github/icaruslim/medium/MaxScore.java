package io.github.icaruslim.medium;

/**
 * MaxScore
 * https://leetcode.cn/problems/visit-array-positions-to-maximize-score/description/
 */
public class MaxScore {
    public long maxScore(int[] nums, int x) {
        long sum0 = 0;
        long sum1 = 0;

        if (nums[0] % 2 == 0) {
            sum1 -= x;
        } else {
            sum0 -= x;
        }

        for (int num : nums) {
            if (num % 2 == 0) {
                sum0 = Math.max(sum0, sum1 - x) + num;
            } else {
                sum1 = Math.max(sum1, sum0 - x) + num;
            }
        }
        return Math.max(sum0, sum1);
    }

    public static void main(String[] args) {
        int[] nums = { 2, 3, 6, 1, 9, 2 };
        System.out.println(new MaxScore().maxScore(nums, 5));
    }
}
