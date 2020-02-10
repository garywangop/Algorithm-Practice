package binaryTree;
import solution.TreeNode;
import java.util.List;
import java.util.ArrayList;

public class Test {
	
	public static void main(String args[]) {
		TreeNode t1 = new TreeNode(1);
		TreeNode t2 = new TreeNode(2);
		TreeNode t3 = new TreeNode(3);
		TreeNode t4 = new TreeNode(4);
		TreeNode t5 = new TreeNode(5);
		TreeNode t6 = new TreeNode(6);
		TreeNode t7 = new TreeNode(7);
		TreeNode t8 = new TreeNode(5);
		t1.left = t2;
		t1.right = t3;
		t2.left = t4;
		t3.left = t5;
		t3.right = t6;
		t4.right = t7;
		t5.left = t8;
		Test test = new Test();
		System.out.print(test.pathSum(t1, 14));
	}

	
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        List<Integer> cur = new ArrayList<>();
        helper(root, sum, cur, res);
        return res;
    }
    private void helper(TreeNode root, int sum, List<Integer> cur, List<List<Integer>> res) {
        if (root == null) {
            return;
        }
        cur.add(root.value);
        if (root.left == null && root.right == null && sum == root.value) {
        	res.add(new ArrayList<>(cur));
        } else {
        	helper(root.left, sum - root.value, cur, res);
            helper(root.right, sum - root.value, cur, res);
        }
        cur.remove(cur.size() - 1);
        
        
    }
}
