package view;

import com.sustc.stdlib.StdDraw;

public class ViewMain {
	static int nowInterface = 0;// 这个变量用来标定当前的界面
	static int playModel = 0;// 这个变量用来标定游戏模式
	static Thread black;
	static Thread white;

	public static void main(String[] args) {
		black = new Thread(new BlackTimer());
		white = new Thread(new WhiteTimer());
		StdDraw.setCanvasSize(800, 800);
		StdDraw.setXscale(0, 1000);
		StdDraw.setYscale(0, 1000);
		StartInterface.draw();

		while (true) {// 把鼠标监听独立出来放到主调中，解决嵌套循环的问题
			if (StdDraw.isMousePressed()) {
				double x = StdDraw.mouseX();
				double y = StdDraw.mouseY();
				switch (nowInterface) {// 其实可以用实例化再map的处理，但是没必要
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
					if (playModel == 0) {// 人工智障模式下不计时
						try {
							black.start();
							white.start();
						} catch (Exception e) {
						}
					}
					break;
				case 4:
					WinnerInterface.hook(x, y);
				}
				StdDraw.pause(200);
			}
		}

	}

}
