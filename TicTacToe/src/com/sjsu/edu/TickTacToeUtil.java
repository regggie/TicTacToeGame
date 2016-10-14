package com.sjsu.edu;

/**
 * Utility class contains TicTacToe logic
 * 
 * @author group22
 * 
 */
public final class TickTacToeUtil {

	// Enter playerSymbol at the position in the matrix as defined

	public static boolean placeSymbol(int player, int row, int col,
			char[][] matrix) {

		// Ensure that the provided values lie within the matrix
		if ((player != 0) && (player != 1)) {

			return false;
		}

		if ((row < 0) || (col < 0) || (row >= 3) || (row >= 3)) {

			System.err
					.println("Given row and column value is out of bounds!!!");
			System.err
					.println("Valid values for row and column of the board are 0,1,2");
			return false;
		}

		if (player == 1) { // Player = USER
			if (!checkIfPositionIsOccupied(row, col, matrix)) {

				matrix[row][col] = 'X';
				return true;
			}
		} else { // Player = COMPUTER
			if (!checkIfPositionIsOccupied(row, col, matrix)) {
				matrix[row][col] = 'O';
				return true;
			}
		}
		return false;

	}

	// Check whether the matrix is fully occupied.

	public static boolean isMatrixFull(char[][] matrix) {

		boolean isFilled;
		for (int i = 0; i < 3; i++) {

			for (int j = 0; j < 3; j++) {

				isFilled = checkIfPositionIsOccupied(i, j, matrix);
				if (isFilled == false) {

					return false;
				}
			}

		}
		return true;
	}

	// Check if given position is occupied

	public static boolean checkIfPositionIsOccupied(int row, int col,
			char[][] matrix) {

		// Return true if position has either 'X' or 'O',
		// else return false

		if ((matrix[row][col] == 'X') || (matrix[row][col] == 'O')) {

			// "Location is occupied
			return true;
		} else {

			// "Location is empty"
			return false;
		}
	}

	// Print matrix

	public static void printMatrix(final char[][] matrix) {

		System.out.println("---------------------");
		for (int i = 0; i < 3; i++) {

			for (int j = 0; j < 3; j++) {

				System.out.print(matrix[i][j] + "\t|\t");
			}
			System.out.print("\n");
			System.out.println("---------------------");
		}
	}

	// Check for all rows to be all 'O' or 'X'

	private static boolean evaluateRow(int player, char[][] matrix) {

		char symbol;
		char positionValue;
		int count = 0;

		if (player == 1) { // Player = USER

			symbol = 'X';
		} else { // Player = COMPUTER

			symbol = 'O';
		}

		// Iterate and check win condition for any of the rows
		for (int i = 0; i < 3; i++) {

			for (int j = 0; j < 3; j++) {

				// positionValue = readPosition(i,j);
				positionValue = matrix[i][j];
				if (positionValue == symbol) {
					count++;
				}
			}
			if (count == 3) {

				return true; // Row won!
			}
			count = 0;
		}
		return false;
	}

	// Check for all columns to be all 'O' or 'X'

	private static boolean evaluateCol(int player, char[][] matrix) {

		char symbol;
		char positionValue;
		int count = 0;

		if (player == 1) { // Player = USER

			symbol = 'X';
		} else { // Player = COMPUTER

			symbol = 'O';
		}

		// Iterate and check win condition for any of the columns
		for (int j = 0; j < 3; j++) {

			for (int i = 0; i < 3; i++) {

				// positionValue = readPosition(i,j);
				positionValue = matrix[i][j];
				if (positionValue == symbol) {
					count++;
				}
			}
			if (count == 3) {

				return true; // Columnn won!
			}
			count = 0;
		}
		return false;

	}

	// Check for diagonals to be all 'O' or 'X'

	private static boolean evaluateDiagonals(int player, char[][] matrix) {

		// diagonal positions 1: [0,0], [1,1], [2,2]
		// diagonal positions 2: [0,2], [1,1], [2,0]
		char symbol;
		char positionValue;
		int diagonal_1_count = 0;
		int diagonal_2_count = 0;

		if (player == 1) { // Player = USER

			symbol = 'X';
		} else { // Player = COMPUTER

			symbol = 'O';
		}

		// Iterate and check win condition for any of the diagonal positions
		for (int i = 0; i < 3; i++) {

			// positionValue = readPosition(i,i);
			positionValue = matrix[i][i];
			if (positionValue == symbol) {
				diagonal_1_count++;
			}
			// positionValue = readPosition(i,(3-i-1));
			positionValue = matrix[i][3 - i - 1];
			if (positionValue == symbol) {
				diagonal_2_count++;
			}

			if (diagonal_1_count == 3 || diagonal_2_count == 3) {

				return true; // Diagonals won!
			}
		}

		return false;

	}

	// Determine winner

	public static boolean determineResult(int player, char[][] matrix) {

		boolean rowResult, colResult, diagonalResult;
		rowResult = evaluateRow(player, matrix);
		colResult = evaluateCol(player, matrix);
		diagonalResult = evaluateDiagonals(player, matrix);
		if (rowResult == true || colResult == true || diagonalResult == true) {
			return true;

		}

		return false;
	}

}
