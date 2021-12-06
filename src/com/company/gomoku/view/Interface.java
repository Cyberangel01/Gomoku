package com.company.gomoku.view;

/*
 * interface类已实现功能：
 * 	start界面（不完整）
 * 	play界面（不完整）
 * 
 * interface类待实现功能：
 * 	选项界面，从start界面接入，包括选择棋盘大小
 * 	选项界面，从start界面接入，选择对手，人或电脑
 * 	
 * 	2021.12.6
 */

import com.sustc.stdlib.StdDraw;

public class Interface implements Button.OnClickListener {

	private Button newGame;

	public Interface() {
		StdDraw.setCanvasSize(800, 800);
		StdDraw.setXscale(0, 1000);
		StdDraw.setYscale(0, 1000);
		// StdDraw.picture(x, y, filename, scaledWidth, scaledHeight);
		newGame = new Button(300, 120, 725, 775, "新游戏");
		newGame.setOnClickListener(this);
	}

	public void start() {
		while (true) {
			if (StdDraw.isMousePressed()) {
				double mouseX = StdDraw.mouseX();
				double mouseY = StdDraw.mouseY();
				newGame.checkPressed(mouseX, mouseY);
			}
		}
	}

	@Override
	public void onClick(Button view) {
		if (view == newGame) {
			play();
		}
	}

	public void play() {
		ChessBoardView board = new ChessBoardView(19);
		board.play();
	}
}
