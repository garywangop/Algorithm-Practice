package review;

public class DesignLinkedList {

	ListNode head;
	int size;

	public DesignLinkedList() {
		head = null;
		size = 0;
	}

	public void show() {
		ListNode cur = head;
		while (cur != null) {
			System.out.print(cur.value + " ");
			cur = cur.next;
		}
		System.out.println();
	}

	public int get(int index) {
		if (index >= size) {
			throw new IllegalArgumentException("Index is out of range");
		}
		ListNode cur = head;
		for (int i = 0; i < index; i++) {
			cur = cur.next;
		}
		return cur.value;
	}

	/**
	 * Add a node of value val before the first element of the linked list. After
	 * the insertion, the new node will be the first node of the linked list.
	 */
	public void addAtHead(int val) {

		ListNode node = new ListNode(val);

		if (head == null) {
			head = node;
		} else {
			node.next = head;
			head = node;
		}
		size++;
	}

	/** Append a node of value val to the last element of the linked list. */
	public void addAtTail(int val) {

		ListNode node = new ListNode(val);

		if (head == null) {
			head = node;
			return;
		} else {
			ListNode cur = head;
			while (cur.next != null) {
				cur = cur.next;
			}
			cur.next = node;
		}
		size++;
	}

	/**
	 * Add a node of value val before the index-th node in the linked list. If index
	 * equals to the length of linked list, the node will be appended to the end of
	 * linked list. If index is greater than the length, the node will not be
	 * inserted.
	 */
	public void addAtIndex(int index, int val) {
		if (index > size) {
			throw new IllegalArgumentException("Index is out of range");
		}
		ListNode node = new ListNode(val);
		if (index == size) {
			addAtTail(val);
		} else if (index == 0) {
			addAtHead(val);
		} else {
			int count = 0;
			ListNode cur = head;
			while (count < index - 1) {
				cur = cur.next;
			}
			ListNode next = cur.next;
			cur.next = node;
			node.next = next;
		}
		size++;
	}

	/** Delete the index-th node in the linked list, if the index is valid. */
	public void deleteAtIndex(int index) {
		if (head == null) {
			throw new IllegalArgumentException("Have nothing to delete");
		}
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException("Index is out of range");
		}
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode cur = dummy;
		for (int i = 0; i < index; i++) {
			cur = cur.next;
		}
		ListNode next = cur.next.next;
		cur.next = next;
		head = dummy.next;
		size--;
	}

	public class ListNode {
		int value;
		ListNode next;

		public ListNode(int value) {
			this.value = value;
		}
	}
}
