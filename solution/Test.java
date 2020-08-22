package solution;

import java.util.ArrayDeque;
import java.util.Queue;

public class Test {

	public static void main(String[] args) {
		DequeBy3Stacks sol = new DequeBy3Stacks();
		
		sol.offerFirst(1);
		sol.offerFirst(2);
		System.out.println(sol.pollLast());
	}

}
