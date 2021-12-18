package model;

import java.util.List;

import util.CException;

/**
 * 棋盘
 */
public class ChessBoardModel {

	private static final int WIN_NUM = 5;

	private int size;

	private int[][] board;

	public ChessBoardModel(int size, int[][] board) {
		this.size = size;
		this.board = board;
	}

	/**
	 * 下棋
	 * 
	 * @param row        行
	 * @param col        列
	 * @param chessColor 颜色
	 * @throws CException 异常
	 * @return 是否获胜
	 */
	public boolean playChess(int row, int col, ChessColor chessColor) throws CException {
		// 下棋操作
		if (isInBound(row, col)) {
			if (isForBiddenMoves(row, col, chessColor)) {
				throw new CException(CException.FORBIDDEN_MOVE, "can not play chess by forbidden move");
			}
			board[row][col] = chessColor.getValue();
		} else {
			throw new CException(CException.OUT_OF_BOUND, "can not play chess outside the chessboard");
		}
		return checkWin(row, col);
	}

	private boolean isForBiddenMoves(int row, int col, ChessColor chessColor) {
		// 白色没有禁手
		if (chessColor == ChessColor.WHITE) {
			return false;
		}
		// 假设下了黑棋
		board[row][col] = ChessColor.BLACK.getValue();
		// 扩大查找返回，构成活三，就禁手
		for (int x = row - 2; x <= row + 2; x++) {
			for (int y = col - 2; y <= col + 2; y++) {
				if (isInBound(x, y) && board[x][y] == ChessColor.BLACK.getValue()) {
					if (isForBiddenMoves(x, y)) {
						board[row][col] = 0;
						return true;
					}
				}
			}
		}
		board[row][col] = 0;
		return false;
	}

	private boolean isForBiddenMoves(int row, int col) {
		// 黑色不能同时组两个活三
		int count = 0;
		for (ChessRow chessRow : ChessRow.values()) {
			ChessRow.ChessRowPair chessRowPair = chessRow.getRows(row, col, board);
			if (checkLiveThree(chessRowPair.rowList, chessRowPair.centerIndex)) {
				count++;
			}
		}
		return count > 1;
	}

	private boolean isInBound(int row, int col) {
		return row >= 0 && row < size && col >= 0 && col < size;
	}

	/**
	 * 判断当前棋局是否有胜出者
	 */
	public boolean checkWin(int row, int col) {
		for (ChessRow chessRow : ChessRow.values()) {
			ChessRow.ChessRowPair chessRowPair = chessRow.getRows(row, col, board);
			if (checkRow(chessRowPair.rowList)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 验证是否是活三
	 * 
	 * @return 是否是活三
	 */
	private boolean checkLiveThree(List<Integer> row, int centerIndex) {
		int count = 1;
		// 探右
		for (int i = centerIndex + 1; i < row.size(); i++) {
			if (row.get(i) == ChessColor.BLACK.getValue()) {
				count++;
			} else if (row.get(i) == 0) {
				break;
			} else {
				// 尽头有白棋，直接非活三
				return false;
			}
		}
		// 探左
		for (int i = centerIndex - 1; i >= 0; i--) {
			if (row.get(i) == ChessColor.BLACK.getValue()) {
				count++;
			} else if (row.get(i) == 0) {
				break;
			} else {
				// 尽头有白棋，直接非活三
				return false;
			}
		}
		return count >= 3;
	}

	private boolean checkRow(List<Integer> row) {
		int count = 1;
		for (int i = 1; i < row.size(); i++) {
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
	 * 
	 * @param row row
	 * @param col col
	 * @return 鏄是否有棋子
	 */
	public boolean hasChess(int row, int col) {
		return board[row][col] != 0;
	}

}
