package io.github.icaruslim.hard;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class FindMaximumElegance {
    public long findMaximumElegance(int[][] items, int k) {
        Arrays.sort(items, Comparator.comparingInt(i -> -i[0]));
        HashMap<Integer, Integer> catOccurrences = new HashMap<>();

        long totalProfit = 0;
        for (int i = 0; i < k; i++) {
            int[] item = items[i];
            totalProfit += item[0];
            catOccurrences.put(item[1], catOccurrences.getOrDefault(item[1], 0) + 1);
        }

        long best = totalProfit + ((long) catOccurrences.size()) * catOccurrences.size();

        // try replace item to increase cat
        int left = k;
        int right = k - 1;

        while (left > 0 && ++right < items.length && catOccurrences.size() < k) {
            int[] newItem = items[right];
            if (catOccurrences.containsKey(newItem[1])) {
                continue;
            }
            while (--left >= 0) {
                int[] oldItem = items[left];
                if (catOccurrences.get(oldItem[1]) <= 1) {
                    continue;
                }
                catOccurrences.put(newItem[1], 1);
                catOccurrences.put(oldItem[1], catOccurrences.get(oldItem[1]) - 1);
                totalProfit = totalProfit - oldItem[0] + newItem[0];

                long newBest = totalProfit + ((long) catOccurrences.size()) * catOccurrences.size();
                if (newBest > best) {
                    best = newBest;
                }
                break;
            }
        }
        return best;
    }

    public static void main(String[] args) {
        int[][] items = { { 1, 1 }, { 1, 2 }, { 1, 3 }, { 1, 4 } };
        System.out.println(new FindMaximumElegance().findMaximumElegance(items, 4));
    }
}
