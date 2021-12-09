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

import com.company.gomoku.controller.IGameController;
import com.company.gomoku.model.Player;
import com.company.gomoku.util.CException;
import com.company.gomoku.util.Logger;
import com.sustc.stdlib.StdDraw;

public class ChessBoardView {
	/*
	 * 这个棋盘大小固定，x范围（140，860），y范围（150，870）
	 * 
	 */
	private int size;

	private double[][][] chesslocal;

	private int grid;

	private IGameController gameController;

	public ChessBoardView(int size) {
		this.size = size;
		chesslocal = new double[size][size][2];
		grid = (720 / (size + 1));
		// 构造这个棋盘的时候就画出来了
		this.DrawChessBoard();
	}

	private void DrawChessBoard() {
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

	public void setGameController(IGameController gameController) {
		this.gameController = gameController;
	}

	/**
	 * 落子代码
	 */
	public void play() {
		StdDraw.pause(300);
		while (true) {
			if (StdDraw.isMousePressed()) {
				// 鼠标监听
				int x = crossX();
				int y = crossY();
				try {
					Player nowPlayer = gameController.getNowTurnPlayer();
					boolean isWin = gameController.playChess(x, y);
					// 下棋
					StdDraw.setPenColor(nowPlayer.getChessColor().getColor());
					StdDraw.filledCircle(chesslocal[x][y][0], chesslocal[x][y][1], getPenRadius());
					if (isWin) {
						Logger.info(nowPlayer + " win!!!");
						break;
					}
				} catch (CException e) {
					Logger.error(e.getMessage());
				}
				StdDraw.show();
				StdDraw.pause(300);
			}
		}
	}

	//以下均为辅助函数
	private int crossX() {
		int x = (int) Math.min(Math.max((Math.round((StdDraw.mouseX() - 140) / grid) - 1), 0),
				Math.min((Math.round((StdDraw.mouseX() - 140) / grid) - 1), size - 1));
		x = Math.max(x, 0);// 计算出距离鼠标最近的交点的序号，用最大最小的方法保障不会越界
		return x;

	}

	private int crossY() {
		int y = (int) Math.min(Math.max((Math.round((StdDraw.mouseY() - 150) / grid) - 1), 0),
				Math.min((Math.round((StdDraw.mouseY() - 150) / grid) - 1), size - 1));
		y = Math.max(y, 0);
		return y;
	}

	private double getPenRadius() {
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
