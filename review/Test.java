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
		
		System.out.println(sol.combinationSum2(new int[] {2, 2,2}, 2));
	}
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        Arrays.sort(candidates);
        helper(candidates, 0, target, cur, res);
        return res;
    }
	
	private void helper(int[] arr, int index, int target, List<Integer> cur, List<List<Integer>> res) {
		if (target == 0) {
            res.add(new ArrayList<>(cur));
            return;
        }
        
        if (target < 0 || index == arr.length) {
            return;
        }
        
        // Add arr[index]
        cur.add(arr[index]);
        helper(arr, index + 1, target - arr[index], cur, res);
        cur.remove(cur.size() - 1);
        
        // Don't add arr[index]
        while (index < arr.length - 1 && arr[index] == arr[index + 1]) {
            index++;
        }
        helper(arr, index + 1, target, cur, res);
    }

}
