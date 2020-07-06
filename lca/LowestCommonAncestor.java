package lca;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;



public class LowestCommonAncestor {
	
	@Test
	public void test() {
		LowestCommonAncestor test = new LowestCommonAncestor();
		KnaryTreeNode node1 = new KnaryTreeNode(5);
		KnaryTreeNode node2 = new KnaryTreeNode(9);
		KnaryTreeNode node3 = new KnaryTreeNode(12);
		KnaryTreeNode node4 = new KnaryTreeNode(2);
		KnaryTreeNode node5 = new KnaryTreeNode(3);
		KnaryTreeNode node6 = new KnaryTreeNode(14);
		KnaryTreeNode node7 = new KnaryTreeNode(1);
		node1.children.add(node2);
		node1.children.add(node3);
		node2.children.add(node4);
		node2.children.add(node5);
		node2.children.add(node7);
		node3.children.add(node6);
		assertEquals(node2, test.lca5(node1,  node4, node5));
		List<KnaryTreeNode> list = new ArrayList<>();
		list.add(node2);
		list.add(node4);
		list.add(node6);
		assertEquals(node1, test.lca6(node1, list));
	}

	// LCA 1
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode one, TreeNode two) {
		if (root == null || root == one || root == two) {
			return root;
		}

		TreeNode left = lowestCommonAncestor(root.left, one, two);
		TreeNode right = lowestCommonAncestor(root.right, one, two);
		if (left != null && right != null) {
			return root;
		}
		return left == null ? right : left;
	}

	// LCA 2
	public TreeNodeP lowestCommonAncestor(TreeNodeP one, TreeNodeP two) {
		int height1 = getHeight(one);
		int height2 = getHeight(two);
		if (height1 > height2) {
			return lca(one, two, height1 - height2);
		} else {
			return lca(two, one, height2 - height1);
		}
	}

	private int getHeight(TreeNodeP root) {
		int res = 0;
		while (root.parent != null) {
			root = root.parent;
			res++;
		}
		return res;
	}

	private TreeNodeP lca(TreeNodeP one, TreeNodeP two, int diff) {
		while (diff != 0) {
			one = one.parent;
			diff--;
		}

		while (one != null && two != null && one != two) {
			one = one.parent;
			two = two.parent;
		}

		return one == two ? one : null;
	}
	
	// LCA 3
	// Find the lca for node 1 and node 2, node1 and node2 may not in the tree
	public TreeNode lca3(TreeNode root, TreeNode one, TreeNode two) {
		TreeNode node = lowestCommonAncestor(root, one, two);
		if (node == one) {
			return find(one, two) ? one : null;
		} else if (node == two) {
			return find(two, one) ? two : null;
		} else {
			return node;
		}
	}
	
	private boolean find(TreeNode root, TreeNode target) {
		if (root == null) {
			return false;
		}
		if (root == target) {
			return true;
		}
		return find(root.left, target) || find(root.right, target);
	}
	
	// LCA 4
	// Given k nodes in a binary tree, k nodes are guaranteed in the tree. Find their lca.
	public TreeNode lca4(TreeNode root, List<TreeNode> nodes) {
		Set<TreeNode> set = new HashSet<>(nodes);
		return lca(root, set);
	}
	
	private TreeNode lca(TreeNode root, Set<TreeNode> set) {
		if (root == null || set.contains(root)) {
			return root;
		}
		TreeNode left = lca(root.left, set);
		TreeNode right = lca(root.right, set);
		if (left != null && right != null) {
			return root;
		}
		return left == null ? right : left;
	}
	
	// LCA 5
	// Given 2 nodes in a k-nary tree, find their lca
	public KnaryTreeNode lca5(KnaryTreeNode root, KnaryTreeNode a, KnaryTreeNode b) {
		if (root == null || root == a || root == b) {
			return root;
		}
		KnaryTreeNode res = null;
		for (KnaryTreeNode node : root.children) {
			KnaryTreeNode subNode = lca5(node, a, b);
			if (subNode != null) {
				if (res != null) {
					return root;
				}
				res = subNode;
			}
		}
		return res;
	}
	
	// LCA 6
	// Given k nodes in a k-nary tree, find their lca
	public KnaryTreeNode lca6(KnaryTreeNode root, List<KnaryTreeNode> nodes) {
		Set<KnaryTreeNode> set = new HashSet<>(nodes);
		return helper6(root, set);
	}
	
	private KnaryTreeNode helper6(KnaryTreeNode root, Set<KnaryTreeNode> set) {
		if (root == null || set.contains(root)) {
			return root;
		}
		
		KnaryTreeNode res = null;
		for (KnaryTreeNode node : root.children) {
			KnaryTreeNode subNode = helper6(node, set);
			if (subNode != null) {
				if (res != null) {
					return root;
				}
				res = subNode;
			}
		}
		return res;
	}
}
