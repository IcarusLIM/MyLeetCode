package io.github.icaruslim.hard;

import java.util.Arrays;

/**
 * PaintWalls
 * https://leetcode.cn/problems/painting-the-walls/description/
 */
public class PaintWalls {
    public int paintWalls(int[] cost, int[] time) {
        // time require for free painter to finish rest & cost by nonfree painter
        int[] costArray = new int[cost.length + 1];
        Arrays.fill(costArray, Integer.MAX_VALUE);
        costArray[cost.length] = 0;

        for (int i = 0; i < cost.length; i++) {
            int costI = cost[i];
            int timeI = time[i];

            for (int j = 1; j < costArray.length; j++) {
                if (costArray[j] == Integer.MAX_VALUE) {
                    continue;
                }
                int newCost = costArray[j] + costI;
                int newTimeRequire = Math.max(j - timeI - 1, 0);

                if (costArray[newTimeRequire] > newCost) {
                    costArray[newTimeRequire] = newCost;
                }
            }
        }
        return costArray[0];
    }

    public static void main(String[] args) {
        int[] cost = { 1, 2, 3, 2 };
        int[] time = { 1, 2, 3, 2 };
        System.out.println("should=3 ,output=" + new PaintWalls().paintWalls(cost, time));
    }
}
