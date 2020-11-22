package leetcode;

import java.util.*;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "[[47,220],[65,1],[98,113],[126,196],[186,218],[320,205],[686,317],[707,325],[754,104],[781,105]]";
		String res = Test.sss(s);
		System.out.println(res);
		
		Set<String> set = new HashSet<>();
		set.add("abc");
		StringBuilder sb = new StringBuilder();
		sb.append("abc");
		System.out.println(sb);
		System.out.println(set.contains(sb.toString()));
		int[] arr = new int[] {1,2,3};
		int[] prev = new int[] {4,5,6};
		prev = arr;
		for (int i : prev) {
			System.out.println(i);
		}
	}
	
	public static String sss(String s) {
		char[] arr = s.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == '[') {
				arr[i] = '{';
			} else if (arr[i] == ']') {
				arr[i] = '}';
			}
		}
		return new String(arr);
	}
}
