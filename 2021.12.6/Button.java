import edu.princeton.cs.algs4.StdDraw;
/*
 * Button类已实现功能：
 * 	跳转（子类重写，基于interface类实现）
 * 
 * Button类待解决问题：
 * 	Button中的文字太小，需要调整
 * 	目前还没有构造子类，function功能需要重写
 * 	子类包括：新游戏，继续游戏，重新开始，选项，退出，（暂停），回到主界面
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
		StdDraw.picture(centerX, centerY, "Button.jpg", ScaleX, ScaleY);// Button.jpg只是测试品，具体使用待定
		StdDraw.text(centerX, centerY, text);

	}

	public static void isPressed() {// 判断是否被点击
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
		// 这个方法让子类自己重写
	}

//辅助函数，判断是否包含在Button中
	private static boolean include(double[] x, double[] localX, double[] localY) {
		return x[0] >= localX[0] && x[0] <= localX[1] && x[1] >= localY[0] && x[1] <= localY[1];
	}
}
