package leetcode;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "[[47,220],[65,1],[98,113],[126,196],[186,218],[320,205],[686,317],[707,325],[754,104],[781,105]]";
		String res = Test.sss(s);
		System.out.println(res);
	}
	
	public static String sss(String s) {
		char[] arr = s.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == '[') {
				arr[i] = '{';
			} else if (arr[i] == ']') {
				arr[i] = '}';
			}
		}
		return new String(arr);
	}
}
