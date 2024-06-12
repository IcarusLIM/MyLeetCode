package io.github.icaruslim.medium;

import java.util.Arrays;

/**
 * CoinChange
 * https://leetcode.cn/problems/coin-change/description/
 */

public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int[] dp = new int[amount + 1];
        dp[0] = -1;
        for (int i = 1; i <= amount; i++) {
            dp[i] = -1;
            for (int coin : coins) {
                int remain = i - coin;
                if (remain <= 0) {
                    if (remain == 0) {
                        dp[i] = 1;
                    }
                    break;
                }
                if (dp[remain] > 0 && (dp[i] < 0 || dp[i] > dp[remain] + 1)) {
                    dp[i] = dp[remain] + 1;
                }
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        CoinChange cc = new CoinChange();
        System.out.println(cc.coinChange(new int[]{1, 2, 5}, 11));
    }
}
