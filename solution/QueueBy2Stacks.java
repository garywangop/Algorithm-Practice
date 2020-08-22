package solution;

import java.util.ArrayDeque;
import java.util.Deque;

public class QueueBy2Stacks {
	private Deque<Integer> stack1;
	  private Deque<Integer> stack2;
	  
	  public QueueBy2Stacks() {
	    stack1 = new ArrayDeque<>();
	    stack2 = new ArrayDeque<>();  
	  }

	  private void shuffle() {
	    while (!stack1.isEmpty()) {
	      stack2.offerFirst(stack1.pollFirst());
	    }
	  }
	  
	  public Integer poll() {
	    if (stack2.isEmpty()) {
	      shuffle();
	    }
	    return stack2.pollFirst();
	  }
	  
	  public void offer(int element) {
	    stack1.offerFirst(element);
	  }
	  
	  public Integer peek() {
	    if (stack2.isEmpty()) {
	      shuffle();
	    }
	    return stack2.peekFirst();
	  }
	  
	  public int size() {
	    return stack1.size() + stack2.size();
	  }
	  
	  public boolean isEmpty() {
	    return stack1.isEmpty() && stack2.isEmpty();
	  }
}
