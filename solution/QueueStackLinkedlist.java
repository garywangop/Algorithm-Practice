package solution;
import java.util.*;
import java.lang.reflect.Array;

public class QueueStackLinkedlist {
	public static void main(String args[]) {
		
	}
}

// Q1: how could we implement a queue by using 2 stacks?
// Queue: offer, poll, peek, size, isEmpty
// Stack: push(offerFirst), pop(pollFirst), peek(peekFirst), size, isEmpty
class MyQueue {
	private Deque<Integer> in = new LinkedList<>(); 
	private Deque<Integer> out = new LinkedList<>(); 
	
	public void offer(int value) {
		in.push(value);
	}	
	private void shuffleIfNecessay() {
		if (out.isEmpty()) {
			while (!in.isEmpty()) {
				out.push(in.pop());
			}
		}
	}	
	public Integer poll() { //用Integer更好，因为如果是空的话，Integer可以返回null，int不行
		shuffleIfNecessay();
		if (out.isEmpty()) {
			return null;
		}
		return out.pop();
	}	
	public Integer peek() {
		shuffleIfNecessay();
		if (out.isEmpty()) {
			return null;
		}
		return out.pop();
	}	
	public int size() {
		return in.size() + out.size();
	}	
	public boolean isEmpty() {
		return in.isEmpty() && out.isEmpty();
	}
}

// Q2: how to implement the min() function when using stack with time complexity O(1)
class MinStack {
	private Deque<Integer> stack = new LinkedList<>(); 
	private Deque<Integer> minS = new LinkedList<>(); 
	
	public void push(int value) {
		stack.push(value);
		if (minS.isEmpty() || value <= minS.peek()) {
			minS.push(value);
		}
	}
	public Integer pop() {
		if (stack.isEmpty()) {
			return null;
		}
		int result = stack.pop();
		if (result == stack.pop()) {
			minS.pop();
		}
		return stack.pop();
	}
	public Integer peek() {
		if (stack.isEmpty()) {
			return null;
		}
		return stack.peek();
	}
	public int size() {
		return stack.size();
	}
	public boolean isEmpty() {
		return stack.isEmpty();
	}
	public Integer min() {
		if (minS.isEmpty()) {
			return null;
		}
		return minS.peek();
	}
}

// Q3: how to reverse a linkedlist


class Solution1 {
	public ListNode reverse(ListNode head) {
		if (head == null || head.next == null) {
			return null;
		}
		ListNode prev = null, curr = head;
		while (curr != null) {
			ListNode next = curr.next;
			
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		return prev;
	}
}

// Use recursion to reverse a linkedlist























