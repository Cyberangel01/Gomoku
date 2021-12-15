package com.company.gomoku.model;

import com.company.gomoku.util.CException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameModel {

    private ChessBoardModel chessBoardModel;

    private List<Player> players;

    private Integer nowTurnIndex;

//    public GameModel(int size, String nameA, String nameB) {
//        chessBoardModel = new ChessBoardModel(size);
//        players = new ArrayList<>();
//        Player playerA = new Player(nameA);
//        Player playerB = new Player(nameB);
//        // 黑棋先走，随机哪个玩家是黑棋
//        Random random = new Random();
//        if (random.nextInt(2) == 1) {
//            playerA.setChessColor(ChessColor.BLACK);
//            playerB.setChessColor(ChessColor.WHITE);
//            nowTurnIndex = 0;
//        } else {
//            playerA.setChessColor(ChessColor.WHITE);
//            playerB.setChessColor(ChessColor.BLACK);
//            nowTurnIndex = 1;
//        }
//        players.add(playerA);
//        players.add(playerB);
//    }
//
//    public ChessBoardModel getChessBoardModel() {
//        return chessBoardModel;
//    }
//
//    public List<Player> getPlayers() {
//        return players;
//    }
//
//    public Player getNowTurnPlayer() {
//        return players.get(nowTurnIndex);
//    }
//
//    /**
//     * 玩家切换
//     */
//    public void nextTurn() {
//        nowTurnIndex = (nowTurnIndex + 1) % 2;
//    }
//
//    /**
//     * 下棋
//     */
//    public boolean playChess(int row, int col) throws CException {
//        Player player = getNowTurnPlayer();
//        if (chessBoardModel.hasChess(row, col)) {
//            throw new CException(CException.OUT_OF_BOUND, "repetition move");
//        }
//        chessBoardModel.playChess(row, col, player.getChessColor());
//        // 赢了返回 true
//        if (chessBoardModel.checkWin(row, col)) {
//            return true;
//        }
//        nextTurn();
//        return false;
//    }

}
