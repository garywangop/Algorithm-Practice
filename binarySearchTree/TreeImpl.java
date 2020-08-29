package binarySearchTree;

import java.util.ArrayDeque;
import java.util.Queue;

import solution.TreeNode;

public class TreeImpl implements Tree{

	@Override
	public TreeNode create(Integer[] list) {
		if (list == null || list.length == 0) {
			return null;
		}
		
		int index = 0;
		TreeNode root = new TreeNode(list[0]);
		Queue<TreeNode> q = new ArrayDeque<>();
		q.offer(root);
		
		while (index < list.length) {
			TreeNode cur = q.poll();
			if (++index < list.length) {
				cur.left = new TreeNode(list[index]);
				q.offer(cur.left);
			}
			if (++index < list.length) {
				cur.right = new TreeNode(list[index]);
				q.offer(cur.right);
			}
			
		}
		return root;
	}

	@Override
	public void print(TreeNode root) {
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
