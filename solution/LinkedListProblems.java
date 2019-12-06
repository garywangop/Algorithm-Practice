package solution;

public class LinkedListProblems {
	public static void main(String args[]) {
		ListNode N1 = new ListNode(0);
		ListNode N2 = new ListNode(1);
		ListNode N3 = new ListNode(2);
		ListNode N4 = new ListNode(3);
		ListNode N5 = new ListNode(4);
		N1.next = N2;
		N2.next = N3;
		N3.next = N4;
		N4.next = N5;
		reOrder(N1);
		ListNode dummy = new ListNode(999);
		dummy.next = N1;
		while (dummy.next != null) {
			System.out.println(dummy.next.value);
			dummy = dummy.next;
		}
	}
	
	//Implement queue
	public class Queue {
		ListNode head;
		ListNode tail;
		public Queue() {
			head = tail = null;
		}
		public Integer poll() {
			if (head == null) {
				return null;
			}
			ListNode node = head;
			head = head.next;
			if (head == null) {
				tail = null;
			}
			node.next = null;
			return node.value;
		}
		public Integer peek() {
			if (head == null) {
				return null;
			}
			return head.value;
		}
		public void offer(int ele) {
			if (head == null) {
				head = new ListNode(ele);
				tail = head;
			} else {
				tail.next = new ListNode(ele);
				tail = tail.next;
			}
		}
	}
	
	// Implement stack
	public class Stack {
		private ListNode head;
		public Stack() {
			head = null;
		}
		public Integer pop() {
			if (head == null) {
				return null;
			}
			ListNode prev = head;
			head = head.next;
			prev.next = null;
			return prev.value;
		}
		public Integer peek() {
			if (head == null) {
				return null;
			}
			return head.value;
		}
		public void push(int ele) {
			ListNode node = new ListNode(ele);
			node.next = head;
			head = node;
		}
	}
	
	// Reverse linked list by iteration
	public static ListNode reverseIteration(ListNode head) {
		ListNode prev = null;
		while (head != null) {
			ListNode next = head.next;
			head.next = prev;
			prev = head;
			head = next;
		}
		return prev;
	}
	
	// Reverese linked list by recursion
	public static ListNode reverseRecursion(ListNode head) {
		// Corner case + base case:
		if (head == null || head.next == null) {
			return head;
		}
		// Recursion rule:
		ListNode newHead = reverseRecursion(head.next);
		head.next.next = head;
		head.next = null;
		return newHead;
	}
	
	// Hard level
	// ReOrder linked list
	public static ListNode reOrder(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode mid = findMid(head);
		ListNode head2 = mid.next;
		ListNode head1 = head;
		mid.next = null;
		
		// Reverse head2
		head2 = reverseRecursion(head2);
		
		// Merge head1 and head2, and this is the result we want
		return merge(head1, head2);
		
	}
	private static ListNode findMid(ListNode head) {
		ListNode s = head, f = head;
		while (f.next != null && f.next.next != null) {
			s = s.next;
			f = f.next.next;
		}
		return s;
	}

	private static ListNode merge(ListNode head1, ListNode head2) {
		ListNode dummy = new ListNode(999);
		//dummy.next = head1;
		ListNode cur = dummy;
		while (head1 != null && head2 != null) {
			/**
			cur.next = head1;
			cur.next.next = head2;
			head1 = head1.next;
			head2 = head2.next;
			cur = cur.next.next;**/
			cur.next = head1;
			head1 = head1.next;
			cur.next.next = head2;
			head2 = head2.next;
			cur = cur.next.next;
		}
		if (head1 != null) {
			cur.next = head1;
		}
		if (head2 != null) {
			cur.next = head2;
		}
		return dummy.next;
	}
}














