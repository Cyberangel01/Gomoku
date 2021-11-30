import edu.princeton.cs.algs4.StdDraw;

public class ChessBoard {
	/*
	 * ������̴�С�̶���x��Χ��140��860����y��Χ��150��870��
	 * 
	 */
	public static void main(String[] args) {
		StdDraw.setCanvasSize(800, 800);
		StdDraw.setXscale(0, 1000);
		StdDraw.setYscale(0, 1000);
		// StdDraw.enableDoubleBuffering();

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
		int size = 15;// �ɱ���
		double[][][] chesslocal = new double[size][size][2];
		for (int i = 0; i < size; i++) {
			StdDraw.line(140 + ((720 / (size + 1)) * (i + 1)), 150, 140 + ((720 / (size + 1)) * (i + 1)), 870);// ���λ�������
			StdDraw.line(140, 150 + ((720 / (size + 1)) * (i + 1)), 860, 150 + ((720 / (size + 1)) * (i + 1)));// ���λ��ƺ���
			for (int j = 0; j < size; j++) {
				chesslocal[j][i][0] = 140 + ((720 / (size + 1)) * (i + 1));// ��ȡÿ�������x����
				chesslocal[i][j][1] = 150 + ((720 / (size + 1)) * (i + 1));// ��ȡÿ�������y����
			}

		} // �������ĵ�
		StdDraw.filledCircle(140 + ((720 / (size + 1)) * 8), 150 + ((720 / (size + 1)) * 8), 5);
		StdDraw.show();
		// StdDraw.clear();
		// StdDraw.show();
		// StdDraw.disableDoubleBuffering();
		boolean color = true;
		while (true) {
			StdDraw.setPenColor(color ? StdDraw.BLACK : StdDraw.RED);
			if (StdDraw.isMousePressed()) {
				int x = (int) (Math.round((StdDraw.mouseX() - 140) / 45));
				int y = (int) (Math.round((StdDraw.mouseY() - 150) / 45));
				StdDraw.filledCircle(chesslocal[y][x][0], chesslocal[y][x][1], 20);
				System.out.println(x + " " + y);
				System.out.println(StdDraw.mouseX() + " " + StdDraw.mouseY());
				StdDraw.show();
				StdDraw.pause(150);
				color = !color;
			}

		}
	}
}
