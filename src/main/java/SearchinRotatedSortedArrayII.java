package main.java;

import java.util.Arrays;

public class SearchinRotatedSortedArrayII {
    public boolean search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                return true;
            }
            if (nums[mid] < nums[right]) {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else if (nums[mid] > nums[left]) {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] == nums[right]) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SearchinRotatedSortedArrayII s = new SearchinRotatedSortedArrayII();
        System.out.println(s.search(new int[]{3, 4, 5, 1, 2}, 3));
    }
}
