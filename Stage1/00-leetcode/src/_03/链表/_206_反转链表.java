package _03.链表;

public class _206_反转链表 {

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
		
		ListNode newHead = reverseList2(header);
		ListNode.log(newHead);

	}
	
	public static ListNode reverseList(ListNode head) {
    	if (head == null || head.next == null) {
    		return head;
    	}
    	
    	ListNode newHead = reverseList(head.next);
    	head.next.next = head;
    	head.next = null;
    	return newHead;
    }
	
	public static ListNode reverseList2(ListNode head) {
    	if (head == null || head.next == null) {
    		return head;
    	}
    	
    	ListNode next = head.next;
    	ListNode newHead = head;
    	newHead.next = null;
    	while (next != null) {
    		ListNode cur = next.next;
    		next.next = newHead;
    		newHead = next;
    		next = cur;
    	}

    	return newHead;
    }

}
