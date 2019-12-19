package midterm;

import java.util.List;
import java.util.ArrayList;

public class AllDecode {
	
	public static void main (String args[]) {
		AllDecode s = new AllDecode();
		List<String> res = s.decode("1121");
		for (String str : res) {
			System.out.println(str);
		}
	}

	public List<String> decode(String input) {
		List<String> res = new ArrayList<>();
		if (input == null) {
			return res;
		}
		StringBuilder sb = new StringBuilder();
		dfs(0, input, sb, res);
		return res;
	}
	
	private void dfs(int index, String input, StringBuilder sb, List<String> res) {
		if (index == input.length()) {
			res.add(sb.toString());
			return;
		}
		for (int i = 1; i <= 2; i++) {
			Character ch = decodeOneCharacter(input, index, 1);
			if (ch != null) {
				sb.append(ch);
				dfs(index + i, input, sb, res);
				sb.deleteCharAt(sb.length() - 1);
			}
		}
	}
	
	private Character decodeOneCharacter(String input, int index, int offset) {
		if (index >= input.length() || index + offset > input.length()) {
			return null;
		}
		String subStr = input.substring(index, offset);
		int code = Integer.parseInt(subStr);
		if (code >= 1 && code <= 26) {
			return (char)(code + 'A' - 1);
		}
		return null;
	}
}
