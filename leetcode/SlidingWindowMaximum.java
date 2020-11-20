package leetcode;

import java.util.PriorityQueue;

public class SlidingWindowMaximum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums3 = new int[] {1};
		int[] nums2 = new int[] {1,-1};
		int[] nums1 = new int[] {1,3,-1,-3,5,3,6,7};
		int[] nums4 = new int[] {9,11};
		int[] nums5 = new int[] {4,-2};
		SlidingWindowMaximum sol = new SlidingWindowMaximum();
		int[] res1 = sol.maxSlidingWindow(nums1, 3);
		
		for (int i : res1) {
			System.out.print(i + " ");
		}
	}
	
	// 239
	// Brute force
	public int[] maxSlidingWindow2(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
		for (int i = 0; i <= nums.length - k; i++) {
			res[i] = findMax(nums, i, i + k);
		}
		return res;
    }
	
	private int findMax(int[] nums, int start, int end) {
		int max = nums[start];
		for (int i = start + 1; i < end; i++) {
			max = Math.max(nums[i], max);
		}
		return max;
	}
	
	// 用个Queue实现monotonic queue
	/*
	 * 在sliding window里，保证q.peek()是当前最大值
	 * 比如以下这个例子，k = 3
	 * 1,3,-1,-3,5,3,6,7
	 * sliding window和q分别是：
	 * 所求的最大值就是p.peek()
	 * 1,3,-1: 初始window，在q.offer的过程中，先offer 1，遇到3的时候发现比1大，那么就把1 poll掉，让3成为第一个元素
	 * 			-1在3之后，是潜在的最大值，所以-1要offer到q里
	 * 			此时的q应该是：3，-1
	 * 3,-1,-3: q是3, -1, -3，由于-3在3之后且比3小，offer到q里，
	 * -1,-3,5: q是-1, -3, 5，由于5比-1
	 * -3,5,3
	 * 5,3,6
	 * 3,6,7
	 */
	public int[] maxSlidingWindow(int[] nums, int k) {
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
	}

}
