package midterm;

import java.util.*;

public class Summer3Final {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> list = Summer3Final.recuritingEvent("ABC");
		for (String s : list) {
			System.out.println(s);
		}
		
		System.out.println(Summer3Final.minSwags(18));
	}

	/*
	 * Q1: Recuriting Event Schedules
	 * 
	 * Our company is organizing a series of university recruiting events. Each day,
	 * we host an event at one university, but sometimes we want to take a break for
	 * one day before moving on to the next university.
	 * 
	 * Given a sequence of universities, print all possible schedules of the
	 * recruiting events.
	 * 
	 * input: a string of universities. Each university is represented as a single
	 * capital letter.
	 * 
	 * Output: all possible schedules. A lowercase letter "x" means we take a break.
	 * 
	 * Example: input: String = "ABC" Output: ABC ABxC AxBC AxBxC
	 */

	public static List<String> recuritingEvent(String s) {
		List<String> res = new ArrayList<>();
		if (s == null || s.length() == 0) {
			return res;
		}

		helper(s, 0, new StringBuilder(), res);
		return res;
	}
	// ABC

	private static void helper(String s, int index, StringBuilder sb, List<String> res) {
		if (index >= s.length() - 1) {
			res.add(sb.toString());
			return;
		}

		int size = sb.length();
		
		// Don't add x at current level
		sb.append(s.charAt(index));
		helper(s, index + 1, sb, res);
		sb.setLength(size);

		// Add x at current level
		sb.append(s.charAt(index)).append('x');
		helper(s, index + 1, sb, res);
		sb.setLength(size);
		
	}

	/*
	 * Q2: Cousins in a Binary Tree
	 * 
	 * In a binary tree, two nodes are cousins of each other if they are at the same
	 * level and have different parents.
	 * 
	 * For example, in the following tree:
	 * 
	 * 
	 * 				9
	 * 			   /\
	 * 			  6  10
	 * 			 /\
	 * 			3  5
	 * 		   /\  /\
	 * 		  7 8  1 2
	 * 
	 * 7 and 1 are cousins. 3 and 5 are not cousins. 7 and 5 are not cousins.
	 * 
	 * Given a binary tree and two nodes, determine if the two nodes are cousins or
	 * not.
	 * 
	 */
	
	public static boolean isCousin(TreeNode root, TreeNode one, TreeNode two) {
		
	}
	
	/*
	 * Q3: Packing Up the Swags
	 * 把一个数拆成平方和，用的数越少好，并求这个数是多少
	 * 比如：
	 * 4： output是1，因为是2的平方
	 * 10：ouput是2，因为是3平方加1平方（也能拆成2个2平方和2个1平方，这样就是4，不是最小）
	 */
	public static int minSwags(int num) {
		if (num <= 0) {
			return 0;
		}
		// m[i] represents the minimum boxes need for i swags
		int[] m = new int[num + 1];
		m[0] = 0; 
		m[1] = 1;
		for (int i = 2; i < m.length; i++) {
			if (hasSquareRoot(i)) {
				m[i] = 1;
			} else {
				m[i] = i;
			}
			
			for (int j = 1; j < i; j++) {
				m[i] = Math.min(m[i], m[j] + m[i - j]);
			}
		}
		return m[num];
	}
	
	private static boolean hasSquareRoot(int num) {
		for (int i = 2; i * i <= num; i++) {
			if (i * i == num) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Q4: Infinite Loop Around the Dinner Table
	 * 给一个String[]的名字，问所有人的名字能不能组成一个环
	 * 怎么连接名字？一个名字的尾字母要等于下一个名字的首字母
	 * 比如ALICE, CHARLES, ERIC, SOPHIA就可以组成一个环
	 * ALICE -> ERIC -> CHARLES -> SOPHIA
	 *    <--------------------------
	 */
	public boolean isLoop(String[] names) {
		if (names == null || names.length <= 1) {
			return false;
		}
		
	}
}
