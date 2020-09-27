package com.dsy.tree;

public class RBTree<E> extends BBST<E> {
	
	private static final boolean RED = false;
	private static final boolean BLACK = true;
	
	public RBTree() {
		this(null);
	}
	
	public RBTree(java.util.Comparator<E> comparator) {
		super(comparator);
	}
	
	private static class RBNode<E> extends Node<E> {
		
		boolean color = RED;
		public RBNode(E element, Node<E> parent) {
			super(element, parent);
		}
	}
	
	private Node<E> color(Node<E> node, boolean color) {
		if (node == null) return node;
		((RBNode<E>)node).color = color;
		return node;
	}
	
	private Node<E> red(Node<E> node) {
		return color(node, RED);
	}
	private Node<E> black(Node<E> node) {
		return color(node, BLACK);
	}
	private boolean colorOf(Node<E> node) {
		return node == null ? BLACK : ((RBNode<E>)node).color;
	}
	
	private boolean isBlack(Node<E> node) {
		return colorOf(node) == BLACK;
	}
	private boolean isRed(Node<E> node) {
		return colorOf(node) == RED;
	}
	
	@Override
	protected void afterAdd(Node<E> node) {
		// TODO Auto-generated method stub
		Node<E> parent = node.parent;
		// 根节点
		if (parent == null) {
			black(node);
			return;
		}
		if (isBlack(parent)) return;
		
		// uncle节点
		Node<E> uncle = parent.sibling();
		// 祖父节点
		Node<E> grand = parent.parent;
		if (isRed(uncle)) {
			black(parent);
			black(uncle);
			// 祖父节点当做新添加的节点
			afterAdd(red(grand));
			return;
		}
		// 叔父不是红色
		if (parent.isLeftChild()) {
			red(grand);
			if (node.isLeftChild()) {
				// LL
				black(parent);
			} else {
				// LR
				black(node);
				rotateLeft(parent);
			}
			rotateRight(grand);
		} else {
			red(grand);
			if (node.isLeftChild()) {
				black(node);
				rotateRight(parent);
			} else {
				black(parent);
			}
			rotateLeft(grand);
		}
	}
	
	@Override
	protected void afterRemove(Node<E> node) {
		// TODO Auto-generated method stub
		super.afterRemove(node);
	}
	
	
	
}
