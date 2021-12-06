
/*
 * ��ʵ�ֹ��ܣ�
 * 	�������̣��ɱ��С��
 * 	���ӣ������Ƿ������ӣ�
 * 	����״̬���棨1�Σ�
 * 	���������뷽������װ
 * 	���뱳��
 * 
 * ��ʵ�ֹ��ܣ�
 * 	���״̬���棨ǰ���Σ�
 * 
 * 
 *	2021.12.6
 */
import edu.princeton.cs.algs4.StdDraw;

public class ChessBoard {
	/*
	 * ������̴�С�̶���x��Χ��140��860����y��Χ��150��870��
	 * 
	 */
	static int size;
	double[][][] chesslocal;
	static int grid;

	public ChessBoard(int size) {
		ChessBoard.size = size;
		chesslocal = new double[size][size][2];
		grid = (720 / (size + 1));
		// ����������̵�ʱ��ͻ�������
		this.DrawChessBoard();
	}

	public void DrawChessBoard() {
		StdDraw.clear();
		StdDraw.enableDoubleBuffering();

		int centerX = 140 + (grid * (size + 1) / 2);
		int centerY = 150 + (grid * (size + 1) / 2);

		StdDraw.picture(centerX, centerY, "test.jpg", 730, 730);// �������̱���
		StdDraw.filledCircle(centerX, centerY, 5);// �������ĵ�

		// ������߿�
		StdDraw.line(135, 145, 865, 145);// ����
		StdDraw.line(135, 875, 865, 875);
		StdDraw.line(135, 145, 135, 875);// ����
		StdDraw.line(865, 145, 865, 875);
		// �����ڱ߿�
		StdDraw.line(140, 150, 860, 150);// ����
		StdDraw.line(140, 870, 860, 870);
		StdDraw.line(140, 150, 140, 870);// ����
		StdDraw.line(860, 150, 860, 870);

		// �����߸�
		for (int i = 0; i < size; i++) {
			StdDraw.line(140 + (grid * (i + 1)), 150, 140 + grid * (i + 1), 870);// ���λ�������
			StdDraw.line(140, 150 + (grid * (i + 1)), 860, 150 + grid * (i + 1));// ���λ��ƺ���
			for (int j = 0; j < size; j++) {
				chesslocal[i][j][0] = 140 + ((720 / (size + 1)) * (i + 1));// ��ȡÿ�������x����
				chesslocal[j][i][1] = 150 + ((720 / (size + 1)) * (i + 1));// ��ȡÿ�������y����
			}

		}
		StdDraw.show();
		StdDraw.disableDoubleBuffering();
	}

//-----------------------------------------------------------------------------------------------//���Ӵ���
	public void play() {
		StdDraw.pause(300);
		int[][][] haschess = new int[size][size][1];// 1������ӣ�2�������
		boolean color = true;// true�������
		while (true) {
			StdDraw.setPenColor(color ? StdDraw.BLACK : StdDraw.WHITE);
			if (StdDraw.isMousePressed()) {
				// ������
				int x = ChessBoard.CrossX();
				int y = ChessBoard.CrossY();
				if (haschess[x][y][0] == 0) {
					StdDraw.filledCircle(chesslocal[x][y][0], chesslocal[x][y][1], getPenRadius());// �ж��Ƿ������ӣ������������
					haschess[x][y][0] = color ? 1 : 2;
					color = !color;
				}
				StdDraw.show();
				StdDraw.pause(300);
			}

		}
	}

//���¾�Ϊ��������
	private static int CrossX() {
		int x = (int) Math.min(Math.max((Math.round((StdDraw.mouseX() - 140) / grid) - 1), 0),
				Math.min((Math.round((StdDraw.mouseX() - 140) / grid) - 1), size - 1));
		x = Math.max(x, 0);// ����������������Ľ������ţ��������С�ķ������ϲ���Խ��
		return x;

	}

	private static int CrossY() {
		int y = (int) Math.min(Math.max((Math.round((StdDraw.mouseY() - 150) / grid) - 1), 0),
				Math.min((Math.round((StdDraw.mouseY() - 150) / grid) - 1), size - 1));
		y = Math.max(y, 0);
		return y;
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

}
