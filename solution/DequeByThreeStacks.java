package solution;
import java.util.*;

public class DequeByThreeStacks {
	public static void main(String args[]) {
		DequeByThreeStacks test = new DequeByThreeStacks();
		
		System.out.println(test.pollLast());
		System.out.println(test.peekFirst());
		test.offerLast(217);
		System.out.println("offerLast(217)");
		System.out.println(test.peekFirst());
		test.offerFirst(188);
		System.out.println("offerFirst(188)");
		System.out.println(test.pollLast());
		System.out.println(test.isEmpty());
		test.offerLast(77);
		System.out.println("offerLast(77)");
		test.offerLast(234);
		System.out.println("offerLast(234)");
		System.out.println(test.peekLast());
		System.out.println(test.pollFirst());
	}
	
	
	public DequeByThreeStacks() {
		
	}
	
	private Deque<Integer> first = new LinkedList<>();
	private Deque<Integer> last = new LinkedList<>();
	private Deque<Integer> buffer = new LinkedList<>();
	
	public void offerFirst(int element) {
		first.offerFirst(element);
	}
	
	public void offerLast(int element) {
		last.offerFirst(element);
	}
	
	public Integer pollFirst() {
		move(last, first);
		return first.isEmpty() ? null : first.pollFirst();
	}
	
	public Integer pollLast() {
		move(first, last);
		return last.isEmpty() ? null : last.pollFirst();
	}
	
	
	public Integer peekFirst() {
		move(last, first);
		return first.peekFirst();
	}
	
	public Integer peekLast() {
		move(first, last);
		return last.peekFirst();
	}
	
	public int size() {
		return first.size() + last.size();
	}
	
	public boolean isEmpty() {
		return first.isEmpty() && last.isEmpty();
	}
	
	// If destination is empty, move half of source elements to destination
	// Amortized T = O(1)
	private void move(Deque<Integer> src, Deque<Integer> dest) {
		if (!dest.isEmpty()) {
			return;
		}
		int halfSize = src.size() / 2;
		// Move half of elements from source to buffer
		for (int i = 0; i < halfSize; i++) {
			buffer.offerFirst(src.pollFirst());
		}
		// Move the rest of elements from source to destination
		while (!src.isEmpty()) {
			dest.offerFirst(src.pollFirst());
		}
		// Move elements from buffer to source
		while (!buffer.isEmpty()) {
			src.offerFirst(buffer.pollFirst());
		}
	}
}
