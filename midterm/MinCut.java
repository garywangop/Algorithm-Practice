package midterm;

public class MinCut {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MinCut sol = new MinCut();
		System.out.println(sol.minCut("aba"));
	}
	
	public int minCut(String s) {
		if (s == null || s.length() <= 1) {
			return 0;
		}
		
		// m[i] represents the minimum cuts for the first i letters
		int[] m = new int[s.length() + 1];
		m[0] = 0;
		m[1] = 0;
		/*
		for (int i = 1; i <= s.length(); i++) {
			m[i] = Integer.MAX_VALUE;
			
			if (palindrome(s, 0, i - 1)) {
				m[i] = 0;
				continue;
			}
			
			for (int j = 1; j < i; j++) {
				if (palindrome(s, j, i - 1)) {
					m[i] = Math.min(m[i], m[j] + 1);
				}
			}
		}
		*/
		for (int i = 1; i <= s.length(); i++) {
		    m[i] = Integer.MAX_VALUE;
		    for (int j = 0; j < i; j++) {
		      if (palindrome(s, j, i - 1) && m[j] + 1 < m[i]) {
		        m[i] = m[j] + 1;
		      }
		    }
		  }
		return m[s.length()];
	}

	private boolean palindrome(String s, int left, int right) {
		while (left < right) {
			if (s.charAt(left) != s.charAt(right)) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	}
}
