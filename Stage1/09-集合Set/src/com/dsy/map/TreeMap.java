package com.dsy.map;

import com.dsy.tree.RBTree;
import com.dsy.tree.BinaryTree.Node;

public class TreeMap<K, V> implements Map<K, V> {
	
	private RBTree<Node> tree = new RBTree<Node>();
	
	private static class Node<K, V> {
		private static final boolean RED = false;
		K key;
		V value;
		boolean color = RED;
		Node<K, V> left;
		Node<K, V> right;
		Node<K, V> parent;
		
		public Node(K key, V value, Node<K, V> parent) {
			this.key = key;
			this.parent = parent;
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
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			String parentString = "null";
			if (parent != null) {
				parentString = parent.element.toString();
			}
			return element + "_p(" + parentString + ")";
		}
		
		public Node<E> sibling() {
			if (isLeftChild()) {
				return parent.right;
			} 
			if (isRightChild()) {
				return parent.left;
			}
			return null;
		}
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public V put(K key, V value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V get(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V remove(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean containsKey(K key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsValue(V value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void traversal(Visitor<K, V> visitor) {
		// TODO Auto-generated method stub
		
	}

}
