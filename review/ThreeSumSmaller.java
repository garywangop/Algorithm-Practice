package review;

import java.util.Arrays;

public class ThreeSumSmaller {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		

	}

	/*
	 * 496. 3Sum Smaller
	 * 
	 * Given an array of n integers nums and a target, find the number of index
	 * triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] +
	 * nums[j] + nums[k] < target.
	 * 
	 * For example, given nums = [-2, 0, 1, 3], and target = 2.
	 * 
	 * Return 2. Because there are two triplets which sums are less than 2:
	 * 
	 * [-2, 0, 1] [-2, 0, 3]
	 */
	public int threeSumSmaller(int[] num, int target) {
	    if (num == null || num.length <= 2) {
	      return 0;
	    }
	    int res = 0;
	    Arrays.sort(num);
	    for (int i = 0; i < num.length - 2; i++) {
	      int left = i + 1, right = num.length - 1;
	      while (left < right) {
	        if (num[i] + num[left] + num[right] < target) {
	          res += right - left;
	          left++;
	        } else {
	          right--;
	        }
	      }
	    }
	    return res;
	  }
}
