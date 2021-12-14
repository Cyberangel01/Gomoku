package view;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Scanner;

import edu.princeton.cs.algs4.StdDraw;

//ע�⣬������Ҫͨ��������������ť��ӵ������У���ʹ�ý���ǰ�����Ƚ���ʵ����
public class Interface {
	static ArrayList<Button> buttons;

	public static void draw() {
	}

	public static void hook(double x, double y) {
	}

//��������
	protected static void drawButtons() {
		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).drawButton();
		}
	}
}

class StartInterface extends Interface {
	private static Button newGame = new Button(300, 120, 500, 505, "����Ϸ", 4);
	private static Button continued = new Button(300, 120, 500, 370, "������Ϸ", 6);
	private static Button option = new Button(300, 120, 500, 235, "ѡ��", 3);
	private static Button exit = new Button(300, 120, 500, 100, "�˳�", 0);
	private static Button[] buttons = { newGame, continued, option, exit };

	public static void draw() {
		StdDraw.enableDoubleBuffering();
		StdDraw.clear();
		StdDraw.picture(500, 500, "background3.jpg");
		StdDraw.setFont(new Font("Arival", Font.BOLD, 70));
		StdDraw.setPenColor();
		StdDraw.text(500, 700, "������");
		StdDraw.setPenColor();
		drawButtons();
		StdDraw.show();
		StdDraw.disableDoubleBuffering();
		ViewMain.nowInterface = 0;
	}

	public static void hook(double x, double y) {
		newGame.isPressed(x, y);
		continued.isPressed(x, y);
		option.isPressed(x, y);
		exit.isPressed(x, y);
	}

	protected static void drawButtons() {
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].drawButton();
		}
	}

}

class ModeInterface extends Interface {
	private static Button playersGame = new Button(300, 150, 500, 600, "��Ҷ�ս", 41);
	private static Button aiGame = new Button(300, 150, 500, 300, "�˹�����", 42);
	private static Button[] buttons = { playersGame, aiGame };

	public static void draw() {
		StdDraw.enableDoubleBuffering();
		StdDraw.clear();
		StdDraw.picture(500, 500, "background3.jpg");
		StdDraw.setFont(new Font("Arival", Font.BOLD, 70));
		StdDraw.text(500, 800, "���˭��һ�ѣ�");
		drawButtons();
		StdDraw.show();
		StdDraw.disableDoubleBuffering();
		ViewMain.nowInterface = 1;
	}

	public static void hook(double x, double y) {
		playersGame.isPressed(x, y);
		aiGame.isPressed(x, y);
	}

	protected static void drawButtons() {
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].drawButton();
		}
	}
}

class OptionInterface extends Interface {
	private static Button size15 = new Button(300, 150, 500, 600, "15x15", 31);
	private static Button size17 = new Button(300, 150, 500, 400, "17x17", 32);
	private static Button size19 = new Button(300, 150, 500, 200, "19x19", 33);
	private static Button[] buttons = { size15, size17, size19 };

	public static void draw() {
		StdDraw.enableDoubleBuffering();
		StdDraw.clear();
		StdDraw.picture(500, 500, "background3.jpg");
		StdDraw.setFont(new Font("Arival", Font.BOLD, 70));
		StdDraw.text(500, 800, "��ѡ�����̴�С");
		drawButtons();
		StdDraw.show();
		StdDraw.disableDoubleBuffering();
		ViewMain.nowInterface = 2;
	}

	public static void hook(double x, double y) {
		size15.isPressed(x, y);
		size17.isPressed(x, y);
		size19.isPressed(x, y);
	}

	protected static void drawButtons() {
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].drawButton();
		}
	}

}

class PlayInterface extends Interface {
	private static Button undo = new Button(120, 60, 790, 60, "����", 7, 25);
	private static Button menu = new Button(120, 60, 80, 60, "���ز˵�", 1, 25);
	private static Button restart = new Button(120, 60, 260, 60, "���¿�ʼ", 2, 25);// �����и��������bug
	private static Button pause = new Button(120, 60, 500, 60, "��ͣ", 5, 25);
	private static Button exit = new Button(120, 60, 920, 60, "�˳�", 0, 25);
	private static Button[] buttons = { undo, menu, restart, pause, exit };
	static ChessBoard board;

//����draw�����Ƚϸ��ӣ���Ϊ������drawҪʵ�ֶ����Ĺ��ܣ���Ҫ���ص�draw����
	public static void draw() {
		board = new ChessBoard();
		StdDraw.enableDoubleBuffering();
		StdDraw.clear();
		StdDraw.picture(500, 500, "background1.jpg", 1000, 1000);
		board.draw();
		StdDraw.setPenColor(StdDraw.WHITE);
		drawButtons();
		StdDraw.show();
		StdDraw.disableDoubleBuffering();
		ViewMain.nowInterface = 3;
	}

	public static void draw(Scanner readsave) {
		board = new ChessBoard();
		StdDraw.enableDoubleBuffering();
		StdDraw.clear();
		StdDraw.picture(500, 500, "background1.jpg", 1000, 1000);
		board.draw(readsave);
		StdDraw.setPenColor(StdDraw.WHITE);
		drawButtons();
		StdDraw.show();
		StdDraw.disableDoubleBuffering();
		ViewMain.nowInterface = 3;
	}

	public static void hook(double x, double y) {
		board.play(x, y);
		menu.isPressed(x, y);
		pause.isPressed(x, y);
		restart.isPressed(x, y);
		exit.isPressed(x, y);
		undo.isPressed(x, y);
	}

	protected static void drawButtons() {
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].drawButton();
		}
	}

}
