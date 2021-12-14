package view;

import edu.princeton.cs.algs4.StdDraw;

public class ViewMain {
	static int nowInterface = 0;// 这个变量用来标定当前的界面

	public static void main(String[] args) {
		StdDraw.setCanvasSize(800, 800);
		StdDraw.setXscale(0, 1000);
		StdDraw.setYscale(0, 1000);
		StartInterface.draw();

		while (true) {
			if (StdDraw.isMousePressed()) {
				double x = StdDraw.mouseX();
				double y = StdDraw.mouseY();
				switch (nowInterface) {
				case 0:
					StartInterface.hook(x, y);
					break;
				case 1:
					ModeInterface.hook(x, y);
					break;
				case 2:
					OptionInterface.hook(x, y);
					break;
				case 3:
					PlayInterface.hook(x, y);
					break;
				}
				StdDraw.pause(200);
			}
		}

	}

}
