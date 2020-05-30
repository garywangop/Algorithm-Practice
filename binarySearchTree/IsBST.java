package binarySearchTree;

import solution.TreeNode;

public class IsBST{
	
	
	public boolean isBST(TreeNode root) {
		return helper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	private boolean helper(TreeNode root, int min, int max) {
		if (root == null) {
			return true;
		}
		if (root.value >= max || root.value <= min) {
			return false;
		}
		
		return helper(root.left, min, root.value) && helper(root.right, root.value, max);
	}
}
