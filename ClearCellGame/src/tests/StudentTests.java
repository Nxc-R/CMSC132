package tests;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import model.BoardCell;
import model.ClearCellGame;
import model.Game;

/* The following directive executes tests in sorted order */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class StudentTests {
	@Test
	public void consTest() {
		Game game = new ClearCellGame(4, 5, new Random(1L), 1);
		String result = getBoardStr(game);
		String expected = "Board(Rows: 4, Columns: 5)\n"
				+ ".....\n"
				+ ".....\n"
				+ ".....\n"
				+ ".....\n"
				+ "";
		assertTrue(result.equals(expected));
	}
	@Test
	public void getAndSetTest() {
		Game game = new ClearCellGame(4, 5, new Random(1L), 1);
		int maxRows = game.getMaxRows(), maxCols = game.getMaxCols();
		game.setBoardCell(0, 2, BoardCell.RED);
		String cellResult = game.getBoardCell(0, 2).getName();
		boolean rowCheck = true, colCheck = true, rowCheck1 = false, colCheck1 = false, boardCheck = true, boardCheck1 = false;;
		BoardCell[] checkRow = new BoardCell[maxCols];
		game.setRowWithColor(2, BoardCell.BLUE);
		for (int i = 0;i < maxCols;i++) {
			checkRow[i] = game.getBoardCell(2, i);
		}
		for (int i = 0;i < maxCols;i++) {
			if (!(checkRow[i].getName().equals("B"))) {
				rowCheck = false;
			} 
		}
		if (rowCheck) {
			rowCheck1 = true;
		}
		BoardCell[][] checkCol = new BoardCell[maxRows][1];
		game.setColWithColor(3, BoardCell.GREEN);
		for (int i = 0;i < checkCol.length;i++) {
			for(int j = 0;j < checkCol[i].length;j++) {
				checkCol[i][j] = game.getBoardCell(i, 3);
			}
		}
		for (int i = 0;i < checkCol.length;i++) {
			for(int j = 0;j < checkCol[i].length;j++) {
				if (!(checkCol[i][j].getName().equals("G"))) {
					colCheck  = false;
				} 
			}
		}
		if (colCheck) {
			colCheck1 = true;
		}
		BoardCell[][] checkBoard = new BoardCell[maxRows][maxCols];
		game.setBoardWithColor(BoardCell.YELLOW);
		for (int i = 0;i < checkCol.length;i++) {
			for(int j = 0;j < checkCol[i].length;j++) {
				checkBoard[i][j] = game.getBoardCell(i, j);
			}
		}
		for (int i = 0;i < checkCol.length;i++) {
			for(int j = 0;j < checkCol[i].length;j++) {
				if (!(checkBoard[i][j].getName().equals("Y"))) {
					boardCheck  = false;
				} 
			}
		}
		if (boardCheck) {
			boardCheck1 = true;
		}
		assertTrue(maxRows == 4);
		assertTrue(maxCols == 5);
		assertTrue(cellResult.equals("R"));
		assertTrue(rowCheck1);
		assertTrue(colCheck1);
		assertTrue(boardCheck1);
		
	}
	@Test
	public void animationStepTest() {
		Game game = new ClearCellGame(4, 5, new Random(1L), 1);
		game.nextAnimationStep();
		game.nextAnimationStep();
		String result = getBoardStr(game);
		String expected = "Board(Rows: 4, Columns: 5)\n"
				+ "RRGYY\n"
				+ "RYBYR\n"
				+ ".....\n"
				+ ".....\n";
		assertTrue(result.equals(expected));
	}
	@Test
	public void processAndgetScoreTest() {
		Game game = new ClearCellGame(7, 10, new Random(1L), 1);
		for (int i = 0;i < 6;i++) {
			game.nextAnimationStep();
		}
		game.processCell(3, 4);
		String result = getBoardStr(game);
		String expected = "Board(Rows: 7, Columns: 10)\n"
				+ "BYGRGRY.RR\n"
				+ "RRBBBB.YRB\n"
				+ "RRRGY.RRRR\n"
				+ "GB....YYBB\n"
				+ "RYBYB.BBGR\n"
				+ "RYBYRR.GYY\n"
				+ "..........\n";
		assertTrue(result.equals(expected));
		game.processCell(3, 6);
		game.processCell(3, 0);
		game.processCell(3, 1);
		game.processCell(3, 9);
		result = getBoardStr(game);
		expected = "Board(Rows: 7, Columns: 10)\n"
				+ "BYGRGRY.RR\n"
				+ "RRBBBB.YRB\n"
				+ "RRRGY.RRRR\n"
				+ "RY.YB.BBGR\n"
				+ "RYBYRR.GYY\n"
				+ "..........\n"
				+ "..........\n";
		int resultScore = game.getScore();
		int expectedScore = 16;
		assertTrue(resultScore == expectedScore);
		assertTrue(result.equals(expected));
	}
	@Test
	public void gameOverTest() {
		Game game = new ClearCellGame(4, 5, new Random(1L), 1);
		game.nextAnimationStep();
		game.nextAnimationStep();
		boolean gameResult = game.isGameOver();
		boolean expectedResult = false;
		assertTrue(gameResult == expectedResult);
		for (int i = 0;i < 2;i++) {
			game.nextAnimationStep();
		}
		gameResult = game.isGameOver();
		expectedResult = true;
		assertTrue(gameResult == expectedResult);
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
