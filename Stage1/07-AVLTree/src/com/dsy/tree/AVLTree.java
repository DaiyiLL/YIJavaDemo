package com.dsy.tree;

public class AVLTree<E> extends BinarySearchTree<E> {

	public AVLTree() {
		this(null);
	}
	
	public AVLTree(java.util.Comparator<E> comparator) {
		super(comparator);
	}
	
	@Override
	protected void afterAdd(Node<E> node) {
		// TODO Auto-generated method stub
//		super.afterAdd(node);
		
		while ((node = node.parent) != null) {
			
		}
	}
	
	private static class AVLNode<E> extends Node<E> {
		int height;
		public AVLNode(E element, Node<E> parent) {
			super(element, parent);
			// TODO Auto-generated constructor stub
		}
		
	}
}
