package com.gomoku.controller;

import com.gomoku.model.GameModel;
import com.gomoku.util.CException;

public class GameController {

    /**
     * 游戏实体类
     */
    private GameModel gameModel;

    public void startPeopleGame(int size, String nameA, String nameB) {
        gameModel = new GameModel(size, nameA, nameB);
    }

    public void playChess(int row, int col) throws CException {
        gameModel.playChess(row, col);
    }

}
