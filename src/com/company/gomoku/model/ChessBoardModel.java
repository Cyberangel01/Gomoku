package com.company.gomoku.model;

import com.company.gomoku.util.CException;

import java.util.ArrayList;
import java.util.List;

/**
 * 棋盘
 */
public class ChessBoardModel {

    private static final int[] CHECK_ARRAY = new int[]{-4, -3, -2, -1, 0, 1, 2, 3, 4};

    private static final int WIN_NUM = 5;

    private int size;

    private int[][] board;

    public ChessBoardModel(int size) {
        this.size = size;
        this.board = new int[size][size];
    }

    /**
     * 下棋
     * @param row 行
     * @param col 列
     * @param chessColor 颜色
     * @throws CException 异常
     */
    public void playChess(int row, int col, ChessColor chessColor) throws CException {
        if (isInBound(row, col)) {
            // todo 下棋三三禁手
            board[row][col] = chessColor.getValue();
        } else {
            throw new CException("can not play chess outside the chessboard");
        }
    }

    private boolean isInBound(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size;
    }

    /**
     * 判断当前棋局是否有胜出者
     */
    public boolean checkWin(int row, int col) {
        // 横
        List<Integer> rowList = new ArrayList<>();
        for (int value : CHECK_ARRAY) {
            if (isInBound(row + value, col)) {
                rowList.add(board[row + value][col]);
            }
        }
        // 竖
        List<Integer> colList = new ArrayList<>();
        for (int value : CHECK_ARRAY) {
            if (isInBound(row, col + value)) {
                colList.add(board[row][col + value]);
            }
        }
        // 左上右下
        List<Integer> diagonalListA = new ArrayList<>();
        for (int value : CHECK_ARRAY) {
            if (isInBound(row + value, col + value)) {
                diagonalListA.add(board[row + value][col + value]);
            }
        }
        // 右上左下
        List<Integer> diagonalListB = new ArrayList<>();
        for (int value : CHECK_ARRAY) {
            if (isInBound(row + value, col - value)) {
                diagonalListB.add(board[row + value][col - value]);
            }
        }
        return checkRow(rowList) || checkRow(colList) || checkRow(diagonalListA) || checkRow(diagonalListB);
    }

    private boolean checkRow(List<Integer> row) {
        int count = 1;
        for (int i = 1;i < row.size(); i++) {
            if (row.get(i).equals(row.get(i - 1))) {
                count++;
            } else {
                count = 1;
            }
            if (count >= WIN_NUM) {
                return true;
            }
        }
        return false;
    }

    /**
     * 该位置是否有棋子
     * @param row row
     * @param col col
     * @return 是否有棋子
     */
    public boolean hasChess(int row, int col) {
        return board[row][col] != 0;
    }

}
