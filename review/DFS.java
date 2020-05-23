package review;

import java.util.List;
import java.util.ArrayList;

public class DFS {

	public static void main(String[] args) {
		DFS sol = new DFS();
		int[] arr = new int[] {2,3,6,7};
		System.out.println(sol.combinationSum(arr, 7));
	}
	
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        helper(0, target, candidates, cur, res);
        return res;
    }
    
    private void helper(int index, int target, int[] arr, List<Integer> cur, List<List<Integer>> res) {
    	if (target == 0) {
            res.add(new ArrayList<>(cur));
            return;
        }
    	
    	if (target < 0 || index == arr.length) {
            return;
        }
        
        int size = target / arr[index];
        for (int i = 0; i <= size; i++) {
            for (int j = 0; j < i; j++) {
            	cur.add(arr[index]);
            }
            helper(index + 1, target - i * arr[index], arr, cur, res);
            while (cur.size() > 0 && cur.get(cur.size() - 1) == arr[index]) {
            	cur.remove(cur.size() - 1);
            }
        }
    }
}
