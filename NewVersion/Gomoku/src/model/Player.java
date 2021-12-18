package model;

/**
 * ÕÊº“¿‡
 */
public class Player {

	private String name;

	private ChessColor chessColor;

	public Player() {
	}

	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ChessColor getChessColor() {
		return chessColor;
	}

	public void setChessColor(ChessColor chessColor) {
		this.chessColor = chessColor;
	}

	@Override
	public String toString() {
		return "Player{" + "name='" + name + '\'' + ", chessColor=" + chessColor + '}';
	}
}
