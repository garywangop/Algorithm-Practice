package dfs;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		String[] res = combinations(231);
		for (String i : res) {
			System.out.println(i);
		}

	}
	
	 private static final String[] phone = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
  
	 public static String[] combinations(int number) {
		 List<String> res = new ArrayList<>();
		 helper(Integer.toString(number).toCharArray(), 0, new StringBuilder(), res);
		 return res.toArray(new String[0]);
	  }
	 
	 private static void helper(char[] arr, int index, StringBuilder sb, List<String> res) {
		 if (index == arr.length) {
			 res.add(sb.toString());
			 return;
		 }
		 
		 String cur = phone[arr[index] - '0'];
		 if (cur.length() == 0) {
			 helper(arr, index + 1, sb, res);
		 } else {
			 for (int i = 0; i < cur.length(); i++) {
				 char ch = cur.charAt(i);
				 sb.append(ch);
				 helper(arr, index + 1, sb, res);
				 sb.deleteCharAt(sb.length() - 1);
			 }
		 }
	 }

}
