package review;

import java.util.Queue;
import java.util.ArrayDeque;

public class DeleteBinaryTree {

	public static void main(String[] args) {
		DeleteBinaryTree sol = new DeleteBinaryTree();
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		TreeNode node6 = new TreeNode(6);
		TreeNode node7 = new TreeNode(7);
		TreeNode node8 = new TreeNode(8);
		TreeNode node9 = new TreeNode(9);
		node1.left = node2;
		node1.right = node3;
		node2.left = node4;
		node3.right = node5;
		node4.left = node6;
		node4.right = node7;
		node5.left = node8;
		node5.right = node9;
		
		TreeNode root = sol.delete(node1);
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
	
	

	public TreeNode delete(TreeNode root) {
		if (root == null) {
			return null;
		}
		root.left = delete(root.left);
		root.right = delete(root.right);
		if (root.left == null && root.right == null) {
			return root;
		} else if (root.left == null || root.right == null) {
			return root.left == null ? root.right : root.left;
		} else {
			return root;
		}
	}
}
