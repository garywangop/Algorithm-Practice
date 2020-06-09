package review;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfusingNumber {

	public static void main(String[] args) {
		ConfusingNumber sol = new ConfusingNumber();
		System.out.println(sol.confusing(100));
		System.out.println(sol.confusingDFS(100));
	}

	/*
	 * Some of the digits can be rotated by 180 degrees to form new digits. e.g.
	 * When 0, 1, 6, 8, 9 are rotated 180 degrees, they become 0, 1, 9, 8, 6
	 * respectively. When 2, 3, 4, 5 and 7 are rotated 180 degrees, they become
	 * invalid.
	 * 
	 * 
	 * A confusing number is a number that when rotated 180 degrees becomes a
	 * different number with each digit valid.(Note that the rotated number can be
	 * greater than the original number.) 10 → 01
	 * 
	 * 
	 * 106 → 901 confusing number 
	 * 101 → 101 not confusing 
	 * 102 not valid
	 * 
	 * Given a positive integer N, return all the confusing numbers between 1 and N
	 * inclusive.
	 * 
	 */

	public List<Integer> confusing(int n) {
		List<Integer> res = new ArrayList<>();
		Map<Integer, Integer> map = confusingMap();
		for (int i = 6; i <= n; i++) {
			if (checkConfusing(i, map)) {
				res.add(i);
			}
		}
		return res;
	}

	private Map<Integer, Integer> confusingMap() {
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, 0);
		map.put(1, 1);
		map.put(6, 9);
		map.put(8, 8);
		map.put(9, 6);
		return map;
	}

	private boolean checkConfusing(int n, Map<Integer, Integer> map) {
		int temp = n;
		int res = 0;
		while (temp > 0) {
			Integer digit = map.get(temp % 10);
			if (digit == null) {
				return false;
			}
			temp /= 10;
			res = res * 10 + digit;
		}
		return res != n;
	}

	public List<Integer> confusingDFS(int n) {
		Map<Integer, Integer> map = confusingMap();
		List<Integer> res = new ArrayList<>();
		for (Integer i : map.keySet()) {
			if (i != 0) {
				dfs(n, i, map, res);
			}
		}
		return res;
	}

	private void dfs(int target, int cur, Map<Integer, Integer> map, List<Integer> res) {
		if (cur > target) {
			return;
		}

		if (checkConfusing(cur, map)) {
			res.add(cur);
		}

		for (Integer i : map.keySet()) {
			dfs(target, cur * 10 + i, map, res);
		}
	}

}
