package dp;

import java.util.*;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> wordDict = new ArrayList<>();
		wordDict.add("leet");
		wordDict.add("code");
		System.out.println(Test.wordBreak("leetcode", wordDict));
	}
	
	public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = getDict(wordDict);
        
        // m[i] represents whether the first i letters can be segmented
        boolean[] m = new boolean[s.length() + 1];
        m[0] = true;
        for (int i = 1; i <= s.length(); i++) {
           for (int j = 0; j < i; j++) {
                if (m[j] && set.contains(s.substring(j, i))) {
                    m[i] = true;
                    break;
                }
            }
        }
        return m[s.length()];
        
    }
    
    private static Set<String> getDict(List<String> list) {
        Set<String> set = new HashSet<>();
        for (String s : list) {
            set.add(s);
        }
        return set;
    }

}
