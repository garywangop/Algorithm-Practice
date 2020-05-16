package review;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import solution.ListNode;
import solution.TreeNode;

public class Test {

	public static void main(String[] args) {
		Test sol = new Test();
		/*
		 * {{{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}}}
		 */
		//String{}{} test = new String{}{} {{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}};
		
		TreeNode node1 = new TreeNode(1);
		TreeNode node3 = new TreeNode(1);
		TreeNode node5 = new TreeNode(1);
		TreeNode node7 = new TreeNode(1);
		TreeNode node2 = new TreeNode(0);
		TreeNode node4 = new TreeNode(0);
		TreeNode node6 = new TreeNode(0);
		node1.left = node2;
		node1.right = node3;
		node2.left = node4;
		node2.right = node5;
		node3.left = node6;
		node3.right = node7;
		System.out.println(sol.sumRootToLeaf(node1));
		
		
	}
	
	int res = 0;
    public int sumRootToLeaf(TreeNode root) {
        int sum = 0;
        helper(root, sum);
        return res;
    }
    
    private void helper(TreeNode root, int sum) {
        if (root == null) {
            return;
        }
        sum = (sum << 1) + root.value;
        if (root.left == null && root.right == null) {
            res += sum;
            return;
        }
        helper(root.left, sum);
        helper(root.right, sum);
    }

}
