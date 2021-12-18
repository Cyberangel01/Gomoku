package view;

import com.sustc.stdlib.StdDraw;

public class ViewMain {
	static int nowInterface = 0;// ������������궨��ǰ�Ľ���
	static int playModel = 0;// ������������궨��Ϸģʽ
	static Thread black;
	static Thread white;

	public static void main(String[] args) {
		black = new Thread(new BlackTimer());
		white = new Thread(new WhiteTimer());
		StdDraw.setCanvasSize(800, 800);
		StdDraw.setXscale(0, 1000);
		StdDraw.setYscale(0, 1000);
		StartInterface.draw();

		while (true) {// �����������������ŵ������У����Ƕ��ѭ��������
			if (StdDraw.isMousePressed()) {
				double x = StdDraw.mouseX();
				double y = StdDraw.mouseY();
				switch (nowInterface) {// ��ʵ������ʵ������map�Ĵ�������û��Ҫ
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
					if (playModel == 0) {// �˹�����ģʽ�²���ʱ
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
