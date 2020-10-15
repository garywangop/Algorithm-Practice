package binarySearchTree;

import java.util.*;

import solution.TreeNode;

public class Test {

	public static void main(String[] args) {
		
		Test sol = new Test();
		int[] array = new int[] {1,1,2,3,3,4,5,5,5};
		int[] res = sol.dedup(array);
		for (int i : res) {
			System.out.println(i);
		}
		
	}
	

	public int[] dedup(int[] array) {
	    // [0, slow] is the range we need
	    int slow = -1;
	    int fast = 0;
	    while (fast < array.length) {
	      if (slow == -1 || array[fast] != array[slow]) {
	        array[++slow] = array[fast++];
	      } else if (array[fast] == array[slow]) {
	        while (array[fast] == array[slow]) {
	          fast++;
	        }
	        slow--;
	      }
	    }
	    return Arrays.copyOf(array, slow + 1);
	  }

}
