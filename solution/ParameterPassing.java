package solution;

public class ParameterPassing {


	public static void main(String[] args) {
		// Problem 0 - 5
		int x = 5;
		changeIntValue(x);
		System.out.println(x);
		
		// Problem 1 - 5
		Simple originalSimple1 = new Simple(5);
		changeSimpleValue1(originalSimple1);
		System.out.println(originalSimple1.value);
		
		// Problem 2 - 10
		Simple originalSimple2 = new Simple(5);
		changeSimpleValue2(originalSimple2);
		System.out.println(originalSimple2.value);
		
		// Problem 3 - 10
		Simple originalSimple3 = new Simple(5);
		originalSimple3 = changeSimpleValue3(originalSimple3);
		System.out.println(originalSimple3.value);
	}
	private static void changeIntValue(int y) {
		y = 10;
		System.out.println("y value is: " + y);
	}
	
	private static void changeSimpleValue1(Simple simple) {
		Simple newSimple = new Simple(10);
		simple = newSimple;
	}
	private static void changeSimpleValue2(Simple simple) {
		simple.value = 10;
	}
	private static Simple changeSimpleValue3(Simple simple) {
		Simple newSimple = new Simple(10);
		return newSimple;
	}	
}
	


class Simple{
	public int value;
	public Simple (int v) {
		value = v;
	}	
}

