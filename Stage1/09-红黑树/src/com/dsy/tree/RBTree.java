package com.dsy.tree;

import com.dsy.tree.BinaryTree.Node;

public class RBTree<E> extends BBST<E> {
	
	private static final boolean RED = false;
	private static final boolean BLACK = true;
	
	public RBTree() {
		this(null);
	}
	
	public RBTree(java.util.Comparator<E> comparator) {
		super(comparator);
	}
	
	@Override
	protected RBNode<E> createNode(Object element, Node parent) {
		// TODO Auto-generated method stub
		return new RBNode<E>((E) element, parent);
	}
	
	private static class RBNode<E> extends Node<E> {
		
		boolean color = RED;
		public RBNode(E element, Node<E> parent) {
			super(element, parent);
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			String str = "";
			if (color == RED) {
				str = "R_";
			}
			return str + element.toString();
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
	protected void afterRemove(Node<E> node, Node<E> replacement) {
		// TODO Auto-generated method stub
		// 如果删除的节点是红色
		if (isRed(node)) return;
		// 如果取代的node的子节点是红色的
		if (isRed(replacement)) {
			black(replacement);
			return;
		}
		Node<E> parent = node.parent;
		// 删除的是根节点
		if (parent == null) return;
		// 删除的是黑色叶子节点
		// 被删除的node是做还是有
		boolean left = parent.left == null || node.isLeftChild();
		Node<E> sibling = left ? parent.right : parent.left; 
		if (left) {
			// 被删除的节点在左边，兄弟节点在右边
			if (isRed(sibling)) {
				// 兄弟节点是红色
				black(sibling);
				red(parent);
				rotateLeft(parent);
				
				// 更换兄弟
				sibling = parent.right;
			}
			
			// 兄弟节点必然是黑色
			if (isBlack(sibling.left) && isBlack(sibling.right)) {
				// 兄弟节点没有1个红色子节点，父节点要想下跟兄弟节点合作
				boolean parentBlack = isBlack(parent);
				black(parent);
				red(sibling);
				if (parentBlack) {
					afterRemove(parent, null);
				}
			} else {
				// 兄弟节点至少一个红色节点，向兄弟节点借元素
				if (isBlack(sibling.right)) {
					// 左边是黑色，兄弟要先旋转
					rotateRight(sibling);
					sibling = parent.right;
				}
				color(sibling, colorOf(parent));
				black(sibling.right);
				black(parent);
				
				rotateLeft(parent);
			}
		} else {
			// 被删除的节点在右边，兄弟节点在左边
			if (isRed(sibling)) {
				// 兄弟节点是红色
				black(sibling);
				red(parent);
				rotateRight(parent);
				
				// 更换兄弟
				sibling = parent.left;
			}
			
			// 兄弟节点必然是黑色
			if (isBlack(sibling.left) && isBlack(sibling.right)) {
				// 兄弟节点没有1个红色子节点，父节点要想下跟兄弟节点合作
				boolean parentBlack = isBlack(parent);
				black(parent);
				red(sibling);
				if (parentBlack) {
					afterRemove(parent, null);
				}
			} else {
				// 兄弟节点至少一个红色节点，向兄弟节点借元素
				if (isBlack(sibling.left)) {
					// 左边是黑色，兄弟要先旋转
					rotateLeft(sibling);
					sibling = parent.left;
				}
				color(sibling, colorOf(parent));
				black(sibling.left);
				black(parent);
				
				rotateRight(parent);
			}
			
		}
		
	}
	
	
	
}
