package binaryTree;

import java.util.ArrayDeque;
import java.util.Queue;

import solution.TreeNode;

public class MinimumDepthOfBinaryTree {
	public static void main(String args[]) {
		TreeNode t1 = new TreeNode(1);
		TreeNode t2 = new TreeNode(2);
		TreeNode t3 = new TreeNode(3);
		TreeNode t4 = new TreeNode(4);
		TreeNode t5 = new TreeNode(5);
		TreeNode t6 = new TreeNode(6);
		t1.right = t2;
		t2.left = t3;
		t2.right = t4;
		t3.left = t5;
		t3.right = t6;
		MinimumDepthOfBinaryTree test = new MinimumDepthOfBinaryTree();
		System.out.print(test.minDepthBFS(t1));
	}

	public int minDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int left = minDepth(root.left);
		int right = minDepth(root.right);

		if (left != 0 && right != 0) {
			return Math.min(left, right) + 1;
		} else if (left == 0) {
			return right + 1;
		} else { // right == 0
			return left + 1;
		}
	}

	public int minDepthBFS(TreeNode root) {
		if (root == null) {
			return 0;
		}
		Queue<TreeNode> queue = new ArrayDeque<>();
		int res = 1;
		queue.offer(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode cur = queue.poll();
				if (cur.left == null && cur.right == null) {
					return res;
				}
				if (cur.left != null) {
					queue.offer(cur.left);
				}
				if (cur.right != null) {
					queue.offer(cur.right);
				}
			}
			res++;
		}
		return res;
	}
}
