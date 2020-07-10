package sudoku;

public class Solver {
	public static void main(String[] args) {
		Solver sol = new Solver();
		int[][] arr = new int[][] {
			{9,0,0,8,3,0,1,5,7},
			{5,0,3,1,0,6,2,8,0},
			{1,0,0,7,4,0,0,9,0},
			{0,0,0,0,5,0,8,3,0},
			{3,0,1,0,0,4,6,7,2},
			{2,0,0,0,1,3,0,0,9},
			{0,0,2,0,7,0,0,1,0},
			{0,0,0,0,0,0,0,6,0},
			{0,3,4,0,6,0,9,2,0}
		};
		

 		sol.solveSudoku(arr);
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (j < arr.length - 1) {
					System.out.print(arr[i][j] + ",");
				} else {
					System.out.println(arr[i][j]);
				}
			}
		}
	}
	
	public void solveSudoku(int[][] board) {
		helper(board, 0, 0);
	}
	
	private boolean helper(int[][] board, int row, int col) {
		if (row == board.length) {
			return true;
		}
		
		for (int i = row; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == 0) {
					for (int k = 1; k <= 9; k++) {
						if (isValid(board, i, j, k)) {
							board[i][j] = k;
							if (j < board[i].length - 1) {
								if (helper(board, i, j + 1)) {
									return true;
								}
							} else if (j == board[i].length - 1) {
								if (helper(board, i + 1, 0)) {
									return true;
								}
							}
							board[i][j] = 0;
						} 
					}
					return false;
				}
				
			}
		}
		
		return true;
	}
	
	private boolean isValid(int[][] board, int row, int col, int target) {
		int blockRow = (row / 3) * 3;
		int blockCol = (col / 3) * 3;
		for (int i = 0; i < 9; i++) {
			if (i != row && board[i][col] == target) {
				return false;
			}
			if (i != col && board[row][i] == target) {
				return false;
			}
			if (board[blockRow + i / 3][blockCol + i % 3] == target) {
				return false;
			}
		}
		return true;
	}

}
