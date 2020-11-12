package leetcode;

import java.util.*;

public class CheckInclusion {
	public static void main(String[] args) {
		CheckInclusion sol = new CheckInclusion();
		System.out.println(sol.checkInclusion2("ab", "eidbaooo"));
	}
	// Method 1: DFS
	public boolean checkInclusion1(String s1, String s2) {
        // 1. get the all the permutations for s1 and store them in hashset
        // 2. Use sliding window to check all the substring in s2
        Set<String> set = permutation(0, s1.toCharArray(), new HashSet<>());
        for (int i = 0; i <= s2.length() - s1.length(); i++) {
            if (set.contains(s2.substring(i, i + s1.length()))) {
                return true;
            }
        }
        return false;
    }
    
    private Set<String> permutation(int index, char[] arr, Set<String> set) {
        if (index == arr.length) {
            set.add(new String(arr));
            return set;
        }
        
        for (int i = index; i < arr.length; i++) {
            swap(arr, i, index);
            permutation(index + 1, arr, set);
            swap(arr, i, index);
        }
        
        return set;
    }
    
    private void swap(char[] arr, int a, int b) {
        char temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    
    // Method 2: use int[]
    public boolean checkInclusion2(String s1, String s2) {
    	if (s1.length() > s2.length()) {
    		return false;
    	}
    	int[] arr = new int[26];
    	for (int i = 0; i < s1.length(); i++) {
    		arr[s1.charAt(i) - 'a']++;
    	}
    	
    	// Initialization: 
    	for (int i = 0; i < s1.length(); i++) {
    		arr[s2.charAt(i) - 'a']--;
    	}
    	
    	if (allZero(arr)) {
    		return true;
    	}
    	// s1: ab
    	// s2: abcd
    	for (int i = 0; i < s2.length() - s1.length(); i++) {
    		// remove s1.charAt(i)
    		arr[s2.charAt(i) - 'a']++;
    		// add s1.charAt(i + s1.length())
    		arr[s2.charAt(i + s1.length()) - 'a']--;
    		if (allZero(arr)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    private boolean allZero(int[] arr) {
    	for (int i = 0; i < arr.length; i++) {
    		if (arr[i] != 0) {
    			return false;
    		}
    	}
    	return true;
    }
	
	
	// Method 3: Use hashmap
	public boolean checkInclusion3(String s1, String s2) {
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
