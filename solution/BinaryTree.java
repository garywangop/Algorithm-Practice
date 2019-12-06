package solution;

import java.util.*;

public class BinaryTree {
	public static void main(String args[]) {
		// Create TreeNode:
		TreeNode t1 = new TreeNode(1);
		TreeNode t2 = new TreeNode(2);
		TreeNode t3 = new TreeNode(3);
		TreeNode t4 = new TreeNode(4);
		TreeNode t5 = new TreeNode(5);
		TreeNode t6 = new TreeNode(6);
		/**t1.left = t2;
		t1.right = t3;
		t2.left = t4;
		t2.right = t5;
		t5.left = t6;**/
		t1.left = t2;
		t2.left = t3;
		t3.left = t4;
		BinaryTree test = new BinaryTree();
		System.out.println(test.getHeight(t1));
		//int min = 0;
		//Integer s = null;
		//System.out.println(min > s);
	}
	
	// Delete in BST
	public TreeNode deleteBST(TreeNode root, int target) {
		if (root == null) {
			return null;
		}
		if (root.value < target) {
			root.right = deleteBST(root.right, target);
		} else if (root.value > target) {
			root.left = deleteBST(root.left, target);
		}
		// Guarantee root != null && root.value = target
		// Case 2: root has no left child
		if (root.left == null) {
			return root.right;
		} 
		// Case 3: root has no right child
		else if (root.right == null) {
			return root.left;
		}
		// Guarantee root.left != null && root.right != null
		// Case 4: root has both left and right children
		// Case 4.1: root.right has no left child
		if (root.right.left == null) {
			root.right.left = root.left;
			return root.right;
		}
		// Case 4.2: root.right has left child
		TreeNode smallest = helperFindSmallest(root.right);
		smallest.left = root.left;
		smallest.right = root.right;
		return smallest;
	}
	private TreeNode helperFindSmallest(TreeNode cur) {
		TreeNode prev = cur;
		cur = cur.left;
		while (cur.left != null) {
			prev = cur;
			cur = cur.left;
		}
		prev.left = prev.left.right;
		return cur;
	}

	//10/5
	// 举一反三 Q3:
	/**
	 * Let's assume if we tweak the lchild with rchild of an arbitrary node in a binary 
	 * tree, then the "structure" of the tree are not changed. Then how can we determine
	 * whether two binary trees' structures are identical
	 */
	public boolean isTweakedIdentical(TreeNode one, TreeNode two) {
		if (one == null && two == null) {
			return true;
		} else if (one == null || two == null) {
			return false;
		} else if (one.value != two.value) {
			return false;
		}
		return (isTweakedIdentical(one.left, two.right) && isTweakedIdentical(one.right, two.left))
				|| (isTweakedIdentical(one.left, two.left) && isTweakedIdentical(one.right, two.right));
	}
	
	// Search in BST
	public TreeNode searchBST(TreeNode root, int target) {
		if (root == null || root.value == target) {
			return root;
		}
		if (root.value < target) {
			return searchBST(root.right, target);
		} else { //root.value > target
			return searchBST(root.left, target);
		}
	}
	
	// Insert in BST
	// Method 1: recursion
	public TreeNode insertBSTRecursive(TreeNode root, int target) {
		if (root == null) {
			TreeNode newRoot = new TreeNode(target);
			return newRoot;
		}
		if (root.value < target) {
			root.right = insertBSTRecursive(root.right, target);
		} else if (root.value > target){
			root.left = insertBSTRecursive(root.left, target);
		}
		return root;
	}
	// Method 2: iteration
	public TreeNode insertBSTIteration(TreeNode root, int target) {
		TreeNode newNode = new TreeNode(target);
		if (root == null) {
			return newNode;
		}
		TreeNode cur = root;
		while (cur.value != target) {
			if (cur.value > target) {
				if (cur.left != null) {
					cur = cur.left;
				} else {
					cur.left = newNode;
					break;
				} 
			} else { // cur.value < target
				if (cur.right != null) {
					cur = cur.right;
				} else {
					cur.right = newNode;
					break;
				}
			}
		}
		return root;
	}
	
	// How to determine a binary tree is a BST?
	public boolean isBST(TreeNode root) {
		return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	private boolean isBST(TreeNode root, int min, int max) {
		if (root == null) {
			return true;
		} 
		if (root.value <= min || root.value >= max) {
			return false;
		}
		return isBST(root.left, min, root.value) && isBST(root.right, root.value, max);
	}
	
	// Print BST keys in the given range
	public void getRange(TreeNode root, int lower, int upper) {
		if (root == null) {
			return;
		}
		if (root.value > lower) {
			getRange(root.left, lower, upper);
		}
		if (root.value >= lower && root.value <= upper) {
			System.out.println(root.value);
		}
		if (root.value < upper) {
			getRange(root.right, lower, upper);
		}
	}
	
	// Pre-order: parent -> left subtree -> right subtree
	/**
	 * Recursion: preOrder(root) Subproblem: preOrder(root.left)
	 * preOrder(root.right) recursion rule: print root.value; preOrder(root.left);
	 * preOrder(root.right) base case: null -> return
	 */
	public List<Integer> preOrder(TreeNode root) {
		// Create an arraylist
		List<Integer> result = new ArrayList<>();
		preOrder(root, result);
		return result;
	}
	private void preOrder(TreeNode root, List<Integer> result) {
		// base case
		if (root == null) {
			return;
		}
		// add parent -> go left -> go right
		result.add(root.value);
		preOrder(root.left, result);
		preOrder(root.right, result);
	}
	// Recursion
	public void preOrderRecursive(TreeNode root) {
		if (root == null) {
			return;
		}
		Deque<TreeNode> stack = new ArrayDeque<>();
		stack.offerFirst(root);
		while (!stack.isEmpty()) {
			TreeNode cur = stack.pollFirst();
			System.out.println(cur.value);
			if (cur.right != null) {
				stack.offerFirst(cur.right);
			}
			if (cur.left != null) {
				stack.offerFirst(cur.left);
			}
		}
	}

	// In-order: left subtree -> parent -> right subtree
	public List<Integer> inOrder(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		inOrder(root, result);
		return result;

	}

	private void inOrder(TreeNode root, List<Integer> result) {
		if (root == null) {
			return;
		}
		inOrder(root.left, result);
		result.add(root.value);
		inOrder(root.right, result);
	}
	// Recursion
	public void inOrderRecursion(TreeNode root) {
		if (root == null) {
			return;
		}
		Deque<TreeNode> stack = new ArrayDeque<>();
		TreeNode helper = root;
		while (helper != null || !stack.isEmpty()) {
			if (helper != null) {
				stack.offerFirst(helper);
				helper = helper.left;
			} else {
				helper = stack.pollFirst();
				System.out.println(helper.value);
				helper = helper.right;
			}
		}
	}

	// Post-order: left subtree -> right subtree -> parent
	public List<Integer> postOrder(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		postOrder(root, result);
		return result;
	}

	private void postOrder(TreeNode root, List<Integer> result) {
		if (root == null) {
			return;
		}
		postOrder(root.left, result);
		postOrder(root.right, result);
		result.add(root.value);
	}
	// Recursion:
	public void postOrderRecursion(TreeNode root) {
		// Initial check
		if (root == null) {
			return;
		}
		// Local variables
		Deque<TreeNode> stack = new ArrayDeque<>();
		TreeNode prev = null;
		stack.offerFirst(root);
		
		while (!stack.isEmpty()) {
			TreeNode current = stack.peekFirst();
			// Going down
			if (prev == null || current == prev.left || current == prev.right) {
				if (current.left != null) {
					stack.offerFirst(current.left);
				} else if (current.right != null) {
					stack.offerFirst(current.right);
				} else {
					System.out.println(current.value);
					stack.pollFirst();
				}
			}
			// From left subtree
			else if (prev == current.left) {
				if (current.right != null) {
					stack.offerFirst(current.right);
				} else {
					System.out.println(current.value);
					stack.pollFirst();
				}
			}
			// From right subtree
			else {
				System.out.println(current.value);
				stack.pollFirst();
			}
			prev = current;
		}
	}

	// Example 1: GetHeight
	/**
	 * height(root) Subproblem: height(root.left), height(root.right) recursion
	 * rule: max(left, right) + 1 base case: null -> return 0
	 */
	public int getHeight(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int leftHeight = getHeight(root.left);
		int rightHeight = getHeight(root.right);
		int current = Math.max(leftHeight, rightHeight) + 1;
		return current;
	}

	// Example 2: CountNode of the whole tree
	/**
	 * count(root) Subproblem: count(root.left), count(root.right) Recursion rule:
	 * count(root.left) + count(root.right) + 1 base case: null -> return 0
	 */
	public int countNodes(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int left = countNodes(root.left);
		int right = countNodes(root.right);
		return 1 + left + right;
	}

	// Problem 1: Check if binary tree is balanced
	/**
	 * isB(root) Subproblem: isB(root.left), isB(root.right) Recursion rule:
	 * isB(root.left) && isB(root.right) && |height(root.left) - height(root.right)|
	 * <= 1 base case: null -> return true
	 */
	// 不是最优解
	public boolean checkBalanced(TreeNode root) {
		if (root == null) {
			return true;
		}
		// Jerry老师的写法更加简单明了
		int leftHeight = getHeight(root.left);
		int rightHeight = getHeight(root.right);
		return Math.abs(leftHeight - rightHeight) <= 1 && checkBalanced(root.left) && checkBalanced(root.right);
		/**
		 * 课件上的写法： boolean left = checkBalanced(root.left); boolean right =
		 * checkBalanced(root.right);
		 * 
		 * if (!left || !right) { return false; }
		 * 
		 * // Judge if the height diff of current node is satisfied int leftHeight =
		 * getHeight(root.left); int rightHeight = getHeight(root.right); if
		 * (Math.abs(leftHeight - rightHeight) > 1) { return false; } return true;
		 **/
	}
	
	// 最优解：
	// If root is balance, return height
	// If root is not balanced, return -1
	public boolean checkBalanced2(TreeNode root) {
		return getHeightOrUnbalanced(root) >= 0;
	}
	private int getHeightOrUnbalanced(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int lh = getHeightOrUnbalanced(root.left);
		int rh = getHeightOrUnbalanced(root.right);
		if(lh < 0 || rh < 0) { // 如果左subtree不balance，或者，右subtree不balance
			return -1; //如果不balance，那就不管高度了
		}
		if (Math.abs(lh - rh) > 1) {
			return -1;
		}
		return 1 + Math.max(lh, rh);
	}
}















