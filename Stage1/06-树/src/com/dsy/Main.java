package com.dsy;

import com.dsy.tree.BinarySearchTree;
import com.dsy.tree.BinarySearchTree.Visitor;
import com.dsy.tree.Comparator;
import com.dsy.tree.printer.BinaryTrees;
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
//		test03();
//		test04();
		test05();
	}
	
	private static void test05() {
		Integer data[] = new Integer[] {
				7, 4, 9, 2, 5, 6, 8, 11, 3, 12, 1
		};
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		for (int i = 0; i < data.length; i++) {
			bst.add(data[i]);
		}
		BinaryTrees.println(bst);
		
		bst.remove(7);
		BinaryTrees.println(bst);
	}
	
	private static void test04() {
		Integer data[] = new Integer[] {
				7, 4, 9, 2, 5, 8, 10, 0, 3
		};
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		for (int i = 0; i < data.length; i++) {
			bst.add(data[i]);
		}
		
//		for (int i = 0; i < 10; i++) {
//			bst.add((int)(Math.random() * 100));
//		}
		BinaryTrees.println(bst);
//		System.out.println(bst);
		
		System.out.println("高度: " + bst.height());
		System.out.println("高度: " + bst.levelHeight());
		System.out.println(bst.isComplete());
	}
	
	private static void test03() {
		Integer data[] = new Integer[] {
				7, 4, 9, 2, 1, 10, 0, 5, 3
		};
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		for (int i = 0; i < data.length; i++) {
			bst.add(data[i]);
		}
		BinaryTrees.println(bst);

		bst.inOrder(new Visitor<Integer>() {
			public boolean visit(Integer element) {
				System.out.print(element + "__");
				return (element == 2);
			}
		});
		System.out.println();
		
		bst.preOrder(new Visitor<Integer>() {
			public boolean visit(Integer element) {
				System.out.print(element + "__");
				return (element == 3);
			}
		});
		System.out.println();
		
		bst.levelOrder(new Visitor<Integer>() {
			public boolean visit(Integer element) {
				System.out.print(element + "__");
				return (element == 5);
			}
		});
		System.out.println();
		
		
	}
	
	private static void test02() {
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
//		bst.preorderTraversal();
//		bst.inorderTraversal();
//		bst.postorderTraversal();
//		bst.levelorderTranversal();
		bst.levelOrder(new Visitor<Integer>() {
			@Override
			public boolean visit(Integer element) {
				// TODO Auto-generated method stub
				System.out.println(element);
				return false;
			}
		});
	}
	
	private static void test01() {
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
