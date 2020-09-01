package _03.链表;

public class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { 
		val = x; 
	}
	
	public static void log(ListNode link) {
		ListNode node = link.next;
		System.out.println(link.val);
		if (node == null) return;
		while (node.next != null) {
			System.out.println(node.val);
			node = node.next;
		}
		System.out.println(node.val);
	}
}
