package com.company.gomoku.controller;

import com.company.gomoku.util.CException;

public interface IGameController {

    /**
     * 开始游戏
     *
     * @param size 棋盘大小
     * @param nameA 玩家A
     * @param nameB 玩家B
     */
    void startPeopleGame(int size, String nameA, String nameB);

    /**
     * 下棋
     *
     * @param row row
     * @param col col
     * @throws CException CException
     */
    void playChess(int row, int col) throws CException;
}
