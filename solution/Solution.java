package solution;

import java.util.*;

public class Solution {
	
	public static void main( String[] args) {
		Solution sol = new Solution();
		int[] arr = {1, 2, 3, 4};
		
	}
	
	public TreeNode LCA(TreeNode one, TreeNode two) {
		int l1 = getHeight(one);
		int l2 = getHeight(two);
		if (l1 < l2) {
			return mergeNode(one, two, l2 - l1);
		} else {
			return mergeNode(two, one, l1 - l2);
		}
	}
	private int getHeight(TreeNode root) {
		int height = 0;
		while (root != null) {
			height++;
			root = root.parent;
		}
		return height;
	}
	private TreeNode mergeNode(TreeNode one, TreeNode two, int diff) {
		while (diff > 0) {
			two = two.parent;
			diff--;
		}
		while (one != two) {
			one = one.parent;
			two = two.parent;
		}
		return one;
	}
}
