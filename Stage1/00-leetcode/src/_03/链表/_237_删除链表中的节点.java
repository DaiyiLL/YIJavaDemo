package _03.链表;

import org.w3c.dom.ranges.RangeException;

//https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
public class _237_删除链表中的节点 {
	
	public static void deleteNode(ListNode node) {
//		if (node.next == null) {
//			throw new RangeException((short) 0, "不能是最后一个节点");
//		}
//		node.val = node.next.val;
//		node.next = node.next.next;
		node = node.next;
    }
	
	public static void main(String []args) {
		ListNode node1 = new ListNode(4);
		ListNode node2 = new ListNode(1);
		ListNode node3 = new ListNode(5);
		ListNode node4 = new ListNode(9);
		
		
		ListNode header = new ListNode(4);
		
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = null;
		
		header.next = node1;
		
		deleteNode(node2);
		
		log(header);
	}
	
	public static void log(ListNode link) {
		ListNode node = link.next;
		if (node == null) return;
		while (node.next != null) {
			System.out.println(node.val);
			node = node.next;
		}
		System.out.println(node.val);
	}
}
