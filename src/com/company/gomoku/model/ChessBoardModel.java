package com.company.gomoku.model;

import com.company.gomoku.util.CException;

/**
 * 棋盘
 */
public class ChessBoardModel {

    private int size;

    private int[][] board;

    public ChessBoardModel(int size) {
        this.size = size;
        this.board = new int[size + 1][size + 1];
    }

    /**
     * 下棋
     * @param row 行
     * @param col 列
     * @param color 颜色
     * @throws CException 异常
     */
    public void playChess(int row, int col, Color color) throws CException {
        if (row > 0 && row <= size && col > 0 && col <= size) {
            // todo 下棋三三禁手
            board[row][col] = color.getValue();
        } else {
            throw new CException("can not play chess outside the chessboard");
        }
    }

    public void reset() {
        this.board = new int[size + 1][size + 1];
    }

}
