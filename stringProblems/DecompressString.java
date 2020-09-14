package stringProblems;

public class DecompressString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DecompressString sol = new DecompressString();
		System.out.println(sol.decompress2("a1c0b2c4"));
	}
	
	// Method 2
	public String decompress2(String input) {
		if (input == null || input.length() == 0) {
			return input;
		}
		
		int size = 0;
		for (int i = 0; i < input.length(); i++) {
			if (Character.isDigit(input.charAt(i))) {
				size += (int) (input.charAt(i) - '0');
			}
		}
		
		char[] res = new char[size];
		// i: index of res
		// j: index of input
		int i = 0;
		int j = 0;
		while (j < input.length()) {
			// 在这个while循环里，每次进循环input.charAt(j)一定是一个character，
			// 所以我只需要看一下j + 1的数字是多少，然后把j上的这个元素copy到res里去就行了
			// 题目里说 There are no adjacent repeated characters with length > 9
			// 所以我可以放心大胆的每次在while循环里让j跳2次
			if (input.charAt(j + 1) != '0') {
				char cur = input.charAt(j);
				int repeat = input.charAt(j + 1) - '0';
				while (repeat-- > 0) {
					res[i++] = cur;
				}
			}
			j += 2;
		}
		return new String(res);
	}

	
	// Method1
	public String decompress1(String input) {
	    // Method 1: "in place"
	    // When we say in place, it usually means the input is a long enough char array, and 
	    // the original string only occupies part of the array starting from index 0, and
	    // usually the length to represent the original string is given
	    if (input.isEmpty()) {
	      return input;
	    }
	    char[] array = input.toCharArray();
	    // We need to handle the 
	    // "a0", "a1", "a2" case (the decoded string is shorter) and 
	    // "a3", "a4" ... case (the decoded string is longer)
	    // in two pass to avoid conflict, since the encoding of the two cases
	    // required different directions
	    return decodeLong(array, decodeShort(array));
	  }
	  // Return the length of the decoded string,
	  // only cares about "a0", "a1", "a2", A.K.A
	  // the decoded string is shorter
	  private int decodeShort(char[] input) {
	    // Since the decoded string is shorter, we should do the decoding work from left 
	    // to right direction
	    int end = 0;
	    for (int i = 0; i < input.length; i+=2) {
	      int digit = getDigit(input[i + 1]);
	      if (digit >= 0 && digit <= 2) {
	        for (int j = 0; j < digit; j++) {
	          input[end++] = input[i];
	        }
	      } else {
	        // we don't handle the longer decoded string here
	        input[end++] = input[i];
	        input[end++] = input[i + 1];
	      }
	    }
	    return end;
	  }
	  // Take care of "a3", "a4", "a5", ...
	  // the decoded string is longer.
	  // length: the length of the valid partition starting from index -
	  private String decodeLong(char[] input, int length) {
	    // we need to calculate the new required length
	    int newLength = length;
	    for (int i = 0; i < length; i++) {
	      int digit = getDigit(input[i]);
	      if (digit > 2 && digit <= 9) {
	        newLength += digit - 2;
	      }
	    }
	    // Notice: if it is required to do this in place, usually the input array is a 
	    // sufficient large one, you will not need to allocate a new array. This 
	    // solution is only for demonstration.
	    char[] result = new char[newLength];
	    int end = newLength - 1;
	    for (int i = length - 1; i >= 0; i--) {
	      int digit = getDigit(input[i]);
	      if (digit > 2 && digit <= 9) {
	        i--;
	        for (int j = 0; j < digit; j++) {
	          result[end--] = input[i];
	        }
	      } else {
	        // We already take care the shorter cases, "a1" in previous pass.
	        // We can leave as it is. e.g. the input could be "abc2"
	        result[end--] = input[i];
	      }
	    }
	    return new String(result);
	  }
	  private int getDigit(char digit) {
	    return digit - '0';
	  }
}
