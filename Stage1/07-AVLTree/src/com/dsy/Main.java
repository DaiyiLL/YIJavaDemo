package com.dsy;

import com.dsy.tree.AVLTree;
import com.dsy.tree.BinarySearchTree;
import com.dsy.tree.printer.BinaryTrees;

public class Main {

	public static void main(String[] args) {
		test01();
	}
	
	
	private static void test01() {
		Integer data[] = new Integer[] {
				85, 19, 69, 3, 7, 99, 95 //, 2, 1, 70, 44, 58, 11, 21, 14, 93, 57, 4, 56
		};
		AVLTree<Integer> avl = new AVLTree<Integer>();
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		for (int i = 0; i < data.length; i++) {
			avl.add(data[i]);
//			bst.add(data[i]);
		}
		
		BinaryTrees.println(avl);
		avl.remove(99);
		avl.remove(85);
		avl.remove(95);
		System.out.println();
//		BinaryTrees.println(bst);
		BinaryTrees.println(avl);
		
//		avl.remove(7);
//		BinaryTrees.println(avl);
	}
}

	
	
	
	
