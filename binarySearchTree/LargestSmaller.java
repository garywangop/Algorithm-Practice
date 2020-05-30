package binarySearchTree;

import solution.TreeNode;

public class LargestSmaller{
	
	// Find the largest node value which is smaller than the target node
	public int largestSmaller(TreeNode root, TreeNode target) {
		if (root == null) {
			return -1;
		}
		int res = -1;
		while (root != null) {
			if (root.value >= target.value) {
				root = root.left;
			} else {
				res = Math.max(res, root.value);
				root = root.right;
			}
		}
		return res;
	}

}
