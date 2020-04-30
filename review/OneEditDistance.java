package review;

public class OneEditDistance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(oneEditDistance("abc", "ac"));
	}

	/*
	 * 330. One Edit Distance
	 * 
	 * Determine if two given Strings are one edit distance.
	 * 
	 * One edit distance means you can only insert one character/delete one
	 * character/replace one character to another character in one of the two given
	 * Strings and they will become identical.
	 * 
	 * Assumptions:
	 * 
	 * The two given Strings are not null Examples:
	 * 
	 * s = "abc", t = "ab" are one edit distance since you can remove the trailing
	 * 'c' from s so that s and t are identical
	 * 
	 * s = "abc", t = "bcd" are not one edit distance
	 */
	public static boolean oneEditDistance(String source, String target) {
		if (Math.abs(source.length() - target.length()) > 1) {
			return false;
		}
		int s = 0;
		int t = 0;
		int count = 0; // Use count to record the number of differences
		while (s < source.length() && t < target.length()) {
			if (source.charAt(s) != target.charAt(t)) {
				if (count == 1) {
					return false;
				}
				count++;
				if (source.length() == target.length()) {
					s++;
					t++;
				} else if (source.length() > target.length()) {
					s++;
				} else {
					t++;
				}
			} else {
				s++;
				t++;
			}
		}
		if (s < source.length() || t < target.length()) {
			count++;
		}
		return count == 1;
	}

}
