package view;

import java.util.Scanner;

import com.sustc.stdlib.StdDraw;

import model.ChessBoardModel;
import model.ChessColor;
import util.CException;

public class ChessBoard {
	static int size = 15;

	double[][][] chesslocal;// 标定鼠标位置的，跟鼠标监听有关
	int grid;
	static boolean color;// 标定棋子颜色的，true为黑
	int[][] haschess;// 需要知道哪里有棋子时调用这个
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
		color = true;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				int k = readsave.nextInt();
				haschess[i][j] = k;
				readThenDraw(k, i, j);
			}
		}
		readsave.close();
	}

	public void draw(String mark) {// 悔棋
		StdDraw.enableDoubleBuffering();
		drawChessBoard();
		color = true;
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
		// color = !color;
		StdDraw.show();
		StdDraw.disableDoubleBuffering();
	}
//---------------------------落子代码-------------------------------//

	public void play(double x_, double y_) {
		// 鼠标监听
		int x = CrossX(x_);
		int y = CrossY(y_);
		if (x >= 0 && x <= size - 1 && y >= 0 && y <= size - 1) {
			if (haschess[x][y] == 0) {// 满足条件后才会进行以下操作
				try {// 临时储存棋盘
					reserve();
					ChessColor nowColor = color ? ChessColor.BLACK : ChessColor.WHITE;
					boolean isWin = chessBoardModel.playChess(x, y, nowColor);// 这个变量判断胜负以及抛出三三异常
					if (isWin) {
						WinnerInterface.draw();
					}
					StdDraw.setPenColor(nowColor.getColor());
					StdDraw.filledCircle(chesslocal[x][y][0], chesslocal[x][y][1], getPenRadius());
					color = !color;
					step++;
					if (ViewMain.playModel == 1) {
						double[] c = computer(x_, y_);
						x = CrossX(c[0]);
						y = CrossY(c[1]);
						nowColor = color ? ChessColor.BLACK : ChessColor.WHITE;
						isWin = chessBoardModel.playChess(x, y, nowColor);
						if (isWin) {
							WinnerInterface.draw();
						}
						StdDraw.setPenColor(nowColor.getColor());
						StdDraw.filledCircle(chesslocal[x][y][0], chesslocal[x][y][1], getPenRadius());
						color = !color;
						step++;
					}
				} catch (CException e) {
					ForbidInterface.draw();
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

	private void readThenDraw(int k, int i, int j) {// 不在这个方法里面写循环是考虑到调用这个方法时往往会有其他操作需要循坏
		switch (k) {// 注意，每次调用这个方法前都要把color重置为true
		case 0:
			break;
		case 1:
			StdDraw.setPenColor();
			StdDraw.filledCircle(chesslocal[i][j][0], chesslocal[i][j][1], getPenRadius());
			color = !color;
			break;
		case 2:
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.filledCircle(chesslocal[i][j][0], chesslocal[i][j][1], getPenRadius());
			color = !color;
			break;
		}
	}

	private void drawChessBoard() {
		StdDraw.setPenColor();
		/*
		 * 这个棋盘外框大小固定，x范围（140，860），y范围（150，870）
		 */
		int centerX = 140 + (grid * (size + 1) / 2);
		int centerY = 150 + (grid * (size + 1) / 2);

		StdDraw.picture(centerX, centerY, "background4.jpg", 730, 730);// 插入棋盘背景
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
			StdDraw.line(140 + (grid * (i + 1)), 150, 140 + grid * (i + 1), 870);
			StdDraw.line(140, 150 + (grid * (i + 1)), 860, 150 + grid * (i + 1));
			for (int j = 0; j < size; j++) {
				chesslocal[i][j][0] = 140 + ((720 / (size + 1)) * (i + 1));
				chesslocal[j][i][1] = 150 + ((720 / (size + 1)) * (i + 1));
			}
		}
		StdDraw.setPenRadius();
	}

	private double[] computer(double x, double y) {
		double[] c = new double[2];
		c[0] = x + (Math.random() * 2 - 1) * grid;
		c[1] = y + (Math.random() * 2 - 1) * grid;
		if (haschess[CrossX(c[0])][CrossY(c[1])] != 0)
			return computer(x, y);
		return c;

	}

	private void reserve() {
		if (step == 1) {// 存储上一次的棋盘
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++)
					haschess1[i][j] = haschess[i][j];

			}
		}
		if (step >= 2) {
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					haschess2[i][j] = haschess1[i][j];
					haschess1[i][j] = haschess[i][j];
				}
			}
		}
	}
}
