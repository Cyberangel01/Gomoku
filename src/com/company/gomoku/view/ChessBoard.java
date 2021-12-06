package com.company.gomoku.view;
/*
 * 已实现功能：
 * 	绘制棋盘（可变大小）
 * 	落子（包括是否能落子）
 * 	棋盘状态储存（1次）
 * 	鼠标监听代码方法化封装
 * 	插入背景
 * 
 * 待实现功能：
 * 	多次状态储存（前三次）
 * 
 * 
 *	2021.12.6
 */

import com.sustc.stdlib.StdDraw;

public class ChessBoard {
	/*
	 * 这个棋盘大小固定，x范围（140，860），y范围（150，870）
	 * 
	 */
	static int size;
	double[][][] chesslocal;
	static int grid;

	public ChessBoard(int size) {
		ChessBoard.size = size;
		chesslocal = new double[size][size][2];
		grid = (720 / (size + 1));
		// 构造这个棋盘的时候就画出来了
		this.DrawChessBoard();
	}

	public void DrawChessBoard() {
		StdDraw.clear();
		StdDraw.enableDoubleBuffering();

		int centerX = 140 + (grid * (size + 1) / 2);
		int centerY = 150 + (grid * (size + 1) / 2);

		StdDraw.picture(centerX, centerY, "test.jpg", 730, 730);// 插入棋盘背景
		StdDraw.filledCircle(centerX, centerY, 5);// 绘制中心点

		// 绘制外边框
		StdDraw.line(135, 145, 865, 145);// 横线
		StdDraw.line(135, 875, 865, 875);
		StdDraw.line(135, 145, 135, 875);// 竖线
		StdDraw.line(865, 145, 865, 875);
		// 绘制内边框
		StdDraw.line(140, 150, 860, 150);// 横线
		StdDraw.line(140, 870, 860, 870);
		StdDraw.line(140, 150, 140, 870);// 竖线
		StdDraw.line(860, 150, 860, 870);

		// 绘制线格
		for (int i = 0; i < size; i++) {
			StdDraw.line(140 + (grid * (i + 1)), 150, 140 + grid * (i + 1), 870);// 依次绘制竖线
			StdDraw.line(140, 150 + (grid * (i + 1)), 860, 150 + grid * (i + 1));// 依次绘制横线
			for (int j = 0; j < size; j++) {
				chesslocal[i][j][0] = 140 + ((720 / (size + 1)) * (i + 1));// 获取每个交点的x坐标
				chesslocal[j][i][1] = 150 + ((720 / (size + 1)) * (i + 1));// 获取每个交点的y坐标
			}

		}
		StdDraw.show();
		StdDraw.disableDoubleBuffering();
	}

//-----------------------------------------------------------------------------------------------//落子代码
	public void play() {
		StdDraw.pause(300);
		int[][][] haschess = new int[size][size][1];// 1代表黑子，2代表白子
		boolean color = true;// true代表黑子
		while (true) {
			StdDraw.setPenColor(color ? StdDraw.BLACK : StdDraw.WHITE);
			if (StdDraw.isMousePressed()) {
				// 鼠标监听
				int x = ChessBoard.CrossX();
				int y = ChessBoard.CrossY();
				if (haschess[x][y][0] == 0) {
					StdDraw.filledCircle(chesslocal[x][y][0], chesslocal[x][y][1], getPenRadius());// 判断是否有棋子，并且添加棋子
					haschess[x][y][0] = color ? 1 : 2;
					color = !color;
				}
				StdDraw.show();
				StdDraw.pause(300);
			}

		}
	}

//以下均为辅助函数
	private static int CrossX() {
		int x = (int) Math.min(Math.max((Math.round((StdDraw.mouseX() - 140) / grid) - 1), 0),
				Math.min((Math.round((StdDraw.mouseX() - 140) / grid) - 1), size - 1));
		x = Math.max(x, 0);// 计算出距离鼠标最近的交点的序号，用最大最小的方法保障不会越界
		return x;

	}

	private static int CrossY() {
		int y = (int) Math.min(Math.max((Math.round((StdDraw.mouseY() - 150) / grid) - 1), 0),
				Math.min((Math.round((StdDraw.mouseY() - 150) / grid) - 1), size - 1));
		y = Math.max(y, 0);
		return y;
	}

	private static double getPenRadius() {
		switch (size) {
		case 15:
			return 20;
		case 17:
			return 17.5;
		case 19:
			return 15;
		}
		return 20;
	}

}