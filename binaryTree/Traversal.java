package binaryTree;

import java.util.List;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.ArrayList;
import solution.TreeNode;

public class Traversal {

	public static void main(String[] args) {
		TreeNode t1 = new TreeNode(1);
		TreeNode t2 = new TreeNode(2);
		TreeNode t3 = new TreeNode(3);
		TreeNode t4 = new TreeNode(4);
		TreeNode t5 = new TreeNode(5);
		TreeNode t6 = new TreeNode(6);
		TreeNode t7 = new TreeNode(7);
		t1.left = t2;
		t1.right = t3;
		t2.left = t4;
		t2.right = t5;
		t3.left = t6;
		t3.right = t7;
		
		Traversal test = new Traversal();
		System.out.println(test.preOrder(t1));
		System.out.println(test.inOrder(t1));
		System.out.println(test.postOrder(t1));
	}

	public List<Integer> preOrder(TreeNode root) {
	    List<Integer> res = new ArrayList<>();
	    if (root == null) {
	      return res;
	    }
	    Deque<TreeNode> stack = new ArrayDeque<>();
	    stack.offerFirst(root);
	    while (!stack.isEmpty()) {
	      TreeNode cur = stack.pollFirst();
	      res.add(cur.value);
	      if (cur.right != null) {
	        stack.offerFirst(cur.right);
	      }
	      if (cur.left != null) {
	        stack.offerFirst(cur.left);
	      }
	    }
	    return res;
	  }
	
	public List<Integer> inOrder(TreeNode root) {
	    List<Integer> res = new ArrayList<>();
	    if (root == null) {
	      return res;
	    }
	    Deque<TreeNode> stack = new ArrayDeque<>();
	    TreeNode helper = root;
	    while (helper != null || !stack.isEmpty()) {
	      if (helper != null) {
	        stack.offerFirst(helper);
	        helper = helper.left;
	      } else {
	        helper = stack.pollFirst();
	        res.add(helper.value);
	        helper = helper.right;
	      }
	    }
	    return res;
	  }
	
	public List<Integer> postOrder(TreeNode root) {
	    List<Integer> res = new ArrayList<>();
	    if (root == null) {
	      return res;
	    }
	    Deque<TreeNode> stack = new ArrayDeque<>();
	    TreeNode prev = null;
	    stack.offerFirst(root);
	    while (!stack.isEmpty()) {
	      TreeNode cur = stack.peekFirst();
	      // Going down:
	      if (prev == null || cur == prev.left || cur == prev.right) {
	        if (cur.left != null) {
	          stack.offerFirst(cur.left);
	        } else if (cur.right != null) {
	          stack.offerFirst(cur.right);
	        } else {
	          res.add(cur.value);
	          stack.pollFirst();
	        }
	      }
	      // From left subtree:
	      else if (prev == cur.left) {
	        if (cur.right != null) {
	          stack.offerFirst(cur.right);
	        } else {
	          res.add(cur.value);
	          stack.pollFirst();
	        }
	      }
	      // From right subtree:
	      else {
	        res.add(cur.value);
	        stack.pollFirst();
	      }
	      prev = cur;
	    }
	    return res;
	  }
}
