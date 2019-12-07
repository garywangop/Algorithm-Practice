package recursion;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import solution.TreeNode;

public class RecursionIII {
	public static void main(String args[]) {
		TreeNode N1 = new TreeNode(1);
		TreeNode N2 = new TreeNode(2);
		TreeNode N3 = new TreeNode(3);
		N1.left = N2;
		N1.right = N3;
		RecursionIII rec = new RecursionIII();
		System.out.println(rec.flatten(N1));
		while (N1 != null) {
			System.out.println(N1.value);
			N1 = N1.right;
		}
	}

	// From leaf to another leaf max sum
	public int leafToLeafSum(TreeNode root) {
		int[] res = new int[] { Integer.MIN_VALUE };
		leafToLeaf(root, res);
		return res[0];
	}

	private int leafToLeaf(TreeNode root, int[] res) {
		if (root == null) {
			return 0;
		}
		int left = leafToLeaf(root.left, res);
		int right = leafToLeaf(root.right, res);

		if (root.left != null && root.right != null) {
			int cur = left + right + root.value;
			res[0] = Math.max(res[0], cur);
			return Math.max(left, right) + root.value;
		} else if (root.left == null) {
			return root.value + right;
		} else if (root.right == null) {
			return root.value + left;
		} else { // root.left == null && root.right == null
			return root.value;
		}
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

	// DFS solution:
	public int maxPathSumLeafToRootdfs(TreeNode root) {
		List<Integer> path = new ArrayList<>();
		int[] max = new int[] { Integer.MIN_VALUE };
		dfs(path, root, max);
		return max[0];
	}

	private void dfs(List<Integer> path, TreeNode root, int[] max) {
		if (root == null) {
			return;
		}
		if (root.left == null && root.right == null) {
			max[0] = Math.max(max[0], sum(path) + root.value);
			return;
		}
		path.add(root.value);
		dfs(path, root.left, max);
		dfs(path, root.right, max);
		path.remove(path.size() - 1);
	}

	private int sum(List<Integer> path) {
		int sum = 0;
		for (int i = 0; i < path.size(); i++) {
			sum += path.get(i);
		}
		return sum;
	}

	// Binary Tree Path Sum To Target III (the path can only be from one node to
	// itself or to any of its descendants)
	// Given a binary tree in which each node contains an integer number. Determine
	// if there exists a path (the path can only be from one node to itself or to
	// any of its descendants), the sum of the numbers on the path is the given
	// target number.
	// DFS solution: T = O(n * height)
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

	// Use HashSet to optimize the time, T = O(n)
	public boolean existHashSet(TreeNode root, int target) {
		if (root == null) {
			return false;
		}
		Set<Integer> prefixSums = new HashSet<>();
		prefixSums.add(0);
		return helperII(root, prefixSums, 0, target);
	}

	private boolean helperII(TreeNode root, Set<Integer> prefixSums, int prevSum, int sum) {
		prevSum += root.value;
		if (prefixSums.contains(prevSum - sum)) {
			return true;
		}
		boolean needRemove = prefixSums.add(prevSum);
		if (root.left != null && helperII(root.left, prefixSums, prevSum, sum)) {
			return true;
		}
		if (root.right != null && helperII(root.right, prefixSums, prevSum, sum)) {
			return true;
		}
		if (needRemove) {
			prefixSums.remove(prevSum);
		}
		return false;
	}

	// 140: Maximum Path Sum Binary Tree III
	// DP largest subarray sum solution:
	public int maxPathSum140DPSolution(TreeNode root) {
		int[] max = new int[] { Integer.MIN_VALUE };
		int sum = 0;
		helper140DPSolution(root, max, sum);
		return max[0];
	}

	private void helper140DPSolution(TreeNode root, int[] max, int sum) {
		if (root == null) {
			return;
		}
		if (sum < 0) {
			sum = root.value;
		} else {
			sum += root.value;
		}
		max[0] = Math.max(max[0], sum);
		helper140DPSolution(root.left, max, sum);
		helper140DPSolution(root.right, max, sum);
	}

	// Recursion 三部曲来做
	public int maxPathSumIII(TreeNode root) {
		// Assumptions: root is not null.
		int[] max = new int[] { Integer.MIN_VALUE };
		helper140(root, max);
		return max[0];
	}

	private int helper140(TreeNode root, int[] max) {
		if (root == null) {
			return 0;
		}
		int left = helper140(root.left, max);
		int right = helper140(root.right, max);
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

	// Solution 2: stack
	public TreeNode flattenStack(TreeNode root) {
		if (root == null) {
			return root;
		}
		Deque<TreeNode> stack = new ArrayDeque<>();
		stack.offerFirst(root);
		while (!stack.isEmpty()) {
			TreeNode node = stack.pollFirst();
			if (node.right != null) {
				stack.offerFirst(node.right);
			}
			if (node.left != null) {
				stack.offerFirst(node.left);
			}
			if (!stack.isEmpty()) {
				node.left = null;
				node.right = stack.peekFirst();
			}
		}
		return root;
	}
}
