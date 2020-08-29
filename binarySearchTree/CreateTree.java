package binarySearchTree;

import java.util.ArrayDeque;
import java.util.Queue;

import solution.TreeNode;

public class CreateTree {

	Integer[] list;

	public CreateTree(Integer[] list) {
		this.list = list;
	}
	
	public TreeNode create() {
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
}
