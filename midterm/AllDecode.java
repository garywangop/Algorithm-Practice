package midterm;

import java.util.List;
import java.util.ArrayList;

public class AllDecode {
	
	public static void main (String args[]) {
		AllDecode s = new AllDecode();
		List<String> res = s.decode("1121");
		
		for (int i = 0; i < res.size(); i++) {
			System.out.println(res.get(i));
		}
	}

	public List<String> decode(String input) {
		List<String> res = new ArrayList<>();
		if (input == null) {
			return res;
		}
		StringBuilder sb = new StringBuilder();
		dfs(input, 0, sb, res);
		return res;
	}
	
	private void dfs(String input, int index, StringBuilder sb, List<String> res) {
		if (index == input.length()) {
			res.add(sb.toString());
		}
		
		for (int i = 1; i <= 2; i++) {
			Character str = decode(input, index, i);
			if (str != null) {
				sb.append(str);
				dfs(input, index + i, sb, res);
				sb.deleteCharAt(sb.length() - 1);
			}
		}
	}
	
	private Character decode(String input, int index, int i) {
		if (index >= input.length() || index + i > input.length()) {
			return null;
		}
		String cur = input.substring(index, index + i);
		int offset = 0;
		for (int j = 0; j < cur.length(); j++) {
			offset = 10 * offset + (cur.charAt(j) - '0');
		}
		if (offset >= 1 && offset <= 26) {
			return (char)('A' + offset - 1);
		}
		return null;
	}

}
