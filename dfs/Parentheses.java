package dfs;

import java.util.*;

public class Parentheses {

	public static void main(String[] args) {
		Parentheses sol = new Parentheses();
		List<String> res = sol.validParenthesesIII(1, 1, 0);
		for (String s : res) {
			System.out.println(s);
		}

	}
	
	// Laicode: 642
	static final char[] ch = new char[] {'(', ')', '<', '>', '{', '}'};
	// Parentheses with priority
	// '{' > '<' > '('
	public List<String> validParenthesesIII(int l, int m, int n) {
		List<String> res = new ArrayList<>();
		int[] remain = new int[] {l ,l, m, m, n, n};
		int total = 2 * (l + m + n);
		Deque<Integer> stack = new ArrayDeque<>();
		helper(remain, total, new StringBuilder(), stack, res);
		return res;
	}
	
	private void helper(int[] remain, int total, StringBuilder sb, Deque<Integer> stack, List<String> res) {
		if (total == 0) {
			res.add(sb.toString());
			return;
		}
		
		for (int i = 0; i < ch.length; i++) {
			if (i % 2 == 0) {
				if (remain[i] != 0 && (stack.isEmpty() || stack.peekFirst() > i)) {
					stack.offerFirst(i);
					remain[i]--;
					sb.append(ch[i]);
					helper(remain, total - 1, sb, stack, res);
					stack.pollFirst();
					remain[i]++;
					sb.deleteCharAt(sb.length() - 1);
				}
			} else {
				if (remain[i] != 0 && !stack.isEmpty() && stack.peekFirst() == i - 1) {
					stack.pollFirst();
					remain[i]--;
					sb.append(ch[i]);
					helper(remain, total - 1, sb, stack, res);
					stack.offerFirst(i - 1);
					remain[i]++;
					sb.deleteCharAt(sb.length() - 1);
				}
			}
		}
	}
	
}
