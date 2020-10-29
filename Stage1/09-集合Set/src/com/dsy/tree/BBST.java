package com.dsy.tree;

import com.dsy.tree.BinaryTree.Node;

public class BBST<E> extends BinarySearchTree<E> {
	
	public BBST() {
		this(null);
	}
	
	public BBST(java.util.Comparator<E> comparator) {
		super(comparator);
	}

	protected void rotateLeft(Node<E> grand) {
		Node<E> parent = grand.right;
		Node<E> child = parent.left;
		grand.right = child;
		parent.left = grand;
		
		afterRotate(grand, parent, child);
	}
	
	protected void rotateRight(Node<E> grand) {
		Node<E> parent = grand.left;
		Node<E> child  = parent.right;
		grand.left = child;
		parent.right = grand;
		
		afterRotate(grand, parent, child);
	}
	
	protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
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
		
	}
	
	/**
	 * 
	 * @param r 根节点
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @param e
	 * @param f
	 * @param g
	 */
	protected void rotate(
			Node<E> r,
			Node<E> a, Node<E> b, Node<E> c,
			Node<E> d,
			Node<E> e, Node<E> f, Node<E> g
			) {
		d.parent = r.parent;
		if (r.isLeftChild()) {
			r.parent.left = d;
		} else if (r.isRightChild()) {
			r.parent.right = d;
		} else {
			root = d;
		}
		b.left = a;
		b.right = c;
		if (a != null) {
			a.parent = b;
		}
		if (c != null) {
			c.parent = b;
		}
//		updateHeight(b);
		
		// e-f-g
		f.left = e;
		f.right = g;
		if (e != null) {
			e.parent = f;
		}
		if (g != null) {
			g.parent = f;
		}
//		updateHeight(f);
		
		// b-d-f
		d.left = b;
		d.right = f;
		if (b != null) {
			b.parent = d;
		}
		if (f != null) {
			f.parent = d;
		}
//		updateHeight(d);
	}
}
