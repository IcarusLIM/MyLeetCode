package io.github.icaruslim.medium;

import java.util.Arrays;

/**
 * NumRescueBoats
 * https://leetcode.cn/problems/boats-to-save-people/description/
 */
public class NumRescueBoats {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int left = 0;
        int right = people.length - 1;

        int count = 0;
        while (left < right) {
            if (people[right] + people[left] <= limit) {
                left++;
            }
            right--;
            count++;
        }
        if (left == right) {
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] people = { 1, 2, 4, 3 };
        new NumRescueBoats().numRescueBoats(people, 0);
    }
}
