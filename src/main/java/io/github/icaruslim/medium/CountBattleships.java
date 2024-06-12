package io.github.icaruslim.medium;

/**
 * CountBattleships
 * https://leetcode.cn/problems/battleships-in-a-board/description/
 * alt: 统计左上角
 */

public class CountBattleships {

    public int countBattleships(char[][] board) {
        int m = board.length;
        int n = board[0].length;

        int rowCount = 0;
        for (int i = 0; i < m; i++) {
            boolean onShip = false;
            for (int j = 0; j < n; j++) {
                char c = board[i][j];
                if (c == '.') {
                    onShip = false;
                } else {
                    if (!onShip) {
                        rowCount++;
                        onShip = true;
                    }
                }
            }
        }

        int rowOverCount = 0;
        for (int j = 0; j < n; j++) {
            boolean onShip = false;
            for (int i = 0; i < m; i++) {
                char c = board[i][j];
                if (c == '.') {
                    onShip = false;
                } else {
                    if (onShip) {
                        rowOverCount++;
                    } else {
                        onShip = true;
                    }
                }
            }
        }
        return rowCount - rowOverCount;
    }

    public static void main(String[] args) {
        char[][] board = { { 'X', '.', '.', 'X' }, { '.', '.', '.', 'X' }, { '.', '.', '.', 'X' } };
        System.out.println(new CountBattleships().countBattleships(board));
    }
}