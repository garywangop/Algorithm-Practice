package recursion;

import java.util.ArrayList;
import java.util.List;

import solution.TreeNode;

public class RecursionIII {
	public static void main(String args[]) {

	}

	// Maximum Path Sum Binary Tree II (path from any node to any node)
	// Given a binary tree in which each node contains an integer number. Find the
	// maximum possible sum from any node to any node (the start node and the end
	// node can be the same).
	public int maxPathSum(TreeNode root) {
		// Assumptions: root is not null.
		// max stores the global maximum path sum and will be
		// updated during the recursion.
		int[] max = new int[] { Integer.MIN_VALUE };
		helper(root, max);
		return max[0];
	}

	// Return the maximum path sum of the "single" path.
	private int helper(TreeNode root, int[] max) {
		if (root == null) {
			return 0;
		}
		int left = helper(root.left, max);
		int right = helper(root.right, max);
		left = left < 0 ? 0 : left;
		right = right < 0 ? 0 : right;
		max[0] = Math.max(root.value + left + right, max[0]);
		return root.value + Math.max(left, right);
	}

	// Max Path Sum From Leaf To Root (path from leaf to root)
	// Given a binary tree in which each node contains an integer number. Find the
	// maximum possible path sum from a leaf to root.
	public int maxPathSumLeafToRoot(TreeNode root) {
		// Assumptions: root != null
		return maxPathSum(root, 0);
	}

	private int maxPathSum(TreeNode root, int sum) {
		sum += root.value;
		if (root.left == null && root.right == null) {
			return sum;
		} else if (root.left == null) {
			return maxPathSum(root.right, sum);
		} else if (root.right == null) {
			return maxPathSum(root.left, sum);
		}
		return Math.max(maxPathSum(root.left, sum), maxPathSum(root.right, sum));
	}

	// Binary Tree Path Sum To Target III (the path can only be from one node to
	// itself or to any of its descendants)
	// Given a binary tree in which each node contains an integer number. Determine
	// if there exists a path (the path can only be from one node to itself or to
	// any of its descendants), the sum of the numbers on the path is the given
	// target number.
	public boolean exist(TreeNode root, int target) {
		if (root == null) {
			return false;
		}
		// pass down the prefix of the path.
		List<TreeNode> path = new ArrayList<>();
		return helper141(root, path, target);
	}

	private boolean helper141(TreeNode root, List<TreeNode> path, int sum) {
		path.add(root);
		// Check if can find a subpath ended at root node,
		// the sum of the subpath is target.
		int temp = 0;
		for (int i = path.size() - 1; i >= 0; i--) {
			temp += path.get(i).value;
			if (temp == sum) {
				return true;
			}
		}
		if (root.left != null && helper141(root.left, path, sum)) {
			return true;
		}
		if (root.right != null && helper141(root.right, path, sum)) {
			return true;
		}
		// Don't forget for the cleanup when return to the previous level.
		path.remove(path.size() - 1);
		return false;
	}

	// Maximum Path Sum Binary Tree III
	public int maxPathSumIII(TreeNode root) {
		// Assumptions: root is not null.
		int[] max = new int[] { Integer.MIN_VALUE };
		helperIII(root, max);
		return max[0];
	}

	private int helperIII(TreeNode root, int[] max) {
		if (root == null) {
			return 0;
		}
		int left = helperIII(root.left, max);
		int right = helperIII(root.right, max);
		int sin = Math.max(Math.max(left, right), 0) + root.value;
		max[0] = Math.max(max[0], sin);
		return sin;
	}

	// Flatten Binary Tree to Linked List
	public TreeNode flatten(TreeNode root) {
		TreeNode[] prev = new TreeNode[1];
		helper523(root, prev);
		return root;
	}

	private void helper523(TreeNode root, TreeNode[] prev) {
		if (root == null) {
			return;
		}
		helper523(root.right, prev);
		helper523(root.left, prev);
		root.right = prev[0];
		root.left = null;
		prev[0] = root;
	}
}
