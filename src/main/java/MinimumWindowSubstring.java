package main.java;

import java.util.Arrays;

public class MinimumWindowSubstring {

    public String minWindow(String s, String t) {
        int fulfill = 0;
        int[] sTimes = new int[256], tTimes = new int[256];
        Arrays.fill(sTimes, 0);
        Arrays.fill(tTimes, 0);

        for (int i = 0; i < t.length(); i++) {
            tTimes[t.charAt(i) - 'A']++;
        }

        int minLength = s.length() + 1;
        String minMatch = "";

        int left = 0, right = 0;

        while (left < s.length()) {
            if (fulfill >= t.length()) {
                if (right - left < minLength) {
                    minLength = right - left;
                    minMatch = s.substring(left, right);
                }
                char cLeft = s.charAt(left);
                if (sTimes[cLeft - 'A'] == tTimes[cLeft - 'A']) {
                    fulfill--;
                }
                sTimes[cLeft - 'A']--;
                left++;
            } else {
                if (right >= s.length()) {
                    break;
                }
                char cRight = s.charAt(right);
                if (sTimes[cRight - 'A'] < tTimes[cRight - 'A']) {
                    fulfill++;
                }
                sTimes[cRight - 'A']++;
                right++;
            }
        }
        return minMatch;
    }

    public static void main(String[] args) {
        MinimumWindowSubstring min = new MinimumWindowSubstring();
        System.out.println(min.minWindow("ADOBECODEBANC", "ABC"));
    }
}
