package review;

public class ReplacementAB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(minReplacements("abaab"));
	}

	public static int minReplacements(String input) {
	    if (input == null || input.length() <= 1) return 0;
	    int[] bCount = new int[input.length()];
	    for (int i = 1; i < bCount.length; i++) {
	      if (input.charAt(i - 1) == 'b') {
	        bCount[i] = bCount[i - 1] + 1;
	      } else {
	        bCount[i] = bCount[i - 1];
	      }
	    }
	    int[] aCount = new int[input.length()];
	    for (int i = aCount.length - 2; i >= 0; i--) {
	      if (input.charAt(i + 1) == 'a') {
	        aCount[i] = aCount[i + 1] + 1;
	      } else {
	        aCount[i] = aCount[i + 1];
	      }
	    }
	    int min = Integer.MAX_VALUE;
	    for (int i = 0; i < input.length(); i++) {
	      min = Math.min(min, aCount[i] + bCount[i]);
	    }
	    return min;
	  }
	
}
