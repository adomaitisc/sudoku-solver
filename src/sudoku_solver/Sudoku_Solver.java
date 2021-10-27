package sudoku_solver;

public class Sudoku_Solver {

	public static void main(String[] args) {
		int[][] sudoku_board = {
				{0, 7, 0, 0, 4, 2, 0, 0, 0},
				{0, 0, 0, 0, 0, 8, 6, 1, 0},
				{3, 9, 0, 0, 0, 0, 0, 0, 7},
				{0, 0, 0, 0, 0, 4, 0, 0, 9},
				{0, 0, 3, 0, 0, 0, 7, 0, 0},
				{5, 0, 0, 1, 0, 0, 0, 0, 0},
				{8, 0, 0, 0, 0, 0, 0, 7, 6},
				{0, 5, 4, 8, 0, 0, 0, 0, 0},
				{0, 0, 0, 6, 1, 0, 0, 5, 0},
		};
		printSudokuBoard(sudoku_board, "Unsolved Board");
		if ( !solve(sudoku_board)) {
			System.out.println("Unable to solve the sudoku board.");
			System.exit(0);
		}
		printSudokuBoard(sudoku_board, "Solved Board");
	}
	
	public static void printSudokuBoard(int[][] sudoku_board, String title) {
		System.out.println("      " + title);
		for ( int row = 0; row < sudoku_board.length; row++) {
			if (row == 3 || row == 6) {
				System.out.println();
			}
			System.out.print(" |");
			for ( int col = 0; col < sudoku_board.length; col++) {
				if ( col == 3 || col == 6) {
					System.out.print(" |");
				}
				System.out.print(sudoku_board[row][col] + "|");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static boolean solve(int[][] sudoku_board) {
		for ( int row_idx = 0; row_idx < sudoku_board.length; row_idx ++) {
			for ( int col_idx = 0; col_idx < sudoku_board.length; col_idx++ ) {
				if ( sudoku_board[row_idx][col_idx] == 0) {
					for ( int num = 1; num <= sudoku_board.length; num++ ) {
						if (verify(sudoku_board, row_idx, col_idx, num)) {
							sudoku_board[row_idx][col_idx] = num;
							if (solve(sudoku_board)) {
								return true;
							} else {
								sudoku_board[row_idx][col_idx] = 0;
							}
						}
					}
					return false;
				}
			}
		}
		return true;
	}
	
	public static boolean verify(int[][] sudoku_board, int row_idx, int col_idx, int num) {
		int grid_row = row_idx/3;
		int grid_col = col_idx/3;
		//Verify Row
		for ( int i = 0; i < sudoku_board.length; i++) {
			if ( num == sudoku_board[row_idx][i]) {
				return false;
			}
		}
		//Verify Column
		for ( int j = 0; j < sudoku_board.length; j++) {
			if ( num == sudoku_board[j][col_idx]) {
				return false;
			}
		}
		//Verify Grid
		for ( int row_pos = 0; row_pos < 3; row_pos++ ) {
			for ( int col_pos = 0; col_pos < 3; col_pos++) {
				if ( num == sudoku_board[row_pos+(grid_row*3)][col_pos+(grid_col*3)]) {
					return false;
				}
			}
		}
		return true;
	}
}
