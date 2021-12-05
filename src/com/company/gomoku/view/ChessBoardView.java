package com.company.gomoku.view;
/*
 * 已实现功能：
 * 绘制棋盘（可变大小），落子（包括是否能落子），棋盘状态储存（1次）
 * 
 * 待实现功能：
 * 鼠标监听代码方法化封装，多次状态储存（前三次），插入背景，设计按钮
 *	2021、12、1
 */

import com.sustc.stdlib.StdDraw;

public class ChessBoardView {
	/*
	 * 这个棋盘大小固定，x范围（140，860），y范围（150，870）
	 * 
	 */
	public static void main(String[] args) {
		StdDraw.setCanvasSize(800, 800);
		StdDraw.setXscale(0, 1000);
		StdDraw.setYscale(0, 1000);
		StdDraw.enableDoubleBuffering();

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
		int size = 15;// 可变量
		double[][][] chesslocal = new double[size][size][2];
		for (int i = 0; i < size; i++) {
			StdDraw.line(140 + ((720 / (size + 1)) * (i + 1)), 150, 140 + ((720 / (size + 1)) * (i + 1)), 870);// 依次绘制竖线
			StdDraw.line(140, 150 + ((720 / (size + 1)) * (i + 1)), 860, 150 + ((720 / (size + 1)) * (i + 1)));// 依次绘制横线
			for (int j = 0; j < size; j++) {
				chesslocal[i][j][0] = 140 + ((720 / (size + 1)) * (i + 1));// 获取每个交点的x坐标
				chesslocal[j][i][1] = 150 + ((720 / (size + 1)) * (i + 1));// 获取每个交点的y坐标
			}

		} // 绘制中心点
		StdDraw.filledCircle(140 + ((720 / (size + 1)) * 8), 150 + ((720 / (size + 1)) * 8), 5);
		StdDraw.show();
		StdDraw.disableDoubleBuffering();

//-----------------------------------------------------------------------------------------------//落子代码
		int[][][] haschess = new int[size][size][1];// 1代表黑子，2代表白子
		boolean color = true;// true代表黑子
		while (true) {
			StdDraw.setPenColor(color ? StdDraw.BLACK : StdDraw.RED);
			if (StdDraw.isMousePressed()) {
				// 鼠标监听
				int x = (int) Math.min(Math.max((Math.round((StdDraw.mouseX() - 140) / 45) - 1), 0),
						Math.min((Math.round((StdDraw.mouseX() - 140) / 45) - 1), 14));// 计算出距离鼠标最近的交点的序号，用最大最小的方法保障不会越界
				int y = (int) Math.min(Math.max((Math.round((StdDraw.mouseY() - 150) / 45) - 1), 0),
						Math.min((Math.round((StdDraw.mouseY() - 150) / 45) - 1), 14));
				x = Math.max(x, 0);
				y = Math.max(y, 0);// 这里单独写是因为前面太长了，这部分应该可以单独写一个方法
				if (haschess[x][y][0] == 0) {
					StdDraw.filledCircle(chesslocal[x][y][0], chesslocal[x][y][1], 20);
					haschess[x][y][0] = color ? 1 : 2;
					color = !color;
				}
				StdDraw.show();
				StdDraw.pause(300);
			}

		}
	}
}
