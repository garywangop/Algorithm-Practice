package stringProblems;

public class CompressString {

	
	public static void main(String[] args) {
		CompressString sol = new CompressString();
		System.out.println(sol.compress("oooooooooooogggggggttttttttttuuuuuuuuuuyyyyyyyhhhhhhh"));
	}
	
	public String compress(String input) {
	
		if (input == null || input.length() == 0) {
			return null;
		}
		
		// 先把长度>=2的元素给compress了，在compress的过程中，把长度==1的元素也给记录下来，方便一会操作
		// [0, slow)是需要保留的
		int slow = 0;
		int fast = 0;
		int singleElement = 0;
		char[] arr = input.toCharArray();
		while (fast < arr.length) {
			int begin = fast;
			while (fast < arr.length && arr[begin] == arr[fast]) {
				fast += 1;
			}
			arr[slow++] = arr[begin];
			if (fast - begin >= 2) {
				int newLength = copy(arr, slow, fast - begin);
				slow += newLength;
			} else {
				singleElement += 1;
			}	
		}
		
		// 跑到这里之后，就要开始处理freq == 1的元素了
		// 从右往左的来操作
		/*
		 singleElement == 3
		 0123456789
		 a2b3cde 
		       f  s
		 此时的arr length应该是s + singleElement，f设置为s - 1，s设置为length - 1
		 (slow, length - 1]是处理过的
		 当f == s的时候就停
		 	1. arr[f]是个number: 一直做swap(arr, f--, slow--)，当arr[f]是character时，还要做一次swap(arr, f--, slow--)
		 	2. arr[f]是个character: arr[slow--] = 1, swap(arr, f, slow--)
		 
		 */
		int size = slow + singleElement;
		char[] res = new char[size];
		fast = slow - 1;
		slow = size - 1;
		while (fast >= 0) {
			if (arr[fast] >= '0' && arr[fast] <= '9') {
				while (fast >= 0 && arr[fast] >= '0' && arr[fast] <= '9') {
					res[slow--] = arr[fast--];
				}
			} else {
				res[slow--] = '1';
			}
			res[slow--] = arr[fast--];
		}
		return new String(res);
	}
	
	// input中的每个character的frequency都>=2
	public String compressLong(String input) {
		if (input == null || input.length() == 0) {
			return input;
		}
		int slow = 0;
		int fast = 0;
		char[] arr = input.toCharArray();
		// [0, slow)需要保留
		while (fast < arr.length) {
			int begin = fast;
			while (fast < arr.length && arr[fast] == arr[begin]) {
				fast += 1;
			}
			arr[slow++] = arr[begin];
			int newLength = copy(arr, slow, fast - begin);
			slow += newLength;
		}
		return new String(arr, 0, slow);
	}
	
	/*
	   01234
	   aabbcc
	   s f
	   length = 4 
	*/
	private int copy(char[] arr, int start, int length) {
		int res = 0;
		int digit = 0;
		for (int i = length; i > 0; i /= 10) {
			digit++;
			res++;
		}
		start += digit;
		while (length > 0) {
			arr[--start] = (char)(length % 10 + '0');
			length /= 10;
		}
		return res;
	}
}
