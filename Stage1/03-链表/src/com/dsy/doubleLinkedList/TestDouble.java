package com.dsy.doubleLinkedList;

public class TestDouble {

	public static void main(String[] args) {
		DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
		list.add(11);
		list.add(22);
		list.add(33);
		list.add(44);
		
		list.add(0, 55);
		list.add(2, 66);
		list.add(list.size(), 77);
		
		System.out.println(list.toString());
		System.out.println(list.toReverseString());
		
		list.remove(0);
		list.remove(2);
		
		System.out.println(list.toString());
	}
}
