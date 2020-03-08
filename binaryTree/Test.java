package binaryTree;
import solution.TreeNode;
import java.util.List;
import java.util.ArrayList;

public class Test {
	
	public static void main(String args[]) {
		String s = "aba";
		Test test = new Test();
		
		System.out.println(-1%2);
}

	
	public boolean allUnique(String s) {
		if (s == null || s.length() <= 1) {
			return true;
		}
		int letter = 0;
		for (int i = 0; i < s.length(); i++) {
			int k = s.charAt(i) - 'a';
			if (((letter >> k) & 1) == 1) {
				System.out.println("In if statement" + letter);
				return false;
			}
			System.out.println("Before, letter is: " + letter);
			letter |= (1 <<k);
			System.out.println("After, letter is: " + letter);
		}
		return true;
	}
}
