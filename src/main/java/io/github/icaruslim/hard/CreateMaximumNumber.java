package io.github.icaruslim.hard;

import java.util.Arrays;

/**
 * CreateMaximumNumber
 * https://leetcode.cn/problems/create-maximum-number/description/
 */

public class CreateMaximumNumber {

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] max = new int[k];
        Arrays.fill(max, Integer.MIN_VALUE);
        for (int i = 0; i <= k; i++) {
            if (nums1.length < i || nums2.length < k - i) {
                continue;
            }
            int[] a1 = select(nums1, i);
            int[] a2 = select(nums2, k - i);
            int[] newMax = merge(a1, a2);
            max = compare(max, newMax) >= 0 ? max : newMax;
        }
        return max;
    }

    public int[] select(int[] a, int k) {
        int[] stack = new int[k];
        Arrays.fill(stack, Integer.MIN_VALUE);
        int top = -1;
        for (int i = 0; i < a.length; i++) {
            int num = a[i];
            int remain = (a.length - i) - (k - (top + 1));
            while (top >= 0 && stack[top] < num && remain > 0) {
                top--;
                remain--;
            }
            if (top < k - 1) {
                stack[++top] = a[i];
            }
        }
        return stack;
    }

    public int compare(int[] a1, int[] a2) {
        for (int i = 0; i < a1.length; i++) {
            if (a1[i] > a2[i]) {
                return 1;
            } else if (a1[i] < a2[i]) {
                return -1;
            }
        }
        return 0;
    }

    public int[] merge(int[] a1, int[] a2) {
        int[] res = new int[a1.length + a2.length];
        int i = 0, j = 0;
        while (i < a1.length && j < a2.length) {
            if (compare(a1, i, a2, j) > 0) {
                res[i + j] = a1[i];
                i++;
            } else {
                res[i + j] = a2[j];
                j++;
            }
        }
        if (i < a1.length) {
            System.arraycopy(a1, i, res, i + j, a1.length - i);
        }
        if (j < a2.length) {
            System.arraycopy(a2, j, res, i + j, a2.length - j);
        }
        return res;
    }

    public int compare(int[] subsequence1, int index1, int[] subsequence2, int index2) {
        int x = subsequence1.length, y = subsequence2.length;
        while (index1 < x && index2 < y) {
            int difference = subsequence1[index1] - subsequence2[index2];
            if (difference != 0) {
                return difference;
            }
            index1++;
            index2++;
        }
        return (x - index1) - (y - index2);
    }

    public static void main(String[] args) {
        CreateMaximumNumber cmn = new CreateMaximumNumber();
        System.out.println(Arrays.toString(cmn.maxNumber(new int[]{6, 7}, new int[]{6, 0, 4}, 5)));
    }

}
