package io.github.icaruslim.hard;

/**
 * RegularExpressionMatching
 * https://leetcode.cn/problems/regular-expression-matching/description/
 */

public class RegularExpressionMatching {

    public boolean charMatch(char s, char p) {
        return p == '.' || s == p;
    }

    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];

        dp[0][0] = true;

        for (int i = 1; i <= p.length(); i++) {
            char pc = p.charAt(i - 1);
            boolean isWildcard = false;
            if (pc == '*' && i >= 2) {
                pc = p.charAt(i - 2);
                isWildcard = true;
                dp[i][0] = dp[i - 2][0];
            }
            for (int j = 1; j <= s.length(); j++) {
                char sc = s.charAt(j - 1);
                dp[i][j] = (isWildcard && (dp[i - 2][j] || (charMatch(sc, pc) && dp[i][j - 1]))) || (charMatch(sc, pc) && dp[i - 1][j - 1]);
            }
        }
        return dp[p.length()][s.length()];
    }

    public static void main(String[] args) {
        RegularExpressionMatching rem = new RegularExpressionMatching();
        System.out.println(rem.isMatch("mississippi", "mis*is.*p*."));
    }
}
