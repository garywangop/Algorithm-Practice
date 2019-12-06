package solution;



public class Queue {
	public static void main(String[] args) {
		System.out.println(power(3,3));
	}
	
	public static long power(int a, int b) {
		if (b == 0) {
			return 1;
		} 
		if (a == 0) {
			return 0;
		}
		long temp = power(a, b/2);
		if (b%2 == 0) {
			return temp * temp;
		} else {
			return temp * temp * a;
		}
	}

}
