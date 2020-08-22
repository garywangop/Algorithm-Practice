package solution;

import java.util.Deque;
import java.util.LinkedList;

public class SortNumbersInTwoStacks {
	public static void main(String args[]) {
		LinkedList<Integer> s3 = new LinkedList<>();
		s3.offerFirst(4);
		s3.offerFirst(3);
		s3.offerFirst(2);
		s3.offerFirst(1);
		
		SortNumbersInTwoStacks test = new SortNumbersInTwoStacks();
		test.sort(s3);
		while (!s3.isEmpty()) {
			System.out.println(s3.pollFirst());
		}
	}
	public void sort(LinkedList<Integer> s1) {
		LinkedList<Integer> s2 = new LinkedList<>();
		if (s1.isEmpty() || s1.size() <= 1) {
			return;
		}
		sort(s1, s2);
		
	}
	// s1: input
	// s2: buffer + output
	private void sort(Deque<Integer> input, Deque<Integer> buffer) {
		int sorted = 0;
	    int size = input.size();

	    while (sorted < size) {

	      int max = Integer.MIN_VALUE;
	      int count = 0;
	      
	      while (input.size() > sorted ) {
	        if (max < input.peekFirst()) {
	          max = input.peekFirst();
	          count = 1;
	        } else if (max == input.peekFirst()){
	          count++;
	        }
	        buffer.offerFirst(input.pollFirst());
	      }

	      for (int i = 0; i < count; i++) {
	        input.offerFirst(max);
	      }

	      while (!buffer.isEmpty()) {
	        if (buffer.peekFirst() != max) {
	          input.offerFirst(buffer.pollFirst());
	        } else {
	        	buffer.pollFirst();
	        }
	      }

	      sorted += count;
	    }
	}
}
