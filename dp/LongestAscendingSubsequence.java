package dp;

public class LongestAscendingSubsequence {

	public static void main(String[] args) {
		LongestAscendingSubsequence sol = new LongestAscendingSubsequence();
		int[] array = new int[] { 28,4,8,14,14,12,7,14,28,24,9,30,28,29,26,3,17,18,5,29,18,8,30,32,13,29,6 };
		int[] res = sol.longest2(array);
		for (int i : res) {
			System.out.println(i);
		}
		

	}

	public int longest(int[] array) {
		if (array.length == 0) {
			return 0;
		}
		int res = 1;
		int[] dp = new int[array.length];
		for (int i = 1; i < array.length; i++) {
			dp[i] = 1;
			for (int j = i - 1; j >= 0; j--) {
				if (array[i] > array[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			res = Math.max(res, dp[i]);
		}
		return res;
	}

	/*
	 * 682. Longest Ascending Subsequence II
	 * 
	 * Given an array A[0]...A[n-1] of integers, find out the longest ascending
	 * subsequence. If there are multiple results, then return any valid result.
	 * 
	 * Assumptions
	 * 
	 * A is not null Examples Input: A = {5, 2, 6, 3, 4, 7, 5} Output: [2,3,4,5]
	 * Because [2, 3, 4, 5] is one of the longest ascending subsequences.
	 */
	
	public int[] longest2(int[] arr) {
	    if (arr.length == 0) {
	      return new int[0];
	    }
	    
	    int[] dp = new int[arr.length];
	    int[] pred = new int[arr.length];
	    for (int i = 0; i < arr.length; i++) {
	      dp[i] = 1;
	      pred[i] = -1;
	      for (int j = 0; j < i; j++) {
	        if (arr[i] > arr[j] && dp[i] < dp[j] + 1) {
	          dp[i] = dp[j] + 1;
	          pred[i] = j;
	        }
	      }
	    }
	    int end = 0;
	    for (int i = 1; i < arr.length; i++) {
	    	if (dp[i] > dp[end]) {
	    		end = i;
	    	}
	    }
	    
	    return traceBack(arr, pred, end, dp[end]);
	  }

	  private int[] traceBack(int[] arr, int[] pred, int index, int size) {
	    int[] res = new int[size];
	    while (index != -1) {
	    	res[size - 1] = arr[index];
	    	size--;
	    	index = pred[index];
	    }
	    return res;
	  }

}
