package binaryTree;

import solution.TreeNode;

public class Solution {

	public static void main(String args[]) {
		TreeNode t1 = new TreeNode(1);
		TreeNode t2 = new TreeNode(1);
		TreeNode t3 = new TreeNode(1);
		TreeNode t4 = new TreeNode(1);
		TreeNode t5 = new TreeNode(1);
		TreeNode t6 = new TreeNode(1);
		t1.right = t3;
		t1.left = t2;
		t2.right = t5;
		t2.left = t4;
		t3.left = t6;

		TreeNode tt1 = new TreeNode(1);
		TreeNode tt2 = new TreeNode(2);
		TreeNode tt3 = new TreeNode(3);
		tt1.left = tt2;
		tt1.right = tt3;
		Solution test = new Solution();

		System.out.print(test.countUnivalSubtrees(t1));
	}

	/*
	 * 142. Binary Tree Diameter Given a binary tree in which each node contains an
	 * integer number. The diameter is defined as the longest distance from one leaf
	 * node to another leaf node. The distance is the number of nodes on the path.
	 * 
	 * If there does not exist any such paths, return 0.
	 */
	int max = 0; // the diameter of the tree

	public int diameter(TreeNode root) {
		if (root == null) {
			return 0;
		}
		helper(root);
		return max;
	}

	public int helper(TreeNode root) {
		if (root.left == null && root.right == null) {
			return 1;
		}
		if (root.left == null) {
			return helper(root.right) + 1;
		}
		if (root.right == null) {
			return helper(root.left) + 1;
		}
		int left = helper(root.left);
		int right = helper(root.right);
		max = Math.max(left + right + 1, max);
		return Math.max(left, right) + 1;
	}

	/*
	 * diameter1 return的是从leaf到root的最大diameter，但是本题需要的是leaf到lead的最大diameter
	 */
	static int res = 0;

	public int diameter1(TreeNode root) {
		if (root == null) {
			return 0;
		}
		if (root.left == null && root.right == null) {
			return 1;
		}
		if (root.left == null) {
			return diameter1(root.right) + 1;
		}
		if (root.right == null) {
			return diameter1(root.left) + 1;
		}
		int left = diameter1(root.left);
		int right = diameter1(root.right);
		res = Math.max(left + right + 1, res);
		return Math.max(left, right) + 1;
	}

	/*
	 * 481. Count Univalue Subtrees Given a binary tree, count the number of
	 * uni-value subtrees.
	 * 
	 * A Uni-value subtree means all nodes of the subtree have the same value.
	 * 
	 * For example: Given binary tree,
	 * 
	 * 5 / \ 1 5 / \ \ 5 5 5 return 4.
	 */

	public int countUnivalSubtrees(TreeNode root) {
		int[] count = new int[]{0};
		helper481(root, count);
		return count[0];
	}

	private boolean helper481(TreeNode root, int[] count) {
		if (root == null) {
			return true;
		}
		boolean left = helper481(root.left, count);
		boolean right = helper481(root.right, count);
		if (left == true && right == true) {
			if (root.left != null && root.value != root.left.value) {
				return false;
			}
			if (root.right != null && root.value != root.right.value) {
				return false;
			}
			count[0]++;
			return true;
		}
		return false;
	}
}
