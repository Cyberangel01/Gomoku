package view;

import java.awt.Font;

import com.sustc.stdlib.StdDraw;

import model.ChessColor;

public class Timer implements Runnable {
	@Override
	public void run() {
	}

}

class BlackTimer extends Timer {
	private int totaltime = 1800;
	private int nowtime = 20;
	ChessColor nowColor;

	@Override
	public void run() {
		while (true) {
			try {
				nowColor = ChessBoard.color ? ChessColor.BLACK : ChessColor.WHITE;
				if (nowColor == ChessColor.BLACK) {
					StdDraw.setPenColor();
					double[] x = { 395, 450, 450, 395 };
					double[] y = { 30, 30, 90, 90 };
					StdDraw.filledPolygon(x, y);
					StdDraw.setPenColor(StdDraw.WHITE);
					StdDraw.setFont(new Font("Arival", Font.BOLD, 20));
					StdDraw.textRight(450, 45, nowtime + "");
					StdDraw.textRight(450, 75, totaltime + "");
					if (nowtime == 0 && totaltime == 1800) {
						ChessBoard.color = !ChessBoard.color;
						WinnerInterface.draw();
					}
					totaltime--;
					nowtime--;
				} else {
					nowtime = 20;
				}
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				break;
			}
		}
	}
}

class WhiteTimer extends Timer {
	private int totaltime = 1800;
	private int nowtime = 20;
	ChessColor nowColor;

	@Override
	public void run() {
		while (true) {
			try {
				nowColor = ChessBoard.color ? ChessColor.BLACK : ChessColor.WHITE;
				if (nowColor == ChessColor.WHITE) {
					StdDraw.setPenColor(StdDraw.WHITE);
					double[] x = { 550, 605, 605, 550 };
					double[] y = { 30, 30, 90, 90 };
					StdDraw.filledPolygon(x, y);
					StdDraw.setPenColor();
					StdDraw.setFont(new Font("Arival", Font.BOLD, 20));
					StdDraw.textLeft(550, 45, nowtime + "");
					StdDraw.textLeft(550, 75, totaltime + "");
					if (nowtime == 0 && totaltime == 1800) {
						ChessBoard.color = !ChessBoard.color;
						WinnerInterface.draw();
					}
					totaltime--;
					nowtime--;
				} else
					nowtime = 20;
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				break;
			}
		}
	}
}
