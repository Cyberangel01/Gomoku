package com.company.gomoku.controller;

import com.company.gomoku.model.GameModel;
import com.company.gomoku.model.Player;
import com.company.gomoku.util.CException;
import com.company.gomoku.view.ChessBoardView;

public class GameController implements IGameController{

    /**
     * 游戏实体类
     */
    private GameModel gameModel;

    /**
     * 游戏view类
     */
    private ChessBoardView chessBoardView;

    @Override
    public void startPeopleGame(int size, String nameA, String nameB) {
        // 初始化gameModel
        gameModel = new GameModel(size, nameA, nameB);
        // 初始化view
        chessBoardView = new ChessBoardView(size);
        chessBoardView.setGameController(this);
    }

    @Override
    public boolean playChess(int row, int col) throws CException {
        return gameModel.playChess(row, col);
    }

    @Override
    public Player getNowTurnPlayer() {
        return gameModel.getNowTurnPlayer();
    }

    public ChessBoardView getChessBoardView() {
        return chessBoardView;
    }
}
