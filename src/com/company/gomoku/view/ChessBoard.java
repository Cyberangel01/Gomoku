package com.company.gomoku.view;

import com.company.gomoku.model.ChessBoardModel;
import com.company.gomoku.model.ChessColor;
import com.company.gomoku.util.CException;
import com.company.gomoku.util.Logger;
import com.sustc.stdlib.StdDraw;

import java.util.Scanner;

public class ChessBoard {
	/*
	 * 这个棋盘外框大小固定，x范围（140，860），y范围（150，870）
	 */
	static int size = 15;
	double[][][] chesslocal;//标定鼠标位置的，跟鼠标监听有关
	int grid;
	static boolean color;// true代表黑子
	int[][] haschess;//需要知道哪里有棋子时调用这个
	// 以下三个变量实现悔棋
	private int[][] haschess1, haschess2;
	private int step;
	private ChessBoardModel chessBoardModel;

//----------------------------初始化----------------------------------//
	public ChessBoard() {
		color = true;
		chesslocal = new double[size][size][2];
		haschess = new int[size][size];// 1代表黑子，2代表白子
		chessBoardModel = new ChessBoardModel(size, haschess);
		haschess1 = new int[size][size];
		haschess2 = new int[size][size];
		step = 0;
		grid = (720 / (size + 1));
	}

//------------------------供调用的绘制方法-----------------------------//
	public void draw() {
		drawChessBoard();
	}

	public void draw(Scanner readsave) {// 读取存档
		drawChessBoard();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				int k = readsave.nextInt();
				haschess[i][j] = k;
				readThenDraw(k, i, j);
			}
		}
		readsave.close();
	}

	public void draw(String mark) {// 悔棋
		StdDraw.enableDoubleBuffering();
		drawChessBoard();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				int k = haschess1[i][j];
				readThenDraw(k, i, j);
			}
		}
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				haschess[i][j] = haschess1[i][j];
				haschess1[i][j] = haschess2[i][j];
			}
		}
		step = Math.max(2, step - 1);
		StdDraw.show();
		StdDraw.disableDoubleBuffering();
	}

	
//---------------------------辅助函数-------------------------------//
	private void drawChessBoard() {
		StdDraw.setPenColor();

		int centerX = 140 + (grid * (size + 1) / 2);
		int centerY = 150 + (grid * (size + 1) / 2);

		StdDraw.picture(centerX, centerY, "test.jpg", 730, 730);// 插入棋盘背景
		StdDraw.filledCircle(centerX, centerY, 5);// 绘制中心点

		// 绘制外边框
		double[] x = { 135, 865, 865, 135 };
		double[] y = { 145, 145, 875, 875 };
		StdDraw.polygon(x, y);

		// 绘制内边框
		double[] x_ = { 140, 860, 860, 140 };
		double[] y_ = { 150, 150, 870, 870 };
		StdDraw.polygon(x_, y_);

		// 绘制线格
		for (int i = 0; i < size; i++) {
			StdDraw.line(140 + (grid * (i + 1)), 150, 140 + grid * (i + 1), 870);// 依次绘制竖线
			StdDraw.line(140, 150 + (grid * (i + 1)), 860, 150 + grid * (i + 1));// 依次绘制横线
			for (int j = 0; j < size; j++) {
				chesslocal[i][j][0] = 140 + ((720 / (size + 1)) * (i + 1));// 获取每个交点的x坐标
				chesslocal[j][i][1] = 150 + ((720 / (size + 1)) * (i + 1));// 获取每个交点的y坐标
			}
		}
		StdDraw.setPenRadius();
	}

//---------------------------落子代码-------------------------------//
	public void play(double x_, double y_) {
		// 鼠标监听
		int x = CrossX(x_);
		int y = CrossY(y_);
		// 储存代码
		if (x < 0 || x > size - 1 || y < 0 || y > size - 1) {
			// 这里涉及到一个判断次数的问题
			// 考虑到大部分时间是在棋盘中操作，如果判断鼠标是否在棋盘中，则多数情况下要判断四回
			// 按这些代码处理，判断的期望值应该是2（估计），甚至更低
		} else {
			if (step == 1)
				for (int i = 0; i < size; i++) {
					for (int j = 0; j < size; j++)
						haschess1[i][j] = haschess[i][j];
				}
			if (step >= 2) {
				for (int i = 0; i < size; i++) {
					for (int j = 0; j < size; j++) {
						haschess2[i][j] = haschess1[i][j];
						haschess1[i][j] = haschess[i][j];
					}
				}
			}
			// 正式下棋
			if (haschess[x][y] == 0) {
				try {
					ChessColor nowColor = color ? ChessColor.BLACK : ChessColor.WHITE;
					boolean isWin = chessBoardModel.playChess(x, y, nowColor);
					if (isWin) {
						Logger.info(nowColor + " win");
					}
					StdDraw.setPenColor(nowColor.getColor());
					StdDraw.filledCircle(chesslocal[x][y][0], chesslocal[x][y][1], getPenRadius());// 判断是否有棋子，并且添加棋子
					color = !color;
					step++;
				} catch (CException e) {
					Logger.error(e.getMessage());
				}
			}
			StdDraw.show();
		}
	}

//---------------------------辅助函数-------------------------------//不用管，只起到辅助功能
	private int CrossX(double x) {

		return (int) (Math.round((x - 140) / grid) - 1);

	}

	private int CrossY(double y) {

		return (int) (Math.round((y - 150) / grid) - 1);
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

	private void readThenDraw(int k, int i, int j) {
		switch (k) {
		case 0:
			break;
		case 1:
			color = false;
			StdDraw.setPenColor();
			StdDraw.filledCircle(chesslocal[i][j][0], chesslocal[i][j][1], getPenRadius());
			break;
		case 2:
			color = true;// 最后一次执行决定下一次的落子颜色
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.filledCircle(chesslocal[i][j][0], chesslocal[i][j][1], getPenRadius());
			break;
		}
	}

	public static boolean getColor() {
		return color;
	}
}
