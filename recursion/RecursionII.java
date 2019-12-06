package recursion;

import java.util.ArrayList;
import java.util.List;

import solution.ListNode;
import solution.TreeNode;

public class RecursionII {
	public static void main(String args[]) {
		RecursionII recursion = new RecursionII();
		int[][] matrix = { { -85, 56, 37, 48 }, { -25, -78, -29, 62 }, { 18, -60, -74, -84 }, { 90, 44, 5, 1 } };
		System.out.println(recursion.spiral(matrix));
		System.out.println(matrix[1][2]);
		String s = "abc";
		System.out.print(s.length());
		StringBuilder sb = new StringBuilder();
		sb.append("if {");
		System.out.print(sb);
	}

	// Spiral Order Traversal I
	// Traverse an N * N 2D array in spiral order clock-wise starting from the top
	// left corner. Return the list of traversal sequence.
	public List<Integer> spiral(int[][] matrix) {
		// Assumptions: matrix is N * N, N >= 0, matrix is not null
		List<Integer> list = new ArrayList<Integer>();
		recursiveTraverse(matrix, 0, matrix.length, list);
		return list;
	}

	private void recursiveTraverse(int[][] matrix, int offset, int size, List<Integer> res) {
		// the base case is when there is only 0 or 1 element left
		if (size == 0) {
			return;
		}
		// Do not forget this base case
		if (size == 1) {
			res.add(matrix[offset][offset]);
			return;
		}
		for (int i = 0; i < size - 1; i++) {
			res.add(matrix[offset][offset + i]);
		}
		for (int i = 0; i < size - 1; i++) {
			res.add(matrix[offset + i][offset + size - 1]);
		}
		for (int i = size - 1; i >= 1; i--) {
			res.add(matrix[offset + size - 1][offset + i]);
		}
		for (int i = size - 1; i >= 1; i--) {
			res.add(matrix[offset + i][offset]);
		}
		recursiveTraverse(matrix, offset + 1, size - 2, res);
	}

	// Reverse Linked List In Pairs
	public ListNode reverseInPairs(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode newHead = head.next;
		head.next = reverseInPairs(head.next.next);
		newHead.next = head;
		return newHead;
	}

	// String Abbreviation Matching
	public boolean match(String input, String pattern) {
		// Assumptions: input, pattern are not null
		return match(input, pattern, 0, 0);
	}

	private boolean match(String s, String t, int si, int ti) {
		// Only when we run out of s and t at the same time, there is a match
		if (si == s.length() && ti == t.length()) {
			return true;
		}
		// If we run out of one of s and t but there is still some
		// characters left for the other one, we can not find the match
		if (si >= s.length() || ti >= t.length()) {
			return false;
		}
		// Case 1: if the current character in t is not a digit
		if (t.charAt(ti) < '0' || t.charAt(ti) > '9') {
			if (s.charAt(si) == t.charAt(ti)) {
				return match(s, t, si + 1, ti + 1);
			}
			return false;
		}
		// Case 2: if the current character in t is a digit.
		// We need to find in total what is the number. e.g. "123" means number 123
		int count = 0;
		while (ti < t.length() && t.charAt(ti) >= '0' && t.charAt(ti) <= '9') {
			count = count * 10 + (t.charAt(ti) - '0');
			ti++;
		}
		return match(s, t, si + count, ti);
	}

	// N Queens
	public List<List<Integer>> nqueens(int n) {
		// Validate the queen position in O(n) each time
		// Assumptions: n > 0
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		// cur will be a list of size n, and cur[i] is the column number
		// where the queen on row i positioned
		List<Integer> cur = new ArrayList<>();
		helper(n, cur, res);
		return res;
	}

	private void helper(int n, List<Integer> cur, List<List<Integer>> res) {
		// base case: when for all the rows we know where the queen is positioned
		if (cur.size() == n) {
			res.add(new ArrayList<Integer>(cur));
			return;
		}
		for (int i = 0; i < n; i++) {
			// check if putting a queen at column i at current row is valid
			if (valid(cur, i)) {
				cur.add(i);
				helper(n, cur, res);
				cur.remove(cur.size() - 1);
			}
		}
	}

	private boolean valid(List<Integer> cur, int col) {
		int row = cur.size();
		for (int i = 0; i < row; i++) {
			if (cur.get(i) == col || Math.abs(cur.get(i) - col) == row - i) {
				return false;
			}
		}
		return true;
	}

	// Store Number Of Nodes In Left Subtree
	public void numNodesLeft(TreeNodeLeft root) {
		numNodes(root);
	}

	private int numNodes(TreeNodeLeft root) {
		if (root == null) {
			return 0;
		}
		// LeftNum is the # of nodes in subtree of root.left
		int leftNum = numNodes(root.left);
		// rightNum is the # of nodes in subtree of root.right
		int rightNum = numNodes(root.right);
		root.numNodesLeft = leftNum;
		// Return the total # of nodes in the subtree of root
		return leftNum + rightNum + 1;
	}
	
	// Lowest Common Ancestor I
	// Given two nodes in a binary tree, find their lowest common ancestor.
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode one, TreeNode two) {
		// Assumptions: root is not null, one and two guaranteed to be in the tree and
		// not null
		if (root == null) {
			return null;
		}
		// if root is one or two, we can ignore the later recursions
		if (root == one || root == two) {
			return root;
		}
		TreeNode ll = lowestCommonAncestor(root.left, one, two);
		TreeNode lr = lowestCommonAncestor(root.right, one, two);
		if (ll != null && lr != null) {
			return root;
		}
		return ll == null ? lr : ll;
	}
}

class TreeNodeLeft {
	public int key;
	public TreeNodeLeft left;
	public TreeNodeLeft right;
	public int numNodesLeft;

	public TreeNodeLeft(int key) {
		this.key = key;
	}
}
