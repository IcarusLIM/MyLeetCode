package main.java;

public class LongestAbsoluteFilePath {
    public int lengthLongestPath(String input) {
        String[] tokens = input.split("\n");
        int[] pRef = new int[]{0};
        return dfs(tokens, 0, pRef);
    }

    public int dfs(String[] tokens, int depth, int[] pRef) {
        int max = 0;
        while (pRef[0] < tokens.length) {
            String token = tokens[pRef[0]];
            int countTab = 0;
            while (token.charAt(countTab) == '\t') countTab++;
            if (countTab == depth) {
                pRef[0]++;
                if (token.indexOf('.') < 0) {
                    int dfsMax = dfs(tokens, depth + 1, pRef);
                    if (dfsMax > 0) {
                        max = Math.max(max, token.length() - countTab + 1 + dfsMax);
                    }
                } else {
                    max = Math.max(max, token.length() - countTab);
                }
            } else if (countTab < depth) {
                break;
            } else {
                // countTab > depth, test case error
            }
        }
        return max;
    }

    public static void main(String[] args) {
        String s = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext";
        System.out.println(new LongestAbsoluteFilePath().lengthLongestPath(s));
    }
}
