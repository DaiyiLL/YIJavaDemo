package com.dsy.tree;

import javax.naming.BinaryRefAddr;
import com.dsy.tree.printer.BinaryTreeInfo;

public class BinarySearchTree<E> implements BinaryTreeInfo {
	
	private int size;
	private Node<E> root;
	
	private java.util.Comparator<E> comparator;
	
	public BinarySearchTree() {
		this(null);
	}
	
	public BinarySearchTree(java.util.Comparator<E> comparator) {
		this.comparator = comparator;
	}

	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void clear() {
		
	}
	
	public void add(E element) {
		elementNotNullCheck(element);
		if (root == null) {
			// 添加空节点
			root = new Node<>(element, null);
			size++;
			return;
		}
		// 添加的不是第一个节点
		// 找到父节点
		Node<E> node = root;
		Node<E> parent = root;
		int cmp = 0;
		while (node != null) {
			cmp = compare(element, node.element);
			parent = node;
			if (cmp > 0) {
				node = node.right;
			} else if (cmp < 0) {
				node = node.left;
			} else {
				return;
			}
		}
		Node<E> newNode = new Node<E>(element, parent);
		if (cmp > 0) {
			parent.right = newNode;
		} else if (cmp < 0) {
			parent.left = newNode;
		}
		size++;
	}
	
	public void remove(E element) {
		
	}
	public boolean contains(E element) {
		return false;
	}
	
	
	private void elementNotNullCheck(E element) {
		if (element == null) {
			throw new IllegalArgumentException("节点不能为空");
		}
	}
	
	/*
	 * return 0  e1==e2
	 * return 1  e1>e2
	 * return -1 e1<e2
	 */
	private int compare(E e1, E e2) {
		if (comparator != null) {
			return comparator.compare(e1, e2);
		}
		return ((Comparable<E>)e1).compareTo(e2);
	}
	
	
	private static class Node<E> {
		E element;
		Node<E> left;
		Node<E> right;
		Node<E> parent;
		
		public Node(E element, Node<E> parent) {
			this.element = element;
			this.parent = parent;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "" + element;
		}
	}


	@Override
	public Object root() {
		// TODO Auto-generated method stub
		return root;
	}

	@Override
	public Object left(Object node) {
		// TODO Auto-generated method stub
		return ((Node<E>)node).left;
	}

	@Override
	public Object right(Object node) {
		// TODO Auto-generated method stub
		return ((Node<E>)node).right;
	}

	@Override
	public Object string(Object node) {
		// TODO Auto-generated method stub
		return node.toString();
	}
	
	
	
}
