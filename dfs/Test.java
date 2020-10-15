package dfs;

import java.util.*;

public class Test {

	public static void main(String[] args) {
		Test sol = new Test();
		String[] res = sol.combinations(5002);
		for (String s : res) {
			System.out.println(s);	
		}
		
	}

	public List<String> Restore(String ip) {
		List<String> res = new ArrayList<>();
		if (ip == null) {
			return res;
		}
		helper(ip, 0, 0, new StringBuilder(), res);
		return res;
	}
	
	private void helper(String ip, int index, int count, StringBuilder sb, List<String> res) {
		if (index == ip.length() && count == 4) {
			res.add(sb.toString());
			return;
		} else if (index == ip.length() || count == 4) {
			return;
		}
		
		int size = sb.length();
		for (int i = index; i < index + 3; i++) {
			if (i < ip.length()) {
				sb.append(ip.charAt(i)).append('.');
				helper(ip, index + 1, count + 1, sb, res);
				sb.setLength(size);
			}
		}
	}
}
