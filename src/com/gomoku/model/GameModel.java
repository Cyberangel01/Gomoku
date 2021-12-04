package com.gomoku.model;

import com.gomoku.util.CException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameModel {

    private ChessBoard chessBoard;

    private List<Player> players;

    private Integer nowTurnIndex;

    public GameModel(int size, String nameA, String nameB) {
        chessBoard = new ChessBoard(size);
        players = new ArrayList<>();
        Player playerA = new Player(nameA);
        Player playerB = new Player(nameB);
        // 黑棋先走，随机哪个玩家是黑棋
        Random random = new Random();
        if (random.nextInt(2) == 1) {
            playerA.setColor(Color.BLACK);
            playerB.setColor(Color.WHITE);
            nowTurnIndex = 0;
        } else {
            playerA.setColor(Color.WHITE);
            playerB.setColor(Color.BLACK);
            nowTurnIndex = 1;
        }
        players.add(playerA);
        players.add(playerB);
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getNowTurnPlayer() {
        return players.get(nowTurnIndex);
    }

    /**
     * 玩家切换
     */
    public void nextTurn() {
        nowTurnIndex = (nowTurnIndex + 1) % 2;
    }

    /**
     * 下棋
     */
    public void playChess(int row, int col) throws CException {
        Player player = getNowTurnPlayer();
        chessBoard.playChess(row, col, player.getColor());
        nextTurn();
    }

}
