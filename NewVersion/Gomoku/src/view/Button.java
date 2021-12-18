package view;

import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import com.sustc.stdlib.StdDraw;

public class Button {
	double ScaleX;
	double ScaleY;
	double centerX;
	double centerY;
	String text;
	int function;
	int pound = 50;

	// �������������㲻ͬ�İ�ť����
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

	public void drawButton() {// ������Ը�����Ҫ�ı�
		StdDraw.setFont(new Font("Arival", Font.BOLD, pound));
		StdDraw.text(centerX, centerY - 5, text);
	}

	public void isPressed(double x, double y) {// �ж��Ƿ񱻵��
		double[] localX = { centerX - ScaleX / 2, centerX + ScaleX / 2 };
		double[] localY = { centerY - ScaleY / 2, centerY + ScaleY / 2 };
		if (include(x, y, localX, localY)) {
			function();
		}

	}

	protected void function() {// ��ͬ�İ�ťʵ�ֵĹ��ܣ�mapֻ�ܴ������û�и��õķ���ǰ����switch�ṹ

		switch (function) {
		case 0:// �˳�
			save();
			System.exit(0);
			break;
		case 1:// �˵�����
			save();
			StartInterface.draw();
			break;
		case 2:
			PlayInterface.draw();
			break;
		case 3:// ѡ�����
			OptionInterface.draw();
			break;
		case 31:// ����Ϊ���ִ�С������
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
		case 4:// ģʽѡ�����
			ModeInterface.draw();
			break;
		case 41:// ��Ҷ�ս
			PlayInterface.draw();
			break;
		case 42:// �˹�����
			ViewMain.playModel = 1;
			PlayInterface.draw();
			break;
		case 5:// ����浵,������Ϸ
			readsave();
			break;
		case 6:// ���壋
			PlayInterface.board.draw("����");
			break;
		}
	}

//---------------------------------------��������-------------------------------------------------//
	private static boolean include(double x, double y, double[] localX, double[] localY) {
		return x >= localX[0] && x <= localX[1] && y >= localY[0] && y <= localY[1];
	}

	static void save() {
		try {// ����
			PrintWriter save = new PrintWriter("save.txt");
			int[][] text = PlayInterface.board.haschess.clone();
			for (int i = 0; i < PlayInterface.board.haschess.length; i++) {
				for (int j = 0; j < PlayInterface.board.haschess.length; j++) {
					save.print(text[i][j] + " ");
				}
			}
			save.println();
			save.print(ViewMain.playModel);
			save.close();
		} catch (FileNotFoundException e) {
		} catch (NullPointerException e) {
		}
	}

	static void readsave() {
		try {// ����
			Scanner readsave = new Scanner(new File("save.txt"));
			Scanner readsave_ = new Scanner(new File("save.txt"));
			String s = readsave_.nextLine();
			ViewMain.playModel = Integer.valueOf(readsave_.nextLine());
			ChessBoard.size = (int) Math.sqrt(s.length() / 2);
			PlayInterface.draw(readsave);
		} catch (FileNotFoundException e) {
		}
	}

}
