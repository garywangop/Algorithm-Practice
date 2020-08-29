package binarySearchTree;

import solution.TreeNode;

public class Test {

	public static void main(String[] args) {
		
		Integer[] arr = new Integer[] {1,2,null,4,5,6,7};
		Tree test = new TreeImpl();
		test.print(test.create(arr));
//		IsBST sol1 = new IsBST();
//		SmallestLarger sol = new SmallestLarger();
		//CloestToTarget sol = new CloestToTarget();
		
		/*
		 *      23
		 *      /\
		 *    13  31
		 *    /    \
		 *   8      27
		 *  /\      /\
		   3  12   25 29
		  /\   /
		 1  4  9
		     \  \
		      5  11
		 */
		TreeNode node1 = new TreeNode(23);
		TreeNode node2 = new TreeNode(13);
		TreeNode node3 = new TreeNode(31);
		TreeNode node4 = new TreeNode(8);
		TreeNode node5 = new TreeNode(27);
		TreeNode node6 = new TreeNode(3);
		TreeNode node7 = new TreeNode(12);
		TreeNode node8 = new TreeNode(25);
		TreeNode node9 = new TreeNode(29);
		TreeNode node10 = new TreeNode(1);
		TreeNode node11 = new TreeNode(4);
		TreeNode node12 = new TreeNode(9);
		TreeNode node13 = new TreeNode(5);
		TreeNode node14 = new TreeNode(11);
		node1.left = node2;
		node1.right = node3;
		node2.left = node4;
		node3.left = node5;
		node4.left = node6; 
		node4.right = node7;
		node5.left = node8;
		node5.right = node9;
		node6.left = node10;
		node6.right = node11;
		node7.left = node12;
		node11.right = node13;
		node12.right = node14;
//		System.out.println(sol1.isBST(node1));
//		System.out.println(sol.smallestLarger(node1, 9));
		//System.out.println(sol.cloest(node1, 10));
		
		/*
		 *  10
		    / \
		   5  15
		  / \   \ 
		 1   8   7
		 */
		
//		IsBST sol = new IsBST();
//		TreeNode node1 = new TreeNode(10);
//		TreeNode node2 = new TreeNode(5);
//		TreeNode node3 = new TreeNode(15);
//		TreeNode node4 = new TreeNode(1);
//		TreeNode node5 = new TreeNode(8);
//		TreeNode node6 = new TreeNode(16);
//		node1.left = node2;
//		node1.right = node3;
//		node2.left = node4;
//		node2.right = node5;
//		node3.right = node6;
//		
//		TreeNode node7 = new TreeNode(10);
//		TreeNode node8 = new TreeNode(5);
//		TreeNode node9 = new TreeNode(15);
//		node7.left = node8;
//		node7.right = node9;
//		
//		System.out.println(sol.isBST(node1));
	}

}
