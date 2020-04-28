package review;

import java.util.HashSet;
import java.util.Set;

public class StringProblems {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(lengthOfLongestSubstring("pwwkew"));

	}

	public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Set<Character> set = new HashSet<>();
        set.add(s.charAt(0));
        int slow = 0;
        int res = 1;
        for (int i = 1; i < s.length(); i++) {
            if (set.add(s.charAt(i))) {
                res = Math.max(res, i - slow + 1);
            } else {
                while (!set.add(s.charAt(i))) {
                    set.remove(s.charAt(slow));
                    slow++;
                }
            }
        }
        return res;
    }
}
