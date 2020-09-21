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
			if (isBalanced(node)) {
				// 更新高度
				updateHeight(node);
			} else {
				// 恢复平衡
				rebalance(node);
				// 整棵树就恢复平衡
				break;
			}
		}
	}
	
	@Override
	protected Node<E> createNode(Object element, Node parent) {
		// TODO Auto-generated method stub
		return new AVLNode<E>((E) element, parent);
	}
	
	private boolean isBalanced(Node<E> node) {
		return Math.abs(((AVLNode<E>)node).balanceFactor()) <= 1;
	}
	
	private void updateHeight(Node<E> node) {
		((AVLNode<E>) node).updateHeight();;
	}
	
	private void rebalance(Node<E> grand) {
		AVLNode<E> parent = (AVLNode<E>)(((AVLNode<E>)grand).tallerChild());
		AVLNode<E> node = (AVLNode<E>)(((AVLNode<E>)parent).tallerChild());
		
		if (parent.isLeftChild()) {
			if (node.isLeftChild()) { // LL
				rotateRight(grand);
			} else { // LR
				rotateLeft(parent);
				rotateRight(grand);
			}
		} else {
			if (node.isLeftChild()) { // RL
				 rotateRight(parent);
				 rotateLeft(grand);
			} else { // RR
				rotateLeft(grand);
			}
		}
	}
	
	private void rotateLeft(Node<E> grand) {
		Node<E> parent = grand.right;
		Node<E> child = parent.left;
		grand.right = child;
		parent.left = grand;
		
		afterRotate(grand, parent, child);
	}
	
	private void rotateRight(Node<E> grand) {
		Node<E> parent = grand.left;
		Node<E> child  = parent.right;
		grand.left = child;
		parent.right = grand;
		
		afterRotate(grand, parent, child);
	}
	
	private void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
		// parent成为字数根节点
		parent.parent = grand.parent;
		if (grand.isLeftChild()) {
			grand.parent.left = parent;
		} else if (grand.isRightChild()) {
			grand.parent.right = parent;
		} else {
			// 根节点
			root = parent;
		}
		
		// 更新child的parent
		if (child != null) {
			child.parent = grand;
		}
		// 更新grand的parent
		grand.parent = parent;
		
		// 更新高度
		updateHeight(grand);
		updateHeight(parent);
	}
	
	private static class AVLNode<E> extends Node<E> {
		int height = 1;
		public AVLNode(E element, Node<E> parent) {
			super(element, parent);
			// TODO Auto-generated constructor stub
		}
		
		public int balanceFactor() {
			int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
			int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
			return leftHeight - rightHeight;
		}
		
		public void updateHeight() {
			int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
			int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
			height = 1 + Math.max(leftHeight, rightHeight);
		}
		
		public Node<E> tallerChild() {
			int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
			int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
			if (leftHeight > rightHeight) return left;
			if (leftHeight < rightHeight) return right;
			return isLeftChild() ? left : right;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			String parentString = "null";
			if (parent != null) {
				parentString = parent.element.toString();
			}
			return element + "_P(" + parentString + ")_height(" + height + ")";
		}
	}
}
