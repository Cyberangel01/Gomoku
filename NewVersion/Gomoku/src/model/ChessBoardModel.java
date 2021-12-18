package model;

import java.util.List;

import util.CException;

/**
 * ����
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
	 * ���壋
	 * 
	 * @param row        ��
	 * @param col        ��
	 * @param chessColor ��ɫ
	 * @throws CException �쳣
	 * @return �Ƿ��ʤ
	 */
	public boolean playChess(int row, int col, ChessColor chessColor) throws CException {
		// �������
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
		// ��ɫû�н���
		if (chessColor == ChessColor.WHITE) {
			return false;
		}
		// �������˺���
		board[row][col] = ChessColor.BLACK.getValue();
		// ������ҷ��أ����ɻ������ͽ���
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
		// ��ɫ����ͬʱ����������
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
	 * �жϵ�ǰ����Ƿ���ʤ����
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
	 * ��֤�Ƿ��ǻ���
	 * 
	 * @return �Ƿ��ǻ���
	 */
	private boolean checkLiveThree(List<Integer> row, int centerIndex) {
		int count = 1;
		// ̽��
		for (int i = centerIndex + 1; i < row.size(); i++) {
			if (row.get(i) == ChessColor.BLACK.getValue()) {
				count++;
			} else if (row.get(i) == 0) {
				break;
			} else {
				// ��ͷ�а��壬ֱ�ӷǻ���
				return false;
			}
		}
		// ̽��
		for (int i = centerIndex - 1; i >= 0; i--) {
			if (row.get(i) == ChessColor.BLACK.getValue()) {
				count++;
			} else if (row.get(i) == 0) {
				break;
			} else {
				// ��ͷ�а��壬ֱ�ӷǻ���
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
	 * ��λ���Ƿ�������
	 * 
	 * @param row row
	 * @param col col
	 * @return 是��Ƿ�������
	 */
	public boolean hasChess(int row, int col) {
		return board[row][col] != 0;
	}

}
