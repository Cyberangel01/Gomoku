import edu.princeton.cs.algs4.StdDraw;
/*
 * interface����ʵ�ֹ��ܣ�
 * 	start���棨��������
 * 	play���棨��������
 * 
 * interface���ʵ�ֹ��ܣ�
 * 	ѡ����棬��start������룬����ѡ�����̴�С
 * 	ѡ����棬��start������룬ѡ����֣��˻����
 * 	
 * 	2021.12.6
 */

public class Interface {
	public static void Start() {
		StdDraw.setCanvasSize(800, 800);
		StdDraw.setXscale(0, 1000);
		StdDraw.setYscale(0, 1000);
		// StdDraw.picture(x, y, filename, scaledWidth, scaledHeight);
		Button newGame = new Button(300, 120, 725, 775, "����Ϸ");
		Button.isPressed();
	}

	public static void PlayInterface() {
		ChessBoard board = new ChessBoard(19);
		board.play();

	}
}
