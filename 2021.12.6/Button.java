import edu.princeton.cs.algs4.StdDraw;
/*
 * Button����ʵ�ֹ��ܣ�
 * 	��ת��������д������interface��ʵ�֣�
 * 
 * Button���������⣺
 * 	Button�е�����̫С����Ҫ����
 * 	Ŀǰ��û�й������࣬function������Ҫ��д
 * 	�������������Ϸ��������Ϸ�����¿�ʼ��ѡ��˳�������ͣ�����ص�������
 * 
 * 2021.12.6
 */

public class Button {
	static double ScaleX;
	static double ScaleY;
	static double centerX;
	static double centerY;
	static String text;

	public Button(double centerX, double centerY, String te) {
		ScaleX = 120;
		ScaleY = 240;
		text = te;
		Button.DrawButton();
	}

	public Button(double ScaleX, double ScaleY, double centerX, double centerY, String te) {
		Button.ScaleX = ScaleX;
		Button.ScaleY = ScaleY;
		Button.centerX = centerX;
		Button.centerY = centerY;
		text = te;
		Button.DrawButton();
	}

	public static void DrawButton() {
		StdDraw.picture(centerX, centerY, "Button.jpg", ScaleX, ScaleY);// Button.jpgֻ�ǲ���Ʒ������ʹ�ô���
		StdDraw.text(centerX, centerY, text);

	}

	public static void isPressed() {// �ж��Ƿ񱻵��
		double[] localX = { centerX - ScaleX / 2, centerX + ScaleX / 2 };
		double[] localY = { centerY - ScaleY / 2, centerY + ScaleY / 2 };
		while (true) {
			if (StdDraw.isMousePressed()) {
				double[] mouse = { StdDraw.mouseX(), StdDraw.mouseY() };
				if (include(mouse, localX, localY)) {
					function();
				}
			}
		}
	}

	private static void function() {
		// test
		Interface.PlayInterface();
		// test
		// ��������������Լ���д
	}

//�����������ж��Ƿ������Button��
	private static boolean include(double[] x, double[] localX, double[] localY) {
		return x[0] >= localX[0] && x[0] <= localX[1] && x[1] >= localY[0] && x[1] <= localY[1];
	}
}
