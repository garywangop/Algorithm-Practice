package solution;

import java.util.Deque;
import java.util.LinkedList;

public class SortNumbersInTwoStacks {
	public static void main(String args[]) {
		LinkedList<Integer> s3 = new LinkedList<>();
		s3.offerFirst(4);
		s3.offerFirst(2);
		s3.offerFirst(89);
		s3.offerFirst(8);
		
		SortNumbersInTwoStacks test = new SortNumbersInTwoStacks();
		test.sort(s3);
		for(int i = 0; i < s3.size(); i++) {
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
	private void sort(Deque<Integer> s1, Deque<Integer> s2) {
		while (!s1.isEmpty()) {
			// Move all elements from s1 to s2, get min and count
			int min = Integer.MAX_VALUE, count = 0;
			while (!s1.isEmpty()) {
//				int cur = s1.pollFirst();
//				if (cur < min) {
//					min = cur;
//					count = 1;
//				}
//				if (cur == min) {
//					count++;
//				}
//				s2.offerFirst(cur);
				
				if (min > s1.peekFirst()) {
					min = s1.peekFirst();
					count = 1;
				} else if (min == s1.peekFirst()) {
					count++;
				}
				s2.offerFirst(s1.pollFirst());
				
			}
			// Move all elements from s2 to s1, except for min numbers
			while (!s2.isEmpty() && s2.peekFirst() >= min) {
//				int temp = s2.pollFirst();
//				if (temp != min) {
//					s1.offerFirst(temp);
//				}
				
				if (s2.peekFirst() == min) {
					s2.pollFirst();
				}
				s1.offerFirst(s2.pollFirst());
				
			}
			// Save min to s2
			while (count > 0) {
				s2.offerFirst(min);
				count--;
			}
			/**
			while (!s2.isEmpty()) {
				s1.offerFirst(s2.pollFirst());
			}
			**/
		}
	}
}
