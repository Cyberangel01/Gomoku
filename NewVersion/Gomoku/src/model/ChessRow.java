package model;

import java.util.ArrayList;
import java.util.List;

public enum ChessRow {

	LEFT_RIGHT {
		@Override
		public ChessRowPair getRows(int row, int col, int[][] board) {
			// 横
			List<Integer> rowList = new ArrayList<>();
			int index = 0;
			for (int value : CHECK_ARRAY) {
				if (isInBound(row + value, col, board.length)) {
					if (value == 0) {
						index = rowList.size();
					}
					rowList.add(board[row + value][col]);
				}
			}
			return new ChessRowPair(rowList, index);
		}
	},
	UP_DOWN {
		@Override
		public ChessRowPair getRows(int row, int col, int[][] board) {
			// 竖
			List<Integer> rowList = new ArrayList<>();
			int index = 0;
			for (int value : CHECK_ARRAY) {
				if (isInBound(row, col + value, board.length)) {
					if (value == 0) {
						index = rowList.size();
					}
					rowList.add(board[row][col + value]);
				}
			}
			return new ChessRowPair(rowList, index);
		}
	},
	LEFT_RIGHT_DOWN {
		@Override
		public ChessRowPair getRows(int row, int col, int[][] board) {
			// 左上右下
			List<Integer> rowList = new ArrayList<>();
			int index = 0;
			for (int value : CHECK_ARRAY) {
				if (isInBound(row + value, col + value, board.length)) {
					if (value == 0) {
						index = rowList.size();
					}
					rowList.add(board[row + value][col + value]);
				}
			}
			return new ChessRowPair(rowList, index);
		}
	},
	LEFT_RIGHT_UP {
		@Override
		public ChessRowPair getRows(int row, int col, int[][] board) {
			// 右上左下
			List<Integer> rowList = new ArrayList<>();
			int index = 0;
			for (int value : CHECK_ARRAY) {
				if (isInBound(row + value, col - value, board.length)) {
					if (value == 0) {
						index = rowList.size();
					}
					rowList.add(board[row + value][col - value]);
				}
			}
			return new ChessRowPair(rowList, index);
		}
	};

	static class ChessRowPair {
		List<Integer> rowList;
		int centerIndex;

		public ChessRowPair(List<Integer> rowList, int centerIndex) {
			this.rowList = rowList;
			this.centerIndex = centerIndex;
		}
	}

	private static final int[] CHECK_ARRAY = new int[] { -4, -3, -2, -1, 0, 1, 2, 3, 4 };

	private static boolean isInBound(int row, int col, int size) {
		return row >= 0 && row < size && col >= 0 && col < size;
	}

	public ChessRowPair getRows(int row, int col, int[][] board) {
		return null;
	}
}
