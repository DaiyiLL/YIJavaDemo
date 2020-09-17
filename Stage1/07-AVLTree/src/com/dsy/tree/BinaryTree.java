package com.dsy.tree;

import java.util.LinkedList;
import java.util.Queue;

import com.dsy.tree.printer.BinaryTreeInfo;

public class BinaryTree<E> implements BinaryTreeInfo {
	
	protected int size;
	protected Node<E> root;
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void clear() {
		root = null;
		size = 0;
	}
	
	protected static class Node<E> {
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
		
		public boolean isLeaf() {
			return (left == null && right == null);
		}
		public boolean hasTwoChildren() {
			return (left != null && right != null);
		}
		
		public boolean isLeftChild() {
			return parent != null && this == parent.left;
		}
		
		public boolean isRightChild() {
			return parent != null && this == parent.right;
		}
	}
	
	protected Node<E> createNode(E element, Node<E> parent) {
		return new Node<E>(element, parent);
	}
	
	
	public static abstract class Visitor<E> {
		boolean stop;
		public abstract boolean visit(E element);
	}
	

	public Object root() {
		// TODO Auto-generated method stub
		return root;
	}

	public Object left(Object node) {
		// TODO Auto-generated method stub
		return ((Node<E>)node).left;
	}

	public Object right(Object node) {
		// TODO Auto-generated method stub
		return ((Node<E>)node).right;
	}

	public Object string(Object node) {
		// TODO Auto-generated method stub
		return node.toString();
	}
	
	public void levelOrder(Visitor<E> visitor) {
		if (visitor == null) return;
		levelOrder(root, visitor);
	}
	
	public void preOrder(Visitor<E> visitor) {
		if (visitor == null) return;
		preOrder(root, visitor);
	}
	
	public void inOrder(Visitor<E> visitor) {
		if (visitor == null) return;
		inOrder(root, visitor);
	}
	
	public void postOrder(Visitor<E> visitor) {
		if (visitor == null) return;
		postOrder(root, visitor);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		toString(root, sb, "");
		return sb.toString();
	}
	
	public int height() {
		return height(root);
	}
	
	public int levelHeight() {
		return levelHeight(root);
	}
	
	public boolean isComplete() {
		if (root == null) return false;
		
//		Queue<Node<E>> queue = new LinkedList<Node<E>>();
//		queue.offer(root);
//		boolean leaf = false;
//		while (!queue.isEmpty()) {
//			Node<E> node = queue.poll();
//			if (leaf && !node.isLeaf()) return false;
//			
//			if (node.hasTwoChildren()) {
//				queue.offer(node.left);
//				queue.offer(node.right);
//			} else if (node.left == null && node.right != null) {
//				return false;
//			} else { // 后面遍历的节点都是叶子节点
//				leaf = true;
//				if (node.left != null) {
//					queue.offer(node.left);
//				}
//			}
//			if (node.right != null) {
//				queue.offer(node.right);
//			}
//		}
		Queue<Node<E>> queue = new LinkedList<Node<E>>();
		queue.offer(root);
		
		boolean isleaf = false;
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();
			if (isleaf && !node.isLeaf()) return false;
			
			if (node.left != null) {
				queue.offer(node.left);
			} else if (node.right != null) {
				return false;
			}
			if (node.right != null) {
				queue.offer(node.right);
			} else {
				isleaf = true;
			}
		}
		
		return true;
	}
	
	
	public Node<E> predecessor(Node<E> node) {
		if (node == null) return null;
		// 前驱节点在左子树中（left.right.right.right....）
		if (node.left != null) {
			Node<E> pre = node.left;
			while (pre.right == null) {
				pre = pre.right;
			}
			return pre;
		}
		// 从父节点，祖父节点寻找前驱节点
		while (node.parent != null && node == node.parent.left) {
			node = node.parent;
		}
		// node.parent = null
		// node == node.parent.right
		return node.parent;
	}
	
	
	public Node<E> successor(Node<E> node) {
		if (node == null) return null;
		// 后继节点在左子树中（right.left.left.left....）
		if (node.right != null) {
			Node<E> suc = node.right;
			while (suc.left != null) {
				suc = suc.left;
			}
			return suc;
		}
		// 从父节点，祖父节点寻找后继节点
		while (node.parent != null && node == node.parent.right) {
			node = node.parent;
		}
		// node.parent = null
		// node == node.parent.right
		return node.parent;
	}
	
	private void levelOrder(Node<E>node, Visitor<E> visitor) {
		if (node == null) return;
		
		Queue<Node<E>> queue = new LinkedList<Node<E>>();
		queue.offer(node);
		while (!queue.isEmpty()) {
			Node<E> curr = queue.poll();
//			System.out.println(node.element);
			boolean flag = visitor.visit(curr.element);
			if (flag) {
				return;
			}
			
			if (curr.left != null) {
				queue.offer(curr.left);
			}
			if (curr.right != null) {
				queue.offer(curr.right);
			}
		}
	}
	
	private void preOrder(Node<E>node, Visitor<E> visitor) {
		if (node == null || visitor.stop) return;
//		if (visitor.stop) return;
		visitor.stop = visitor.visit(node.element);
		preOrder(node.left, visitor);
		preOrder(node.right, visitor);
	}
	
	private void inOrder(Node<E>node, Visitor<E> visitor) {
		if (node == null || visitor.stop) return;
		inOrder(node.left, visitor);
		if (visitor.stop) return;  // 此处防止打印
		visitor.stop = visitor.visit(node.element);
		inOrder(node.right, visitor);
	}
	
	// 后续遍历
	private void postOrder(Node<E>node, Visitor<E> visitor) {
		if (node == null || visitor.stop) return;
		postOrder(node.left, visitor);
		postOrder(node.right, visitor);
		if (visitor.stop) return;
		visitor.stop = visitor.visit(node.element);
	}
	
	private void toString(Node<E> node, StringBuilder sb, String prefix) {
		if (node == null) return;
		
		sb.append(prefix).append(node.element).append("\n");
		toString(node.left, sb, prefix + "L---");
		toString(node.right, sb, prefix + "R---");
	}
	
	private int height(Node<E> node) {
		if (node == null) return 0;
		return 1 + Math.max(height(node.left), height(node.right));
	}
	
	private int levelHeight(Node<E> node) {
		if (node == null) return 0;
		Queue<Node<E>> queue = new LinkedList<Node<E>>();
		queue.offer(node);
		int height = 0;
		int levelSize = 1;  // 每一层元素数量
		while (!queue.isEmpty()) {
			Node<E> curr = queue.poll();
			levelSize --;
			
			if (curr.left != null) {
				queue.offer(curr.left);
			}
			if (curr.right != null) {
				queue.offer(curr.right);
			}
			
			if (levelSize == 0) {
				// 意味着即将访问下一层
				levelSize = queue.size();
				height += 1;
			}
		}
		return height;
	}
}
