package review;

public class BuyStock {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = new int[] {5,1,2,3,7,2,5,1,3};
		System.out.println(maxProfit2(arr));
	}

	/*
	 * 92. Buy Stock I
	 * 
	 * Given an array of positive integers representing a stock’s price on each day.
	 * On each day you can only make one operation: either buy or sell one unit of
	 * stock and you can make at most 1 transaction. Determine the maximum profit
	 * you can make.
	 * 
	 * Assumptions
	 * 
	 * The given array is not null and is length of at least 2. Examples
	 * 
	 * {2, 3, 2, 1, 4, 5}, the maximum profit you can make is 5 - 1 = 4
	 */
	// T = O(n), S = O(1)
	public int maxProfit(int[] array) {
		int res = 0;
		int min = array[0];
		for (int i = 1; i < array.length; i++) {
			if (array[i] < min) {
				min = array[i];
			} else {
				res = Math.max(res, array[i] - min);
			}
		}
		return res;
	}

	/*
	 * 93. Buy Stock II
	 * 
	 * Given an array of positive integers representing a stock’s price on each day.
	 * On each day you can only make one operation: either buy or sell one unit of
	 * stock, you can make as many transactions you want, but at any time you can
	 * only hold at most one unit of stock. Determine the maximum profit you can
	 * make.
	 * 
	 * Assumptions
	 * 
	 * The give array is not null and is length of at least 2 Examples
	 * 
	 * {2, 3, 2, 1, 4, 5}, the maximum profit you can make is (3 - 2) + (5 - 1) = 5
	 */
	
	public static int maxProfit2(int[] array) {
		int min = array[0], temp = 0, profit = 0;
	    for (int i = 1; i < array.length; i++) {
	      if (array[i] < array[i - 1]) {
	        min = array[i];
	        profit += temp;
	        temp = 0;
	      } else {
	        temp = Math.max(array[i] - min, temp);
	      }
	    }
	    return min < array[array.length - 1] ? profit += temp : profit;
	  }
}
