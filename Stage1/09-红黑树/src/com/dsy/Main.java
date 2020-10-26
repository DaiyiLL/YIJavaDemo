package com.dsy;

import com.dsy.tree.RBTree;
import com.dsy.tree.printer.BinaryTrees;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test00();
		test01();
	}
	
	private static void test01() {
		Integer data[] = new Integer[] {
			55, 87, 56, 74, 96, 22, 62, 20, 70, 68, 90, 50
		};
		RBTree<Integer> rbTree = new RBTree<>();
		for (int i = 0; i < data.length; i++) {
			rbTree.add(data[i]);
		}
		BinaryTrees.println(rbTree);
		
		for (int i = 0; i < data.length; i++) {
			rbTree.remove(data[i]);
			BinaryTrees.println(rbTree);
		}
	}
	
	private static void test00() {
		Integer data[] = new Integer[] {
			55, 87, 56, 74, 96, 22, 62, 20, 70, 68, 90, 50
		};
		RBTree<Integer> rbTree = new RBTree<>();
		for (int i = 0; i < data.length; i++) {
			rbTree.add(data[i]);
		}
		BinaryTrees.println(rbTree);
	}

}
