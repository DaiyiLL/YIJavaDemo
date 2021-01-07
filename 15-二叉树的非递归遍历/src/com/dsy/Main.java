package com.dsy;

import com.dsy.tree.printer.BinaryTrees;
import com.dsy.tree.BinarySearchTree;
import com.dsy.tree.BinaryTree.Visitor;

public class Main {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// 创建BST
		Integer data[] = new Integer[] {
				7, 4, 9, 2, 5, 8, 11
		};
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		for (int i = 0; i < data.length; i++) {
			bst.add(data[i]);
		}
		// 树状打印
		BinaryTrees.println(bst);
		// 遍历器
		StringBuilder sb = new StringBuilder();
		Visitor<Integer> visitor = new Visitor<Integer>() {
			@Override
			public boolean visit(Integer element) {
				sb.append(element).append(" ");
				return false;
			}
		};
		// 遍历
		sb.delete(0, sb.length());
		bst.preOrder2(visitor);
		System.out.println(sb);
		Asserts.test(sb.toString().equals("7 4 2 5 9 8 11 "));

		sb.delete(0, sb.length());
		bst.inOrder(visitor);
		Asserts.test(sb.toString().equals("2 4 5 7 8 9 11 "));

		sb.delete(0, sb.length());
		bst.postOrder(visitor);
		Asserts.test(sb.toString().equals("2 5 4 8 11 9 7 "));
	}
}
