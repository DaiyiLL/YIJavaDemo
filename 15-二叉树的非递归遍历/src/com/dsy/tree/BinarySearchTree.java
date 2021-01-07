package com.dsy.tree;

import java.util.LinkedList;
import java.util.Queue;

import javax.naming.BinaryRefAddr;

import com.dsy.tree.BinaryTree.Node;
import com.dsy.tree.printer.BinaryTreeInfo;

public class BinarySearchTree<E> extends BinaryTree {
	
	private java.util.Comparator<E> comparator;
	
	public BinarySearchTree() {
		this(null);
	}
	
	public BinarySearchTree(java.util.Comparator<E> comparator) {
		this.comparator = comparator;
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
		remove(node(element));
	}
	public boolean contains(E element) {
		return node(element) != null;
	}
	
	
	// 打印遍历
	public void preorderTraversal() {
		this.preorderTraversal(root);
	}
	private void preorderTraversal(Node<E> node) {
		if (node == null) {
			return;
		}
		System.out.println(node.element);
		preorderTraversal(node.left);
		preorderTraversal(node.right);
	}
	public void inorderTraversal() {
		this.inorderTraversal(root);
	}
	private void inorderTraversal(Node<E> node) {
		if (node == null) {
			return;
		}
		inorderTraversal(node.left);
		System.out.println(node.element);
		inorderTraversal(node.right);
	}
	public void postorderTraversal() {
		this.postorderTraversal(root);
	}
	private void postorderTraversal(Node<E> node) {
		if (node == null) {
			return;
		}
		postorderTraversal(node.left);
		postorderTraversal(node.right);
		System.out.println(node.element);
	}
	public void levelorderTranversal() {
		if (root == null) return;
		
		Queue<Node<E>> queue = new LinkedList<Node<E>>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();
			System.out.println(node.element);
			
			if (node.left != null) {
				queue.offer(node.left);
			}
			if (node.right != null) {
				queue.offer(node.right);
			}
		}
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
		return ((java.lang.Comparable<E>)e1).compareTo(e2);
	}
	

	private void remove(Node<E> node) {
		if (node == null) return;
		size --;
		
		// 度为2的节点
		if (node.hasTwoChildren()) {
			// 前驱或者后继
			Node<E> s = successor(node);
			// 用后继节点的值覆盖度为2 的节点的值
			node.element = s.element;
			// 删除后继节点
			node = s;
//			return;
		}
		
		// 删除node节点（node的度避让是1或者0）
		Node<E> replacement = node.left != null ? node.left : node.right;
		if (replacement != null)  {
			// node必定为1的节点
			// 更改paren
			replacement.parent = node.parent;
			if (node.parent == null) {
				// 度为1的节点，并且是根节点
				root = replacement;
			} else if (node == node.parent.left) {
				replacement.parent.left = replacement;
			} else {
				replacement.parent.right = replacement;
			}
			
		} else if (node.parent == null) { // 叶子节点并且是根节点
			root = null;
		} else { // 叶子节点，当不是根节点
			if (node == node.parent.right) {
				node.parent.right = null;
			} else {
				node.parent.left = null;
			}
		}
	}
	
	private Node<E> node(E element) {
		Node<E> node = root;
		while (node != null) {
			int cmp = compare(element, node.element);
			if (cmp == 0) {
				return node;
			} 
			if (cmp > 0) {
				node = node.right;
			} else {
				node = node.left;
			}
		}
		return null;
	}
}
