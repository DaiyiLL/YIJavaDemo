package com.dsy;

import com.dsy.tree.BinarySearchTree;
import com.dsy.tree.Comparator;
import com.dsy.tree.printer.LevelOrderPrinter;

public class Main {
	
	private static class PersonComparator implements Comparator<Person> {
		@Override
		public int compare(Person e1, Person e2) {
			// TODO Auto-generated method stub
			return e1.getAge() - e2.getAge();
		}
		
	}
	
	private static class PersonComparator2 implements Comparator<Person> {
		@Override
		public int compare(Person e1, Person e2) {
			// TODO Auto-generated method stub
			return e1.getAge() - e2.getAge();
		}
		
	}
	
	public static void main(String[] args) {
		Integer data[] = new Integer[] {
				7, 4, 9, 2, 5, 8, 11, 3, 10, 50, 13, 1, -2, 6
		};
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>(new java.util.Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o1 - o2;
			}
		});
		for (int i = 0; i < data.length; i++) {
			bst.add(data[i]);
		}
		
		LevelOrderPrinter printer = new LevelOrderPrinter(bst);
		System.out.println(printer.printString());
		
//		BinarySearchTree<Person> bst = new BinarySearchTree<Person>(new PersonComparator());
//		bst.add(new Person("戴淑仪", 27));
//		bst.add(new Person("hello", 24));
//		
//		BinarySearchTree<Person> bst2 = new BinarySearchTree<Person>(new Comparator<Person>() {
//
//			@Override
//			public int compare(Person e1, Person e2) {
//				// TODO Auto-generated method stub
//				return e1.getAge() - e2.getAge();
//			}
//		});
//		bst.add(new Person("戴淑仪", 27));
//		bst.add(new Person("hello", 24));
	}

}
