package view;

import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import edu.princeton.cs.algs4.StdDraw;

public class Button {
	double ScaleX;
	double ScaleY;
	double centerX;
	double centerY;
	String text;
	int function;
	int pound = 50;

	public Button(double centerX, double centerY, String te, int function) {
		this.ScaleX = 120;
		this.ScaleY = 240;
		this.text = te;
		this.function = function;
	}

	public Button(double ScaleX, double ScaleY, double centerX, double centerY, String te, int function) {
		this.ScaleX = ScaleX;
		this.ScaleY = ScaleY;
		this.centerX = centerX;
		this.centerY = centerY;
		text = te;
		this.function = function;
	}

	public Button(double ScaleX, double ScaleY, double centerX, double centerY, String te, int function, int pound) {
		this.ScaleX = ScaleX;
		this.ScaleY = ScaleY;
		this.centerX = centerX;
		this.centerY = centerY;
		text = te;
		this.function = function;
		this.pound = pound;
	}

	public void drawButton() {
		// StdDraw.picture(centerX, centerY, "test.jpg", ScaleX, ScaleY);//
		// Button.jpg只是测试品，具体使用待定
		StdDraw.setFont(new Font("Arival", Font.BOLD, pound));
		// StdDraw.setPenColor();
		StdDraw.text(centerX, centerY - 5, text);
	}

	public void isPressed(double x, double y) {// 判断是否被点击
		double[] localX = { centerX - ScaleX / 2, centerX + ScaleX / 2 };
		double[] localY = { centerY - ScaleY / 2, centerY + ScaleY / 2 };
		if (include(x, y, localX, localY)) {
			function();
		}

	}

	protected void function() {

		switch (function) {
		case 0:// 退出
			save();
			System.exit(0);
			break;
		case 1:// 菜单界面
			save();
			StartInterface.draw();
			break;
		case 2:// 游戏界面，这里有个待解决的bug
			PlayInterface.draw();
			StdDraw.show();
			break;
		case 3:// 选项界面
//			try {
//				PrintWriter save = new PrintWriter("save.txt");
//				int[][][] initialize = new int[19][19][1];
//				for (int i = 0; i < 19; i++) {
//					for (int j = 0; j < 19; j++) {
//						save.print(initialize[i][j][0] + " ");
//					}
//				}
//				save.close();
//			} catch (FileNotFoundException e) {
//			}
			OptionInterface.draw();
			break;
		case 31:// 依次为三种大小的棋盘
			ChessBoard.size = 15;
			StartInterface.draw();
			break;
		case 32:
			ChessBoard.size = 17;
			StartInterface.draw();
			break;
		case 33:
			ChessBoard.size = 19;
			StartInterface.draw();
			break;
		case 4:// 模式选择界面
			ModeInterface.draw();
			break;
		case 41:// 玩家对战
			// 对战代码?
			PlayInterface.draw();
			// PlayInterface.draw();
			break;
		case 42:// 人工智障
			// 对战代码
			PlayInterface.draw();
			break;
		case 5:// 暂停
			break;
		case 6:// 导入存档,继续游戏
			readsave();
			break;
		case 7:// 悔棋
			PlayInterface.board.draw("悔棋");
			break;
		}
	}

//辅助函数，判断是否包含在Button中
	private static boolean include(double x, double y, double[] localX, double[] localY) {
		return x >= localX[0] && x <= localX[1] && y >= localY[0] && y <= localY[1];
	}

	private static void save() {
		try {// 存档
			PrintWriter save = new PrintWriter("save.txt");
			int[][][] text = PlayInterface.board.haschess.clone();
			for (int i = 0; i < PlayInterface.board.haschess.length; i++) {
				for (int j = 0; j < PlayInterface.board.haschess.length; j++) {
					save.print(text[i][j][0] + " ");
				}
			}
			save.close();
		} catch (FileNotFoundException e) {
		} catch (NullPointerException e) {
		}
	}

	private static void readsave() {
		try {
			Scanner readsave = new Scanner(new File("save.txt"));
			Scanner readsave_ = new Scanner(new File("save.txt"));
			String s = readsave_.nextLine();
			ChessBoard.size = (int) Math.sqrt(s.length() / 2);
			PlayInterface.draw(readsave);
		} catch (FileNotFoundException e) {
		}
	}

}
