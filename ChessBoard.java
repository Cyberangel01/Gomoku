import edu.princeton.cs.algs4.StdDraw;

public class ChessBoard {
	/*
	 * 这个棋盘大小固定，x范围（140，860），y范围（150，870）
	 * 
	 */
	public static void main(String[] args) {
		StdDraw.setCanvasSize(800, 800);
		StdDraw.setXscale(0, 1000);
		StdDraw.setYscale(0, 1000);
		// StdDraw.enableDoubleBuffering();

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
		// StdDraw.clear();
		// StdDraw.show();
		// StdDraw.disableDoubleBuffering();
		boolean color = true;
		while (true) {
			StdDraw.setPenColor(color ? StdDraw.BLACK : StdDraw.RED);
			if (StdDraw.isMousePressed()) {
				int x = (int) Math.min(Math.max((Math.round((StdDraw.mouseX() - 140) / 45) - 1), 0),
						Math.min((Math.round((StdDraw.mouseX() - 140) / 45) - 1), 14));// 计算出距离鼠标最近的交点的序号，用最大最小的方法保障不会越界
				int y = (int) Math.min(Math.max((Math.round((StdDraw.mouseY() - 150) / 45) - 1), 0),
						Math.min((Math.round((StdDraw.mouseY() - 150) / 45) - 1), 14));
				x = Math.max(x, 0);
				y = Math.max(y, 0);// 这里单独写是因为前面太长了，这部分应该可以单独写一个方法
				StdDraw.filledCircle(chesslocal[x][y][0], chesslocal[x][y][1], 20);
				StdDraw.show();
				StdDraw.pause(300);
				color = !color;
			}

		}
	}
}
