package binarySearchTree;

import java.util.ArrayDeque;
import java.util.Queue;

import solution.TreeNode;

public class PrintTree {
	
	TreeNode root;
	
	public PrintTree(TreeNode root) {
		this.root = root;
	}

	public void levelOrder() {
		Queue<TreeNode> q = new ArrayDeque<>();
		q.offer(root);
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				TreeNode cur = q.poll();
				if (cur.left != null) {
					q.offer(cur.left);
				}
				if (cur.right != null) {
					q.offer(cur.right);
				}
				System.out.print(cur.value + " ");
			}
			System.out.println();
		}
	}
}
