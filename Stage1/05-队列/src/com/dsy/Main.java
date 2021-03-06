package com.dsy;

import com.dsy.circle.CirCleQueue;
import com.dsy.circle.CircleDeque;

public class Main {

	public static void main(String[] args) {
//		test03();
		test04();
	}
	
	public static void test04() {
		CircleDeque<Integer> queue = new CircleDeque<Integer>();
		for (int i = 0; i < 10; i++) {
			queue.enQueueFront(i);
			queue.enQueueRear(i + 100);
		}
		System.out.println(queue);
		
		
		for (int i = 0; i < 7; i++) {
			queue.deQueueFront();
		}
		
		for (int i = 15; i < 30; i++) {
			queue.enQueueRear(i);
		}
		System.out.println(queue);
		while (!queue.isEmpty()) {
			System.out.println(queue.deQueueRear());
//			queue.deQueue();
		}
	}
	
	public static void test03() {
		CirCleQueue<Integer> queue = new CirCleQueue<Integer>();
		for (int i = 0; i < 20; i++) {
			queue.enQueue(i);
		}
		for (int i = 0; i < 7; i++) {
			queue.deQueue();
		}
		
		for (int i = 15; i < 30; i++) {
			queue.enQueue(i);
		}
		System.out.println(queue);
		while (!queue.isEmpty()) {
			System.out.println(queue.deQueue());
//			queue.deQueue();
		}
	}
	
	public static void test02() {
		Deque<Integer> queue = new Deque<Integer>();
		queue.enQueueFront(11);
		queue.enQueueFront(22);
		queue.enQueueRear(33);
		queue.enQueueRear(44);
		
		while (!queue.isEmpty()) {
			System.out.println(queue.deQueueFront());
		}
	}
	
	public static void test01() {
		Queue<Integer> queue = new Queue<Integer>();
		queue.enQueue(11);
		queue.enQueue(22);
		queue.enQueue(33);
		queue.enQueue(44);
		
		while (!queue.isEmpty()) {
			System.out.println(queue.deQueue());
		}
	}
	
	
}
