package binaryTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.ArrayDeque;

import solution.TreeNode;

public class TopView {

	public static void main(String[] args) {
		TreeNode node1 = new TreeNode(10);
		TreeNode node2 = new TreeNode(5);
		TreeNode node3 = new TreeNode(15);
		TreeNode node4 = new TreeNode(1);
		TreeNode node5 = new TreeNode(8);
		TreeNode node6 = new TreeNode(7);
		node1.left = node2;
		node1.right = node3;
		node2.left = node4;
		node2.right = node5;
		node3.right = node6;

		TopView sol = new TopView();
		System.out.println(sol.topView(node1));
		
		Map<Integer, Integer> map = new HashMap<>();
		map.put(1, 11);
		map.put(3, 33);
		map.put(5, 55);
		map.put(2, 22);
		map.put(-1, -11);
		map.put(-100, -100);
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			System.out.println(entry.getValue());
		}
	}

	public List<Integer> topView(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		if (root == null) {
			return res;
		}
		Queue<Node> q = new ArrayDeque<>();
		q.offer(new Node(root, 0));
		res.add(root.value);
		int left = 0, right = 0;
		while (!q.isEmpty()) {
			Node cur = q.poll();
			TreeNode curNode = cur.node;
			int coordinate = cur.x;
			if (coordinate < left) {
				res.add(curNode.value);
				left = coordinate;
			}
			if (coordinate > right) {
				res.add(curNode.value);
				right = coordinate;
			}
			if (curNode.left != null) {
				q.offer(new Node(curNode.left, coordinate - 1));
			}
			if (curNode.right != null) {
				q.offer(new Node(curNode.right, coordinate + 1));
			}
		}
		return res;
	}

	static class Node {
		TreeNode node;
		int x;

		public Node(TreeNode node, int x) {
			this.node = node;
			this.x = x;
		}
	}
	
	public void levelView(TreeNode root) {
		if (root == null) {
			return;
		}
		Queue<TreeNode> q = new ArrayDeque<>();
		q.offer(root);
		while (!q.isEmpty()) {
			TreeNode cur = q.poll();
			if (cur.left != null) {
				q.offer(cur.left);
			}
			if (cur.right != null) {
				q.offer(cur.right);
			}
			System.out.println(cur.value);
		}
	}

}
