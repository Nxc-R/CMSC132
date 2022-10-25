package model;

import java.util.Random;
/**
 * This class extends GameModel and implements the logic of the clear cell game.
 * We define an empty cell as BoardCell.EMPTY. An empty row is defined as one
 * where every cell corresponds to BoardCell.EMPTY.
 * 
 * @author Department of Computer Science, UMCP
 */

public class ClearCellGame extends Game {

	private int strategy, score;
	private Random random;

	/**
	 * Defines a board with empty cells. It relies on the super class constructor to
	 * define the board. The random parameter is used for the generation of random
	 * cells. The strategy parameter defines which clearing cell strategy to use
	 * (for this project it will be 1). For fun, you can add your own strategy by
	 * using a value different that one.
	 * 
	 * @param maxRows
	 * @param maxCols
	 * @param random
	 * @param strategy
	 */
	public ClearCellGame(int maxRows, int maxCols, Random random, int strategy) {
		super(maxRows, maxCols);
		this.strategy = strategy;
		this.random = random;
		this.score = 0;
	}

	/**
	 * The game is over when the last board row (row with index board.length -1) is
	 * different from empty row.
	 */
	public boolean isGameOver() {
		for (int i = 0; i < board[board.length - 1].length; i++) {
			if (board[board.length - 1][i] != BoardCell.EMPTY) {
				return true;
			}
		}

		return false;
	}

	public int getScore() {
		return score;
	}

	/**
	 * This method will attempt to insert a row of random BoardCell objects if the
	 * last board row (row with index board.length -1) corresponds to the empty row;
	 * otherwise no operation will take place.
	 */
	public void nextAnimationStep() {
		if (isGameOver() == false) {
			if (isEmptyBoard(board[0])) {
				for (int i = 0;i < board[0].length;i++) {
					setBoardCell(0, i, BoardCell.getNonEmptyRandomBoardCell(random));
				}
			} else {
				BoardCell[][] temp = copyBoard(board);
				for (int i = 0;i < board.length - 1;i++) {
					board[i + 1] = temp[i];
				}
				for (int i = 0;i < board[0].length;i++) {
					setBoardCell(0, i, BoardCell.getNonEmptyRandomBoardCell(random));
				}
			}
		
		}
	}

	/**
	 * This method will turn to BoardCell.EMPTY the cell selected and any adjacent
	 * surrounding cells in the vertical, horizontal, and diagonal directions that
	 * have the same color. The clearing of adjacent cells will continue as long as
	 * cells have a color that corresponds to the selected cell. Notice that the
	 * clearing process does not clear every single cell that surrounds a cell
	 * selected (only those found in the vertical, horizontal or diagonal
	 * directions).
	 * 
	 * IMPORTANT: Clearing a cell adds one point to the game's score.<br />
	 * <br />
	 * 
	 * If after processing cells, any rows in the board are empty,those rows will
	 * collapse, moving non-empty rows upward. For example, if we have the following
	 * board (an * represents an empty cell):<br />
	 * <br />
	 * RRR<br />
	 * GGG<br />
	 * YYY<br />
	 * * * *<br/>
	 * <br />
	 * then processing each cell of the second row will generate the following
	 * board<br />
	 * <br />
	 * RRR<br />
	 * YYY<br />
	 * * * *<br/>
	 * * * *<br/>
	 * <br />
	 * IMPORTANT: If the game has ended no action will take place.
	 * 
	 * 
	 */
	public void processCell(int rowIndex, int colIndex) {
		if (isGameOver() == false) {
			if (rowIndex <= board.length - 1 && colIndex <= board[rowIndex].length - 1
					&& board[rowIndex][colIndex] != BoardCell.EMPTY) {
				if (strategy == 1) {
					int index = 0;
					clearCell(rowIndex, colIndex, "Down");
					clearCell(rowIndex, colIndex, "Right");
					clearCell(rowIndex, colIndex, "Left");
					clearCell(rowIndex, colIndex, "Up");
					clearCell(rowIndex, colIndex, "Up-Right");
					clearCell(rowIndex, colIndex, "Up-Left");
					clearCell(rowIndex, colIndex, "Down-Right");
					clearCell(rowIndex, colIndex, "Down-Left");
					setBoardCell(rowIndex, colIndex, BoardCell.EMPTY);
					score++;
					BoardCell[][] temp = new BoardCell[numOfNonEmptyRow(board)][board[0].length];
					for (int i = 0; i < board.length; i++) {
						if (!(isEmptyBoard(board[i]))) {
							temp[index] = board[i];
							index++;
						}
					}
					for (int i = 0; i < temp.length; i++) {
						board[i] = temp[i];
					}
					for (int i = temp.length;i < board.length;i++) {
						setRowWithColor(i, BoardCell.EMPTY);
					}
				}
			}
		}
	}
	private boolean isEmptyBoard(BoardCell[] boardcell) {
		for (int i = 0; i < boardcell.length; i++) {
			if (boardcell[i] != BoardCell.EMPTY) {
				return false;
			}
		}

		return true;
	}
	
	private BoardCell[][] copyBoard(BoardCell[][] board) {
		BoardCell[][] returnCopy = new BoardCell[board.length][board[0].length];
		for (int i = 0;i < returnCopy.length;i++) {
			for(int j = 0;j < returnCopy[i].length;j++) {
				returnCopy[i][j] = board[i][j];
			}
		}
		return returnCopy;
	}
	
	private void clearCell(int rowIndex, int colIndex, String direction) {
		BoardCell toClear = getBoardCell(rowIndex, colIndex);
		if (direction == "Down") {
			for (int i = 0;i < board.length;i++) {
				for (int j = 0;j < board[i].length;j++) {
					if (rowIndex < board.length - 1 && board[rowIndex + 1][colIndex] == toClear) {
						setBoardCell(rowIndex + 1, colIndex, BoardCell.EMPTY);
						rowIndex++;
						score++;
					}
				}
			}
		} else if (direction == "Right") {
			for (int i = 0;i < board.length;i++) {
				for (int j = 0;j < board[i].length;j++) {
					if (colIndex < board[i].length - 1 && board[rowIndex][colIndex + 1] == toClear) {
						setBoardCell(rowIndex, colIndex + 1, BoardCell.EMPTY);
						colIndex++;
						score++;
					}
				}
			}
		} else if (direction == "Left") {
			for (int i = 0;i < board.length;i++) {
				for (int j = 0;j < board[i].length;j++) {
					if (colIndex > 0 && board[rowIndex][colIndex - 1] == toClear) {
						setBoardCell(rowIndex, colIndex - 1, BoardCell.EMPTY);
						colIndex--;
						score++;
					}
				}
			}
		} else if (direction == "Up") {
			for (int i = 0;i < board.length;i++) {
				for (int j = 0;j < board[i].length;j++) {
					if (rowIndex > 0 && board[rowIndex - 1][colIndex] == toClear) {
						setBoardCell(rowIndex - 1, colIndex, BoardCell.EMPTY);
						rowIndex--;
						score++;
					}
				}
			}
		} else if (direction == "Up-Right") {
			for (int i = 0;i < board.length;i++) {
				for (int j = 0;j < board[i].length;j++) {
					if (rowIndex > 0 && colIndex < board[i].length - 1 && board[rowIndex - 1][colIndex + 1] == toClear) {
						setBoardCell(rowIndex - 1, colIndex + 1, BoardCell.EMPTY);
						rowIndex--;
						colIndex++;
						score++;
					}
				}
			}
		} else if (direction == "Up-Left") {
			for (int i = 0;i < board.length;i++) {
				for (int j = 0;j < board[i].length;j++) {
					if (rowIndex > 0 && colIndex > 0 && board[rowIndex - 1][colIndex - 1] == toClear) {
						setBoardCell(rowIndex - 1, colIndex - 1, BoardCell.EMPTY);
						rowIndex--;
						colIndex--;
						score++;
					}
				}
			}
		} else if (direction == "Down-Right") {
			for (int i = 0;i < board.length;i++) {
				for (int j = 0;j < board[i].length;j++) {
					if (rowIndex < board.length - 1 && colIndex < board[i].length - 1 && board[rowIndex + 1][colIndex + 1] == toClear) {
						setBoardCell(rowIndex + 1, colIndex + 1, BoardCell.EMPTY);
						rowIndex++;
						colIndex++;
						score++;
					}
				}
			} 
		} else if (direction == "Down-Left") {
			for (int i = 0;i < board.length;i++) {
				for (int j = 0;j < board[i].length;j++) {
					if (rowIndex < board.length - 1 && colIndex > 0 && board[rowIndex + 1][colIndex - 1] == toClear) {
						setBoardCell(rowIndex + 1, colIndex - 1, BoardCell.EMPTY);
						rowIndex++;
						colIndex--;
						score++;
					}
				}
			} 
		}
		
	}
	private int numOfNonEmptyRow(BoardCell[][] boards) {
		int returnInt = 0;
		for (int i = 0;i < boards.length;i++) {
			if (!(isEmptyBoard(boards[i]))) {
				returnInt++;
			}
		}
		return returnInt;
	}
}