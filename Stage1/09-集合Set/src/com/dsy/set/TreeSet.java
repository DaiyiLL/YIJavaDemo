package com.dsy.set;

import com.dsy.tree.BinarySearchTree;
import com.dsy.tree.RBTree;

public class TreeSet<E> implements Set<E> {
	
	BinarySearchTree<E> tree = new RBTree<E>();

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return tree.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return tree.isEmpty();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		tree.clear();
	}

	@Override
	public boolean contains(E element) {
		// TODO Auto-generated method stub
		return tree.contains(element);
	}

	@Override
	public void add(E element) {
		// TODO Auto-generated method stub
		tree.add(element);
	}

	@Override
	public void remove(E element) {
		// TODO Auto-generated method stub
		tree.remove(element);
	}

	@Override
	public void traversal(Visitor<E> visitor) {
		// TODO Auto-generated method stub
		tree.inOrder(new BinarySearchTree.Visitor<E>() {
			@Override
			public boolean visit(E element) {
				// TODO Auto-generated method stub
				return visitor.visit(element);
			}
			
		});
	}

}
