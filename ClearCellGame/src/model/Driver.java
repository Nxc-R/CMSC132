package model;

import java.util.Random;

public class Driver {

	public static void main(String[] args) {
		Game game = new ClearCellGame(4, 5, new Random(1L), 1);
		//game.setBoardCell(2, 2, BoardCell.BLUE);
		for (int i = 0;i < 3;i++) {
			game.nextAnimationStep();
		}
		//System.out.println("Before");
		game.processCell(0, 2);
		game.processCell(0, 3);
		game.processCell(2, 0);
		System.out.println(getBoardStr(game));
		System.out.println(game.getScore());
		/*System.out.println("After");
		game.processCell(3, 4);
		game.processCell(3, 6);
		game.processCell(3, 0);
		game.processCell(3, 1);
		game.processCell(3, 9);
		System.out.println(getBoardStr(game));*/
		//System.out.println(game.getScore());
		/*Game ccGame = new ClearCellGame(10, 10, new Random(1L), 1);
		ccGame.setBoardWithColor(BoardCell.BLUE);
		ccGame.setRowWithColor(2, BoardCell.EMPTY);
		ccGame.setRowWithColor(3, BoardCell.EMPTY);
		ccGame.setRowWithColor(6, BoardCell.EMPTY);
		ccGame.setRowWithColor(9, BoardCell.EMPTY);
		System.out.println("Before");
		System.out.println(getBoardStr(ccGame));
		ccGame.processCell(1, 0);
		System.out.println("After");
		System.out.println(getBoardStr(ccGame));*/
		
	}
	private static String getBoardStr(Game game) {
		int maxRows = game.getMaxRows(), maxCols = game.getMaxCols();

		String answer = "Board(Rows: " + maxRows + ", Columns: " + maxCols + ")\n";
		for (int row = 0; row < maxRows; row++) {
			for (int col = 0; col < maxCols; col++) {
				answer += game.getBoardCell(row, col).getName();
			}
			answer += "\n";
		}

		return answer;
	}
}
