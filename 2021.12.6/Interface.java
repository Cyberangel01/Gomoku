import edu.princeton.cs.algs4.StdDraw;
/*
 * interface类已实现功能：
 * 	start界面（不完整）
 * 	play界面（不完整）
 * 
 * interface类待实现功能：
 * 	选项界面，从start界面接入，包括选择棋盘大小
 * 	选项界面，从start界面接入，选择对手，人或电脑
 * 	
 * 	2021.12.6
 */

public class Interface {
	public static void Start() {
		StdDraw.setCanvasSize(800, 800);
		StdDraw.setXscale(0, 1000);
		StdDraw.setYscale(0, 1000);
		// StdDraw.picture(x, y, filename, scaledWidth, scaledHeight);
		Button newGame = new Button(300, 120, 725, 775, "新游戏");
		Button.isPressed();
	}

	public static void PlayInterface() {
		ChessBoard board = new ChessBoard(19);
		board.play();

	}
}
