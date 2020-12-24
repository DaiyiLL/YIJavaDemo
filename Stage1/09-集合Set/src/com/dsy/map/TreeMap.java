package com.dsy.map;

import java.util.LinkedList;
import java.util.Queue;

import com.dsy.tree.RBTree;

public class TreeMap<K, V> implements Map<K, V> {
	private static final boolean RED = false;
	private static final boolean BLACK = true;
//	private RBTree<Node<K, V>> tree = new RBTree<Node<K, V>>();
	
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
			this.value = value;
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
		keyNotNullCheck(key);
		if (root == null) {
			// 添加空节点
			root = new Node<>(key, value, null);
			size++;
//			afterPut(root);
			
			root.color = BLACK;
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
	
	@Override
	public V get(K key) {
		// TODO Auto-generated method stub
		Node<K, V> node = node(key);
		return node != null ? node(key).value : null;
	}

	@Override
	public V remove(K key) {
		// TODO Auto-generated method stub
		return remove(node(key));
	}

	@Override
	public boolean containsKey(K key) {
		// TODO Auto-generated method stub
		return node(key) != null;
	}

	@Override
	public boolean containsValue(V value) {
		// TODO Auto-generated method stub
		if (root == null) return false;
		Queue<Node<K, V>> queue = new LinkedList<>();
		while (!queue.isEmpty()) {
			Node<K, V> node = queue.poll();
			if (valEquals(node.value, value)) {
				return true;
			}
			if (node.left != null) {
				queue.offer(node.left);
			}
			if (node.right != null) {
				queue.offer(node.right);
			}
		}
		return false;
	}

	@Override
	public void traversal(Visitor<K, V> visitor) {
		// TODO Auto-generated method stub
		if (visitor == null) return;
		traversal(root, visitor);
	}
	
	private void traversal(Node<K, V> node, Visitor<K, V> visitor) {
		// TODO Auto-generated method stub
		if (node == null || visitor == null) return;
		traversal(node.left, visitor);
		if (visitor.stop) return;
		visitor.visit(node.key, node.value);
		
		traversal(node.right, visitor);
	}
	
	private boolean valEquals(V v1, V v2) {
		return v1 == null ? v2 == null : v1.equals(v2);
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
	
	private V remove(Node<K, V> node) {
		if (node == null) return null;
		size --;
		
		V oldValue = node.value;
		// 度为2的节点
		if (node.hasTwoChildren()) {
			// 前驱或者后继
			Node<K, V> s = successor(node);
			// 用后继节点的值覆盖度为2 的节点的值
			node.key = s.key;
			node.value = s.value;
			// 删除后继节点
			node = s;
//			return;
		}
		
		// 删除node节点（node的度避让是1或者0）
		Node<K, V> replacement = node.left != null ? node.left : node.right;
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
			
			// 被删除的节点
//			afterRemove(node, replacement);
			afterRemove(replacement);
		} else if (node.parent == null) { // 叶子节点并且是根节点
			root = null;
			
			// 被删除的节点
//			afterRemove(node, null);
//			afterRemove(node);
		} else { // 叶子节点，当不是根节点
			if (node == node.parent.right) {
				node.parent.right = null;
			} else {
				node.parent.left = null;
			}
			// 被删除的节点
//			afterRemove(node, null);
			afterRemove(node);
		}
		return oldValue;
	}
	
	private void afterRemove(Node<K, V> node, Node<K, V> replacement) {
		// TODO Auto-generated method stub
		// 如果删除的节点是红色
		if (isRed(node)) return;
		// 如果取代的node的子节点是红色的
		if (isRed(replacement)) {
			black(replacement);
			return;
		}
		Node<K, V> parent = node.parent;
		// 删除的是根节点, 根节点不调用了
//		if (parent == null) return;
		// 删除的是黑色叶子节点
		// 被删除的node是做还是有
		boolean left = parent.left == null || node.isLeftChild();
		Node<K, V> sibling = left ? parent.right : parent.left; 
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
	
	private void afterRemove(Node<K, V> node) {
		// TODO Auto-generated method stub
		// 如果删除的节点是红色
//		if (isRed(node)) return;
		// 如果取代的node的子节点是红色的, 或者删除的几点是红色
		if (isRed(node)) {
			black(node);
			return;
		}
		Node<K, V> parent = node.parent;
		// 删除的是根节点
		if (parent == null) return;
		// 删除的是黑色叶子节点
		// 被删除的node是做还是有
		boolean left = parent.left == null || node.isLeftChild();
		Node<K, V> sibling = left ? parent.right : parent.left; 
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
	
	public Node<K, V> predecessor(Node<K, V> node) {
		if (node == null) return null;
		// 前驱节点在左子树中（left.right.right.right....）
		if (node.left != null) {
			Node<K, V> pre = node.left;
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
	
	
	public Node<K, V> successor(Node<K, V> node) {
		if (node == null) return null;
		// 后继节点在左子树中（right.left.left.left....）
		if (node.right != null) {
			Node<K, V> suc = node.right;
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
	@SuppressWarnings("unchecked")
	private int compare(K k1, K k2) {
		if (comparator != null) {
			return comparator.compare(k1, k2);
		}
		return ((Comparable<K>)k1).compareTo(k2);
	}
	
	private void keyNotNullCheck(K key) {
		if (key == null) {
			throw new IllegalArgumentException("节点不能为空");
		}
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
