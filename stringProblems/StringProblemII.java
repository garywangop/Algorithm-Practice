package stringProblems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class StringProblemII {
	public static void main (String[] args) {
		StringProblemII test = new StringProblemII();
		String input = "an apple";
		System.out.println(test.rightShift("abcdefg",39));
		int[][] matrix = new int[][] {{1, 2, 3}, {4, 5, 6}};
		System.out.println(matrix.length);
		System.out.println(matrix[0].length);
	}
	// Q1: Reverse a string: iterative reverse
	public String reverse(String input) {
		if (input.length() <= 1) {
			return input;
		}
		char[] arr = input.toCharArray();
		int left = 0, right = arr.length - 1;
		while (left < right) {
			char temp = arr[left];
			arr[left] = arr[right];
			arr[right] = temp;
			left++;
			right--;
		}
		return new String(arr);
	}
	
	// Recursive reverse
	public String reverse2(String input) {
		if (input.length() <= 1) {
			return input;
		}
		char[] arr = input.toCharArray();
		helper(arr, 0, arr.length - 1);
		return new String(arr);
	}
	private void helper(char[] arr, int left, int right) {
		if (left >= right) {
			return;
		}
		
		char temp = arr[left];
		arr[left] = arr[right];
		arr[right] = temp;
		helper(arr, left + 1, right - 1);
	}
	
	// Q2: Reverse Words In A Sentence I
	// 先全反过来，然后一个个单词反
	public String reverseWords(String input) {
	    if (input == null || input.length() <= 1) {
	    	return input;
	    }
	    char[] arr = input.toCharArray();
	    helperQ2(arr, 0, arr.length - 1);
	    int i = 0, j = 0;
	    while (i < arr.length) {
	    	if (arr[i] == ' ') {
	    		if (j == 0) {
	    			helperQ2(arr, 0, i - 1);
	    		} else {
	    			helperQ2(arr, j + 1, i - 1);
	    		}
	    		j = i;
	    	}
	    	i++;
	    }
	    helperQ2(arr, j + 1, i - 1);
	    return new String(arr);
	    
	}
	private void helperQ2(char[] arr, int left, int right) {
		while (left < right) {
			char temp = arr[left];
			arr[left] = arr[right];
			arr[right] = temp;
			left++;
			right--;
		}
	}
	
	// String Replace (basic)
	public String replace(String input, String source, String target) {
		char[] arr = input.toCharArray();
		if (source.length() >= target.length()) {
			return replaceShort(arr, source, target);
		}
		return replaceLong(arr, source, target);
	}
	private String replaceShort(char[] arr, String source, String target) {
		int slow = 0, fast = 0;
		while (fast < arr.length) {
			if (fast <= arr.length - source.length() && checkSub(arr, fast, source)) {
				copyValue(arr, slow, target);
				slow += target.length();
				fast += source.length();
			} else {
				arr[slow] = arr[fast];
				slow++;
				fast++;
			}
		}
		return new String(arr, 0, slow);
	}
	private String replaceLong(char[] arr, String source, String target) {
		List<Integer> match = getLastIndex(arr, source);
		char[] res = new char[arr.length + match.size() * (target.length() - source.length())];
		int lastIndex = match.size() - 1;
		int slow = res.length - 1, fast = arr.length - 1;
		while (fast >= 0) {
			if (lastIndex >= 0 && fast == match.get(lastIndex)) {
				copyValue(res, slow - target.length() + 1, target);
				slow -= target.length();
				fast -= source.length();
				lastIndex--;
			} else {
				res[slow] = arr[fast];
				slow--;
				fast--;
			}
		}
		return new String(res);
	}
	private boolean checkSub(char[] arr, int i, String source) {
		for (int j = 0; j < source.length(); j++) {
			if (arr[i + j] != source.charAt(j)) {
				return false;
			}
		}
		return true;
	}
	private void copyValue(char[] arr, int start, String target) {
		for (int i = 0; i < target.length(); i++) {
			arr[start + i] = target.charAt(i);
		}
	}
	private List<Integer> getLastIndex(char[] arr, String source) {
		List<Integer> match = new ArrayList<>();
		for (int i = 0; i <= arr.length - source.length(); i++) {
			if (checkSub(arr, i, source)) {
				match.add(i + source.length() - 1);
				i += source.length() - 1;
			} 
		}
		return match;
	}
	// All Permutations II
	// Set = "aba", all permutations are ["aab", "aba", "baa"]
	 public List<String> permutationsII(String input) {
		 List<String> res = new ArrayList<>();
		 if (input == null) {
			 return res;
		 }
		 char[] arr = input.toCharArray();
		 helperPermutationsII(arr, 0, res);
		 return res;
	 }
	 private void helperPermutationsII(char[] arr, int index, List<String> res) {
		 if (arr.length == index) {
			 res.add(new String(arr));
			 return;
		 }
		 HashSet<Character> check = new HashSet<>();
		 for (int i = index; i < arr.length; i++) {
			 if (check.add(arr[i])) {
				 swap(arr, i, index);
				 helperPermutationsII(arr, index + 1, res);
				 swap(arr, i, index);
			 }
		 }
	 }
	 private void swap(char[] arr, int a, int b) {
		 char temp = arr[a];
		 arr[a] = arr[b];
		 arr[b] = temp;
	 }
	 // Right Shift By N Characters
	 // The given string is not null.
	 // n >= 0.
	 //"abc", 4 -> "cab"
	 public String rightShift(String input, int n) {
		    if (input == null || input.length() <= 1) {
		      return input;
		    }
		    char[] arr = input.toCharArray();
		    int shift = n % input.length();
		    helperQ2(arr, 0, input.length() - shift - 1);
		    helperQ2(arr, input.length() - shift, input.length() - 1);
		    helperQ2(arr, 0, input.length() - 1);
		    return new String(arr);
		  }
		  
}
