package review;

public class TestLinkedList {

	public static void main(String[] args) {
		DesignLinkedList linkedList = new DesignLinkedList();
		
		linkedList.addAtHead(1);
		System.out.print("linkedList.addAtHead(1): ");
		linkedList.show();
		
		linkedList.addAtTail(3);
		System.out.print("linkedList.addAtTail(3): ");
		linkedList.show();
		
		linkedList.addAtIndex(1, 2);  // linked list becomes 1->2->3
		System.out.print("linkedList.addAtIndex(1, 2): ");
		linkedList.show();
		
		System.out.println("run get(1): " + linkedList.get(1)); // returns 2
		
		linkedList.deleteAtIndex(1);  // now the linked list is 1->3
		System.out.print("linkedList.deleteAtIndex(1): ");
		linkedList.show(); 
		
		System.out.println("run get(1): " + linkedList.get(1));
		
		
	}

}
