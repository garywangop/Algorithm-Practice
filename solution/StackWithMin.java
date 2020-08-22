package solution;

import java.util.ArrayDeque;
import java.util.Deque;

public class StackWithMin {

	private Deque<Integer> stack;
	  private Deque<Integer> min;

	  public StackWithMin() {
	    stack = new ArrayDeque<>();
	    min = new ArrayDeque<>();
	  }
	  
	  public int pop() {
	    if (stack.isEmpty()) {
	      return -1;
	    } else {
	      min.pollFirst();
	      return stack.pollFirst();
	    }
	    
	  }
	  
	  public void push(int element) {
	    stack.offerFirst(element);
	    if (min.isEmpty()) {
	      min.offerFirst(element);
	    } else {
	      int prev = min.peekFirst();
	      if (prev <= element) {
	        min.offerFirst(prev);
	      } else {
	        min.offerFirst(element);
	      }
	    }
	  }
	  
	  public int top() {
	    return stack.isEmpty() ? -1 : stack.peekFirst();
	  }
	  
	  public int min() {
	    return min.peekFirst();
	  }
}
