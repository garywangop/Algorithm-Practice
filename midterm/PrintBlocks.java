package midterm;

import java.util.*;

public class PrintBlocks {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PrintBlocks sol = new PrintBlocks();
		List<String> test = sol.printBlocks(3);
		for (String s : test) {
			System.out.println(s);
		}
	}
	
	public List<String> printBlocks(int n) {
		List<String> res = new ArrayList<>();
		if (n <= 0) {
			return res;
		}
		
		dfs(n, 0, 0, new StringBuilder(), res);
		print(res);
		return res;
	}
	
	private void dfs(int n, int left, int right, StringBuilder sb, List<String> res) {
		if (left == n && right == n) {
			res.add(sb.toString());
			return;
		}
		
		if (left < n) {
			sb.append('{');
			dfs(n, left + 1, right, sb, res);
			sb.deleteCharAt(sb.length() - 1);
		}
		
		if (right < left) {
			sb.append('}');
			dfs(n, left, right + 1, sb, res);
			sb.deleteCharAt(sb.length() - 1);
		}
	}
	
	private void print(List<String> input) {
		int indentation = 0;
		for (String s : input) {
			for (int i = 0; i < s.length(); i++) {
				
				if (s.charAt(i) == '{') {
					printSpace(indentation);
					System.out.println("if {");
					indentation += 2;
				} else {
					indentation -=2;
					printSpace(indentation);
					System.out.println("}");
				}
			}
			System.out.println();
		}
		
	}
	
	private void printSpace(int indentation) {
		while (indentation > 0) {
			System.out.print(' ');
			indentation--;
		}
	}
}
