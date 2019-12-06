package solution;

public class IsPrime {
	public static boolean isPrime(int n) {
		for (int i = 2; i < n; i++) {
			if (n % i ==0) {
				return false;
			}
		}
		return true;
	}
	
	public static void main (String[] args) {
		//isPrime(5);
		
		System.out.println(isPrime(5));
		
	}
	
}
