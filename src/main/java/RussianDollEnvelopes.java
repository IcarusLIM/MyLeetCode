package main.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class RussianDollEnvelopes {
    public int maxEnvelopes(int[][] envelopes) {

        Arrays.sort(envelopes, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);

        int currentWidth = 0;
        List<int[]> lastDepth = new ArrayList<>();
        List<int[]> currentDepth = new ArrayList<>();
        int lp = 0, cp = 0;
        for (int[] envelope : envelopes) {
            if (envelope[0] != currentWidth) {
                int currentMaxDepth = currentDepth.size() > 0 ? currentDepth.get(currentDepth.size() - 1)[1] : 0;
                while (lp < lastDepth.size()) {
                    if (lastDepth.get(lp)[1] > currentMaxDepth) {
                        currentDepth.add(lastDepth.get(lp));
                    }
                    lp++;
                }
                lastDepth = currentDepth;
                currentWidth = envelope[0];
                currentDepth = new ArrayList<>();
                lp = 0;
                cp = 0;
            }
            int maxDepth = 1;
            while (lp < lastDepth.size() && lastDepth.get(lp)[0] < envelope[1]) {
                maxDepth = Math.max(maxDepth, lastDepth.get(lp)[1] + 1);
                if (currentDepth.size() == 0 || lastDepth.get(lp)[1] > currentDepth.get(cp - 1)[1]) {
                    currentDepth.add(lastDepth.get(lp));
                    cp++;
                }
                lp++;
            }
            if (lp < lastDepth.size() && lastDepth.get(lp)[0] == envelope[1]) {
                maxDepth = Math.max(maxDepth, lastDepth.get(lp)[1]);
            }
            if (currentDepth.size() == 0 || currentDepth.get(cp - 1)[1] < maxDepth) {
                currentDepth.add(new int[]{envelope[1], maxDepth});
                cp++;
            }
        }

        // the tail of lastDepth may have not merge to currentDepth, do check
        if (lastDepth.size() > 0) {
            return Math.max(lastDepth.get(lastDepth.size() - 1)[1], currentDepth.get(currentDepth.size() - 1)[1]);
        } else {
            return currentDepth.get(currentDepth.size() - 1)[1];
        }
    }

    public static void main(String[] args) {
//        int[][] envelopes = {{5, 4}, {6, 4}, {6, 7}, {2, 3}};
//        int[][] envelopes = {{1, 1}, {1, 1}, {1, 1}};
//        int[][] envelopes = {{46, 89}, {50, 53}, {52, 68}, {72, 45}, {77, 81}};
        int[][] envelopes = {{10, 8}, {1, 12}, {6, 15}, {2, 18}};
        RussianDollEnvelopes r = new RussianDollEnvelopes();
        System.out.println(r.maxEnvelopes(envelopes));
    }
}
