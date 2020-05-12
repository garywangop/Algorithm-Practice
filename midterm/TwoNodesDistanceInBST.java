package midterm;

import java.util.Arrays;

public class TwoNodesDistanceInBST {

	public static void main(String[] args) {
		TwoNodesDistanceInBST sol = new TwoNodesDistanceInBST();
		
		TreeNode node1 = new TreeNode(15);
		TreeNode node2 = new TreeNode(5);
		TreeNode node3 = new TreeNode(20);
		TreeNode node4 = new TreeNode(3);
		TreeNode node5 = new TreeNode(10);
		TreeNode node6 = new TreeNode(1);
		TreeNode node7 = new TreeNode(4);
		TreeNode node8 = new TreeNode(8);
		TreeNode node9 = new TreeNode(7);
		TreeNode node10 = new TreeNode(9);
		
		node1.left = node2;
		node1.right = node3;
		node2.left = node4;
		node2.right = node5;
		node4.left = node6;
		node4.right = node7;
		node5.left = node8;
		node8.left = node9;
		node8.right = node10;
		
		System.out.println(sol.findDistance(node1, node4, node8));
		System.out.println(sol.findDistance(node1, node2, node9));
		System.out.println(sol.findDistance(node1, node6, node10));
		
	}

	public int findDistance(TreeNode root, TreeNode a, TreeNode b) {
		if (a.val > b.val) {
			findDistance(root, b, a);
		}
		TreeNode node = lca(root, a, b);
		return distance(node, a) + distance(node, b);
	}
	
	private TreeNode lca(TreeNode root, TreeNode a, TreeNode b) {
		while (root.val < a.val || root.val > b.val) {
			if (root.val < a.val) {
				root = root.right;
			} else {
				root = root.left;
			}
		}
		return root;
	}
	
	private int distance(TreeNode root, TreeNode a) {
		int res = 0;
		while (root != a) {
			if (root.val > a.val) {
				root = root.left;
			} else {
				root = root.right;
			}
			res++;
		}
		return res;
	}

}
