package com.company.gomoku.view;

/*
 * Button类已实现功能：
 * 	跳转（子类重写，基于interface类实现）
 * 
 * Button类待解决问题：
 * 	Button中的文字太小，需要调整
 * 	目前还没有构造子类，function功能需要重写
 * 	子类包括：新游戏，继续游戏，重新开始，选项，退出，（暂停），回到主界面
 * 
 * 2021.12.6
 */

import com.sustc.stdlib.StdDraw;

public class Button {
	private double scaleX;
	private double scaleY;
	private double centerX;
	private double centerY;
	private String text;
	private OnClickListener onClickListener;

	public Button(double centerX, double centerY, String text) {
		scaleX = 120;
		scaleY = 240;
		this.text = text;
		drawButton();
	}

	public Button(double ScaleX, double ScaleY, double centerX, double centerY, String text) {
		this.scaleX = ScaleX;
		this.scaleY = ScaleY;
		this.centerX = centerX;
		this.centerY = centerY;
		this.text = text;
		drawButton();
	}

	public void setOnClickListener(OnClickListener onClickListener) {
		this.onClickListener = onClickListener;
	}

	public void drawButton() {
		StdDraw.picture(centerX, centerY, "Button.jpg", scaleX, scaleY);// Button.jpg只是测试品，具体使用待定
		StdDraw.text(centerX, centerY, text);
	}

	// 判断是否被点击
	public void checkPressed(double mouseX, double mouseY) {
		double[] localX = { centerX - scaleX / 2, centerX + scaleX / 2 };
		double[] localY = { centerY - scaleY / 2, centerY + scaleY / 2 };
		double[] mouse = { mouseX, mouseY };
		if (include(mouse, localX, localY) && onClickListener != null) {
			onClickListener.onClick(this);
		}
	}

    //辅助函数，判断是否包含在Button中
	private static boolean include(double[] x, double[] localX, double[] localY) {
		return x[0] >= localX[0] && x[0] <= localX[1] && x[1] >= localY[0] && x[1] <= localY[1];
	}

	interface OnClickListener {
		void onClick(Button view);
	}
}
