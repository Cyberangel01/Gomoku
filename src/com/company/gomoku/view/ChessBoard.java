package view;

import java.util.Scanner;

import edu.princeton.cs.algs4.StdDraw;

public class ChessBoard {
	/*
	 * �����������С�̶���x��Χ��140��860����y��Χ��150��870��
	 */
	static int size = 15;
	double[][][] chesslocal;
	int grid;
	static boolean color;// true�������
	int[][][] haschess;
	// ������������ʵ�ֻ���
	private int[][][] haschess1, haschess2;
	private int step;

//----------------------------��ʼ��----------------------------------//
	public ChessBoard() {
		color = true;
		chesslocal = new double[size][size][2];
		haschess = new int[size][size][1];// 1������ӣ�2�������
		haschess1 = new int[size][size][1];
		haschess2 = new int[size][size][1];
		step = 0;
		grid = (720 / (size + 1));
	}

//------------------------�����õĻ��Ʒ���-----------------------------//
	public void draw() {
		drawChessBoard();
	}

	public void draw(Scanner readsave) {// ��ȡ�浵
		drawChessBoard();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				int k = readsave.nextInt();
				haschess[i][j][0] = k;
				readThenDraw(k, i, j);
			}
		}
		readsave.close();
	}

	public void draw(String mark) {// ����
		StdDraw.enableDoubleBuffering();
		drawChessBoard();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				int k = haschess1[i][j][0];
				readThenDraw(k, i, j);
			}
		}
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				haschess[i][j][0] = haschess1[i][j][0];
				haschess1[i][j][0] = haschess2[i][j][0];
			}
		}
		step = Math.max(2, step - 1);
		StdDraw.show();
		StdDraw.disableDoubleBuffering();
	}

	// ���������붼�����������Ǹ���ʵ���˴��������
//---------------------------���ƴ���-------------------------------//
	private void drawChessBoard() {
		StdDraw.setPenColor();

		int centerX = 140 + (grid * (size + 1) / 2);
		int centerY = 150 + (grid * (size + 1) / 2);

		StdDraw.picture(centerX, centerY, "test.jpg", 730, 730);// �������̱���
		StdDraw.filledCircle(centerX, centerY, 5);// �������ĵ�

		// ������߿�
		double[] x = { 135, 865, 865, 135 };
		double[] y = { 145, 145, 875, 875 };
		StdDraw.polygon(x, y);

		// �����ڱ߿�
		double[] x_ = { 140, 860, 860, 140 };
		double[] y_ = { 150, 150, 870, 870 };
		StdDraw.polygon(x_, y_);

		// �����߸�
		for (int i = 0; i < size; i++) {
			StdDraw.line(140 + (grid * (i + 1)), 150, 140 + grid * (i + 1), 870);// ���λ�������
			StdDraw.line(140, 150 + (grid * (i + 1)), 860, 150 + grid * (i + 1));// ���λ��ƺ���
			for (int j = 0; j < size; j++) {
				chesslocal[i][j][0] = 140 + ((720 / (size + 1)) * (i + 1));// ��ȡÿ�������x����
				chesslocal[j][i][1] = 150 + ((720 / (size + 1)) * (i + 1));// ��ȡÿ�������y����
			}
		}
		StdDraw.setPenRadius();
	}

//---------------------------���Ӵ���-------------------------------//
	public void play(double x_, double y_) {
		// ������
		int x = CrossX(x_);
		int y = CrossY(y_);
		// �������
		if (x < 0 || x > size - 1 || y < 0 || y > size - 1) {
			// �����漰��һ���жϴ���������
			// ���ǵ��󲿷�ʱ�����������в���������ж�����Ƿ��������У�����������Ҫ�ж��Ļ�
			// ����Щ���봦���жϵ�����ֵӦ����2�����ƣ�����������
		} else {
			if (step == 1)
				for (int i = 0; i < size; i++) {
					for (int j = 0; j < size; j++)
						haschess1[i][j][0] = haschess[i][j][0];
				}
			if (step >= 2) {
				for (int i = 0; i < size; i++) {
					for (int j = 0; j < size; j++) {
						haschess2[i][j][0] = haschess1[i][j][0];
						haschess1[i][j][0] = haschess[i][j][0];
					}
				}
			}
			// ��ʽ����
			if (haschess[x][y][0] == 0) {
				StdDraw.setPenColor(color ? StdDraw.BLACK : StdDraw.WHITE);
				StdDraw.filledCircle(chesslocal[x][y][0], chesslocal[x][y][1], getPenRadius());// �ж��Ƿ������ӣ������������
				haschess[x][y][0] = color ? 1 : 2;
				color = !color;
				step++;
			}
			StdDraw.show();
		}
	}

//---------------------------��������-------------------------------//
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
			color = true;// ���������һ��ִ�о��ܾ�����һ�ε�������ɫ
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.filledCircle(chesslocal[i][j][0], chesslocal[i][j][1], getPenRadius());
			break;
		}
	}

	public static boolean getColor() {
		return color;
	}
}
