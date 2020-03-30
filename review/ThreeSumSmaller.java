package review;

import java.util.Arrays;

public class ThreeSumSmaller {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (j == 2) {
					break;
				}
				System.out.println(j);
			}
		}

	}

	public static int threeSumSmaller(int[] num, int target) {
	    if (num == null || num.length <= 2) {
	      return 0;
	    }
	    int res = 0;
	    Arrays.sort(num);
	    for (int i = 0; i < num.length - 2; i++) {
	      for (int j = 1; j < num.length - 1; j++) {
	        for (int k = 2; k < num.length; k++) {
	          if (num[i] + num[j] + num[k] < target) {
	            res++;
	          } else {
	            break;
	          }
	        }
	      }
	    }
	    return res;
	  }
}
