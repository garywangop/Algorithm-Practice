package dp;

import solution.TreeNode;

public class DPIII {
	public static void main(String args[]) {
		DPIII test = new DPIII();
		int[][] matrix = {{1,1,1,0,1},{1,0,1,1,1},{1,1,1,1,1},{1,0,1,1,0},{0,0,1,1,0}};
		String[] str = {"ab", "abc"};
		System.out.print(str.length);
	}
	
	// Longest Consecutive 1s
	public int longest(int[] array) {
		int count = 0;
		int res = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == 1) {
				if (i == 0 || array[i - 1] == 0) {
					count = 1;
				} else {
					count++;
				}
				res = Math.max(res, count);
			}
		}
		return res;
	}
	
	// Longest Cross Of 1s
	public int largest(int[][] matrix) {
	    // Assmuptions: matrix is not null, has size of N * N
	    // where N >= 0 and M >= 0
	    int N = matrix.length;
	    if (N == 0) {
	      return 0;
	    }
	    int M = matrix[0].length;
	    if (M == 0) {
	      return 0;
	    }
	    // leftup records the longest possible length for left and up
	    // arms ending at each cells in the matrix.
	    int[][] leftUp = leftUp(matrix, N, M);
	    // rightdown records the longest possible length for right and down
	    // arms ending at each cells in the matrix.
	    int[][] rightDown = rightDown(matrix, N, M);
	    return merge(leftUp, rightDown, N, M);
	  }

	  // Merge leftup and rightdown matrix into leftup,
	  // the value of each cell is the min value of the corresponding cells
	  // in the two matrix, also it returns the max value among all the cells 
	  // in the merged matrix.
	  private int merge(int[][] leftUp, int[][] rightDown, int N, int M) {
	    int result = 0;
	    for (int i = 0; i < N; i++) {
	      for (int j = 0; j < M; j++) {
	        leftUp[i][j] = Math.min(leftUp[i][j], rightDown[i][j]);
	        result = Math.max(result, leftUp[i][j]);
	      }
	    }
	    return result;
	  }

	  // Calculate the max possible length of left and up arms
	  // for each of the cells in the matrix.
	  private int[][] leftUp(int[][] matrix, int N, int M) {
	    int[][] left = new int[N][M];
	    int[][] up = new int[N][M];
	    for (int i = 0; i < N; i++) {
	      for (int j = 0; j < M; j++) {
	        if (matrix[i][j] == 1) {
	          if (i == 0 && j == 0) {
	            up[i][j] = 1;
	            left[i][j] = 1;
	          } else if (i == 0) {
	            up[i][j] = 1;
	            left[i][j] = left[i][j - 1] + 1;
	          } else if (j == 0) {
	            up[i][j] = up[i - 1][j] + 1;
	            left[i][j] = 1;
	          } else {
	            up[i][j] = up[i - 1][j] + 1;
	            left[i][j] = left[i][j - 1] + 1;
	          }
	        }
	      }
	    }
	    // Merge left and up, return the merged matrix.
	    merge(left, up, N, M);
	    return left;
	  }

	// Calculate the max possible length of right and down arms
	// for each of the cells in the matrix.
	private int[][] rightDown(int[][] matrix, int N, int M) {
		int[][] right = new int[N][M];
		int[][] down = new int[N][M];
		for (int i = N - 1; i >= 0; i--) {
			for (int j = M - 1; j >= 0; j--) {
				if (matrix[i][j] == 1) {
					if (i == N - 1 && j == M - 1) {
						down[i][j] = 1;
						right[i][j] = 1;
					} else if (i == N - 1) {
						down[i][j] = 1;
						right[i][j] = right[i][j + 1] + 1;
					} else if (j == M - 1) {
						down[i][j] = down[i + 1][j] + 1;
						right[i][j] = 1;
					} else {
						down[i][j] = down[i + 1][j] + 1;
						right[i][j] = right[i][j + 1] + 1;
					}
				}
			}
		}
		// Merge right and down, return the merged matrix.
		merge(right, down, N, M);
		return right;
	}
	
	// Largest X Of 1s
	public int largestI(int[][] matrix) {
	    // Assumptions: matrix is not null, has size of N * M
	    // N >= 0 and M >= 0
	    int N = matrix.length;
	    if (N == 0) {
	      return 0;
	    }
	    int M = matrix[0].length;
	    if (M == 0) {
	      return 0;
	    }
	    int[][] leftUpI = leftUpI(matrix, N, M);
	    int[][] rightDownI = rightDownI(matrix, N, M);
	    return merge(leftUpI, rightDownI, N, M);
	  }

	  private int[][] leftUpI(int[][] matrix, int N, int M) {
	    int[][] left = new int[N][M];
	    int[][] up = new int[N][M];
	    for (int i = 0; i < N; i++) {
	      for (int j = 0; j < M; j++) {
	        if (matrix[i][j] == 1) {
	          left[i][j] = getNumber(left, i - 1, j - 1, N, M) + 1;
	          up[i][j] = getNumber(up, i - 1, j + 1, N, M) + 1;
	        }
	      }
	    }
	    merge(left, up, N, M);
	    return left;
	  }
	  private int[][] rightDownI(int[][] matrix, int N, int M) {
	    int[][] right = new int[N][M];
	    int[][] down = new int[N][M];
	    for (int i = N - 1; i >= 0; i--) {
	      for (int j = M - 1; j >= 0; j--) {
	        if (matrix[i][j] == 1) {
	          right[i][j] = getNumber(right, i + 1, j + 1, N, M) + 1;
	          down[i][j] = getNumber(down, i + 1, j - 1, N, M) + 1;
	        }
	      }
	    }
	    merge(right, down, N, M);
	    return right;
	  }
	  private int getNumber(int[][] number, int x, int y, int N, int M) {
	    if (x < 0 || x >= N || y < 0 || y >= M) {
	      return 0;
	    }
	    return number[x][y];
	  }
	  
	// Largest Square Surrounded By One
	public int largestSquareSurroundedByOne(int[][] matrix) {
		// Assumptions: matrix is not null, has size of M * N,
		// N >= 0 and M >= 0, the elements in the matrix are either 0 or 1
		if (matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int result = 0;
		int M = matrix.length;
		int N = matrix[0].length;
		// the longest left are length ending at each position in the matrix.
		// we here apply a trick for ease of later processing:
		// left[i][j] is actually mapped to matrix[i - 1][j - 1],
		// this will reduced the corner cases.
		int[][] left = new int[M + 1][N + 1];
		int[][] up = new int[M + 1][N + 1];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (matrix[i][j] == 1) {
					left[i + 1][j + 1] = left[i + 1][j] + 1;
					up[i + 1][j + 1] = up[i][j + 1] + 1;
					// the maximum length of a surrounded by 1 matrix with rightbottom
					// position at matrix[i][j] is determined by the min value of
					// left[i + 1][j + 1] and up[i + 1][j + 1],
					// and we check one by one all the possible lengths if it can
					// provide the actual matrix.
					// we only need to check the longest left are length of the righttop
					// cell and the longest up arm length of the leftbottom cell.
					for (int maxLength = Math.min(left[i + 1][j + 1], up[i + 1][j + 1]); maxLength >= 1; maxLength--) {
						if (left[i + 2 - maxLength][j + 1] >= maxLength && up[i + 1][j + 2 - maxLength] >= maxLength) {
							result = Math.max(result, maxLength);
							break;
						}
					}
				}
			}
		}
		return result;
	}
	
	public int largestSquareSurroundedByOneII(int[][] matrix) {
		if (matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int res = 0;
		int M = matrix.length;
		int N = matrix[0].length;
		int[][] M1 = new int[M][N];
		int[][] M2 = new int[M][N];
		for (int i = M - 1; i >= 0; i--) {
			for (int j = N - 1; j >= 0; j--) {
				if (matrix[i][j] == 1) {
					if (i == M - 1 && j == N - 1) {
						M1[i][j] = 1;
						M2[i][j] = 1;
					} else if (i == M - 1) {
						M1[i][j] = M1[i][j + 1] + 1;
						M2[i][j] = 1;
					} else if (j == N - 1) {
						M1[i][j] = 1;
						M2[i][j] = M2[i + 1][j] + 1;
					} else {
						M1[i][j] = M1[i][j + 1] + 1;
						M2[i][j] = M2[i + 1][j] + 1;
					}
				}
			}
		}
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				for (int length = Math.min(M1[i][j], M2[i][j]); length > 0; length--) {
					if (M2[i][j + length - 1] >= length && M1[i + length - 1][j] >= length) {
						res = Math.max(res, length);
						break;
					}
				}
			}
		}
		return res;
	}
	
	// Largest Square Of Matches
	public int largestSquareOfMatches(int[][] matrix) {
	    if (matrix.length == 0 || matrix[0].length == 0) {
	      return 0;
	    }
	    int result = 0;
	    int M = matrix.length;
	    int N = matrix[0].length;
	    // The longest right/down are length ending at each position in the matrix.
	    int[][] right = new int[M + 1][N + 1];
	    int[][] down = new int[M + 1][N + 1];
	    for (int i = M - 1; i >= 0; i--) {
	      for (int j = N - 1; j >= 0; j--) {
	        if (hasRight(matrix[i][j])) {
	          right[i][j] = right[i][j + 1] + 1;
	        }
	        if (hasDown(matrix[i][j])) {
	          down[i][j] = down[i + 1][j] + 1;
	        }
	        if (hasBoth(matrix[i][j])) {
	          for (int maxLength = Math.min(right[i][j], down[i][j]); maxLength >= 1; maxLength--) {
	            if (right[i + maxLength][j] >= maxLength &&
	              down[i][j + maxLength] >= maxLength) {
	                result = Math.max(result, maxLength);
	                break;
	            }
	          }
	        }
	      }
	    }
	    return result;
	  }
	  private boolean hasRight(int value) {
	    return (value & 0b1) != 0;
	  }
	  private boolean hasDown(int value) {
	    return (value & 0b10) != 0;
	  }

	private boolean hasBoth(int value) {
		return value == 0b11;
	}
	
	// Largest SubMatrix Sum
	public int largestSubMatrixSum(int[][] matrix) {
	    // Assumptions: matrix is not null, has size N * M and N, M >= 1
	    int N = matrix.length;
	    int M = matrix[0].length;
	    int result = Integer.MIN_VALUE;
	    for (int i = 0; i < N; i++) {
	      int[] cur = new int[M];
	      for (int j = i; j < N; j++) {
	        // For each row i, we add the rows one by one for row j,
	        // to make sure each time we only introduce O(n) time.
	        add(cur, matrix[j]);
	        // For each possible pair of rows i, j,
	        // we would like to know what is the largest submatrix sum
	        // using top row as row i and bottom row as row j.
	        // We "flatten" these area to a one dimensional array.
	        result = Math.max(result, max(cur));
	      }
	    }
	    return result;
	  }
	  private void add(int[] cur, int[] add) {
	    for (int i = 0; i < cur.length; i++) {
	      cur[i] += add[i];
	    }
	  }
	  // Largest subarray sum.
	  private int max(int[] cur) {
	    int result = cur[0];
	    int tmp = cur[0];
	    for (int i = 1; i < cur.length; i++) {
	      tmp = Math.max(tmp + cur[i], cur[i]);
	      result = Math.max(result, tmp);
	    }
	    return result;
	  }
	
	public int distance(TreeNode root, TreeNode a, TreeNode b) {
		int small = Math.min(a.value, b.value);
		int large = Math.max(a.value, b.value);
		TreeNode dummy = root;
		while (dummy != null) {
			if (root.value < small) {
				dummy = dummy.right;
			} else if (dummy.value > large) {
				dummy = dummy.left;
			} else {
				// dummy is the LCA for a and b
				return 2 * findHeight(root, dummy) - findHeight(root, a) - findHeight(root, b);
			}
		}
		return 0;
	}
	private int findHeight(TreeNode root, TreeNode target) {
		int height = 0;
		while (root != target) {
			if (root.value < target.value) {
				root = root.right;
			} else if (root.value > target.value) {
				root = root.left;
			} else {
				return height;
			}
			height++;
		}
		return 0;
	}
}
