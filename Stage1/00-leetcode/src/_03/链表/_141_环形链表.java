package _03.链表;

public class _141_环形链表 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode node1 = new ListNode(4);
		ListNode node2 = new ListNode(1);
		ListNode node3 = new ListNode(5);
		ListNode node4 = new ListNode(9);
		
		
		ListNode header = new ListNode(-1);
		
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = null;
		
		header.next = node1;
		
		boolean flag = hasCycle(header);
		System.out.println(flag);
	}
	
	public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
        	slow = slow.next;
        	fast = fast.next.next;
        	
        	if (slow == fast) return true;
        }
        return false;
    }
	

}
