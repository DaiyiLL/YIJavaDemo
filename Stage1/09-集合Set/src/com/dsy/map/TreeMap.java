package com.dsy.map;

import com.dsy.tree.RBTree;

public class TreeMap<K, V> implements Map<K, V> {
	private static final boolean RED = false;
	private static final boolean BLACK = true;
	private RBTree<Node> tree = new RBTree<Node>();
	
	private java.util.Comparator<K> comparator;
	
	public TreeMap() {
		this(null);
	}
	
	public TreeMap(java.util.Comparator<K> comparator) {
		this.comparator = comparator;
	}
	
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
				parentString = parent.toString();
			}
			return "{"+ key +":"+ value +"}" + "_p(" + parentString + ")";
		}
		
		public Node<K, V> sibling() {
			if (isLeftChild()) {
				return parent.right;
			} 
			if (isRightChild()) {
				return parent.left;
			}
			return null;
		}
	}
	
	private int size;
	private Node<K, V> root;
	

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		root = null;
		size = 0;
	}

	@Override
	public V put(K key, V value) {
		// TODO Auto-generated method stub
		elementNotNullCheck(key);
		if (root == null) {
			// 添加空节点
			root = new Node<>(key, value, null);
			size++;
			afterPut(root);
			return null;
		}
		// 添加的不是第一个节点
		// 找到父节点
		Node<K, V> node = root;
		Node<K, V> parent = root;
		int cmp = 0;
		while (node != null) {
			cmp = compare(key, node.key);
			parent = node;
			if (cmp > 0) {
				node = node.right;
			} else if (cmp < 0) {
				node = node.left;
			} else {
				node.key = key;
				V oldValue = node.value;
				node.value = value;
				return oldValue;
			}
		}
		Node<K, V> newNode = new Node<>(key, value, parent);
		if (cmp > 0) {
			parent.right = newNode;
		} else if (cmp < 0) {
			parent.left = newNode;
		}
		size++;
		afterPut(newNode);
		
		return null;
	}
	
	private void afterPut(Node<K, V> node) {
		// TODO Auto-generated method stub
		Node<K, V> parent = node.parent;
		// 根节点
		if (parent == null) {
			black(node);
			return;
		}
		if (isBlack(parent)) return;
		
		// uncle节点
		Node<K, V> uncle = parent.sibling();
		// 祖父节点
		Node<K, V> grand = parent.parent;
		if (isRed(uncle)) {
			black(parent);
			black(uncle);
			// 祖父节点当做新添加的节点
			afterPut(red(grand));
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
	
	private void rotateLeft(Node<K, V> grand) {
		Node<K, V> parent = grand.right;
		Node<K, V> child = parent.left;
		grand.right = child;
		parent.left = grand;
		
		afterRotate(grand, parent, child);
	}
	
	private void rotateRight(Node<K, V> grand) {
		Node<K, V> parent = grand.left;
		Node<K, V> child  = parent.right;
		grand.left = child;
		parent.right = grand;
		
		afterRotate(grand, parent, child);
	}
	
	private void afterRotate(Node<K, V> grand, Node<K, V> parent, Node<K, V> child) {
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
	
	private Node<K, V> color(Node<K, V> node, boolean color) {
		if (node == null) return node;
		node.color = color;
		return node;
	}
	
	private Node<K, V> red(Node<K, V> node) {
		return color(node, RED);
	}
	private Node<K, V> black(Node<K, V> node) {
		return color(node, BLACK);
	}
	private boolean colorOf(Node<K, V> node) {
		return node == null ? BLACK : node.color;
	}
	
	private boolean isBlack(Node<K, V> node) {
		return colorOf(node) == BLACK;
	}
	private boolean isRed(Node<K, V> node) {
		return colorOf(node) == RED;
	}
	
	/*
	 * return 0  e1==e2
	 * return 1  e1>e2
	 * return -1 e1<e2
	 */
	private int compare(K k1, K k2) {
		if (comparator != null) {
			return comparator.compare(k1, k2);
		}
		return ((java.lang.Comparable<K>)k1).compareTo(k2);
	}
	
	private void elementNotNullCheck(K key) {
		if (key == null) {
			throw new IllegalArgumentException("节点不能为空");
		}
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
	
	private Node<K, V> node(K key) {
		Node<K, V> node = root;
		while (node != null) {
			int cmp = compare(key, node.key);
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
