package binarySearchTree;

import java.util.*;

import solution.TreeNode;

public class Test {

	public static void main(String[] args) {
	
		
		Test sol = new Test();
		System.out.println(sol.checkInclusion("abc", "cccccba"));
		
		
	}
	

	public boolean checkInclusion(String s1, String s2) {
		if (s1.length() > s2.length()) {
			return false;
		}
		// the number of occurrences for s1
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < s1.length(); i++) {
			int freq = map.getOrDefault(s1.charAt(i), 0);
			map.put(s1.charAt(i), freq + 1);
		}
		
		// Initialization: 
		int match = 0;
		for (int i = 0; i < s1.length(); i++) {
			Integer freq = map.get(s2.charAt(i));
			if (freq != null) {
				map.put(s2.charAt(i), freq - 1);
				if (freq == 1) {
					match++;
				} else if (freq == 0) {
					match--;
				}
			}
		}
		
		if (match == map.size()) {
			return true;
		}
		
		// 用sliding window：
		// s2[i]吐掉：
		// if this letter is in the map, update the map and match. If not, do nothing
		// s2[i + s1.length()]吃进来:
		// If this letter is in the map, update the map and match. If not, do nothing
		for (int i = 0; i < s2.length() - s1.length(); i++) {
			char out = s2.charAt(i);
			char in = s2.charAt(i + s1.length());
			Integer outFreq = map.get(out);
			
			if (outFreq != null) {
				if (outFreq == 0) {
					match--;
				} else if (outFreq == -1) {
					match++;
				}
				map.put(out, outFreq + 1);
			}
			
			Integer inFreq = map.get(in);
			if (inFreq != null) {
				if (inFreq == 1) {
					match++;
				} else if (inFreq == 0) {
					match--;
				}
				map.put(in, inFreq - 1);
			}
			
			if (match == map.size()) {
				return true;
			}
		}
		
		return false;
	}
}
