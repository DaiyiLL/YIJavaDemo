package com.dsy;

import java.util.ArrayList;

import com.dsy.circle.DoubleCircleLinkedList;
import com.dsy.circle.SingleCircleLinkedList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		List<Person> list = new LinkedList<Person>();
//		list.add(new Person(27, "戴淑仪"));
//		
//		System.out.println(list);
//		
//		
//		List<Integer> nums = new LinkedList<Integer>();
//		nums.add(20);
//		nums.add(0, 10);
//		nums.add(30);
//		nums.add(nums.size(), 40);
//		System.out.println(nums);
		
//		SingleCircleLinkedList<Integer> list = new SingleCircleLinkedList<Integer>();
		
//		DoubleCircleLinkedList<Integer> list = new DoubleCircleLinkedList<Integer>();
//		testList(list);
		
		josephus();
	}
	
	public static void josephus() {
		DoubleCircleLinkedList<Integer> list = new DoubleCircleLinkedList<Integer>();
		for (int i = 1; i <= 8; i++) {
			list.add(i);
		}
		
		// 指向头节点
		list.reset();
		while (list.size > 1) {
			list.next();
			list.next();
			System.out.println(list.remove());
		}
		System.out.println("获胜者: " + list.get(0));
	}
	
	public static void testList(AbstractList<Integer> list) {
		list.add(11);
		list.add(22);
		list.add(33);
		list.add(44);
		
		list.add(0, 55);
		System.out.println(list.toString());
		list.add(2, 66);
		list.add(list.size(), 77);
		
		System.out.println(list.toString());
		
		list.remove(0);
		list.remove(2);
		
		System.out.println(list.toString());
	}
	

}
