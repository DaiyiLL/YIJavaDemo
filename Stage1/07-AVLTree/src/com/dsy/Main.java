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
				7, 4, 9, 2, 5, 6, 8, 11, 3, 12, 1
		};
		AVLTree<Integer> avl = new AVLTree<Integer>();
		for (int i = 0; i < data.length; i++) {
			avl.add(data[i]);
		}
		BinaryTrees.println(avl);
		
		avl.remove(7);
		BinaryTrees.println(avl);
	}
}

	
	
	
	
