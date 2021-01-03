package com.dsy.map;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

import com.dsy.tree.printer.BinaryTreeInfo;
import com.dsy.tree.printer.BinaryTrees;

@SuppressWarnings({"unchecked", "rawtypes"})
public class HashMap<K, V> implements Map<K, V> {
	private static final boolean RED = false;
	private static final boolean BLACK = true;
	private static final int DEFAULT_CAPACITY = 1 << 4;
	private static final float DEFAULT_LOAD_FACTOR = 0.75f;
	
	private int size;
	
	private Node<K, V>[] table;
	
	public HashMap() {
		table = new Node[DEFAULT_CAPACITY];
	}

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
		if (size == 0) return ;
		for (int i = 0; i < table.length; i++) {
			table[i] = null;
		}
		size = 0;
	}

	@Override
	public V put(K key, V value) {
		// TODO Auto-generated method stub
		resize();
		int index = index(key);
		// 取出index未知的红黑树根节点
		Node<K, V> root = table[index]; 
		if (root == null) {
			root = this.createNode(key, value, null);
			table[index] = root;
			size ++;
			fixAfterPut(root);
			return null;
		}
		
		// 根节点不为空，添加新节点到红黑树上
		// 添加的不是第一个节点
		// 找到父节点
		Node<K, V> node = root;
		Node<K, V> parent = root;
		int cmp = 0;
		K k1 = key;
		int  h1 =  hash(k1);
		Node<K, V> result = null;
		boolean searched = false;
		do {
			parent = node;
			K k2 = node.key;
			int h2 = node.hash;
			if (h1 > h2) {
				cmp = 1;
			} else if (h1 < h2) {
				cmp = -1;
			} else if (Objects.equals(k1, k2)) {
				cmp = 0;
			} else if (k1 != null && k2 != null
					&& k1.getClass()  ==  k2.getClass()
					&& k1 instanceof Comparable
					&& (cmp = ((Comparable)k1).compareTo(k2)) != 0) {
				// 可比较性
			} else if (searched) { 
				cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
			} else {
				// 先烧苗，然后再根据内存地址大小比较
				if ((node.left != null && (result = node(node.left, k1)) != null)
						|| (node.right != null && (result = node(node.right, k1)) != null)) {
					// 找到了
					node = result;
					cmp = 0;
				} else {
					searched = true;
					// 不存在这个key，使用地址比较
					cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
//					cmp = 1;
				}
			}
			
			if (cmp > 0) {
				node = node.right;
			} else if (cmp < 0) {
				node = node.left;
			} else {
				V oldValue = node.value;
				node.key = key;
				node.value = value;
				node.hash = h1;
				return oldValue;
			}
		} while (node != null);
		
		Node<K, V> newNode = this.createNode(key, value, parent);
		if (cmp > 0) {
			parent.right = newNode;
		} else if (cmp < 0) {
			parent.left = newNode;
		}
		size++;
		fixAfterPut(newNode);
		
		return null;
	}
	
	

	@Override
	public V get(K key) {
		// TODO Auto-generated method stub
		Node<K, V> node = node(key);
		return node != null ? node.value : null;
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
		if (size == 0) return false;
		
		Queue<Node<K, V>> queue = new LinkedList<>(); 
		for (int i = 0; i < table.length; i++) {
			if (table[i] == null) continue;
			
			queue.offer(table[i]);
			while (!queue.isEmpty()) {
				Node<K, V> node = queue.poll();
				if (Objects.equals(value, node.value)) {
					return true;
				}
				if (node.left != null) {
					queue.offer(node.left);
				}
				if (node.right != null) {
					queue.offer(node.right);
				}
			}
		}
		return false;
	}

	@Override
	public void traversal(Visitor<K, V> visitor) {
		// TODO Auto-generated method stub
		if (size == 0 || visitor == null) return;
		
		Queue<Node<K, V>> queue = new LinkedList<>(); 
		for (int i = 0; i < table.length; i++) {
			if (table[i] == null) continue;
			
			queue.offer(table[i]);
			while (!queue.isEmpty()) {
				Node<K, V> node = queue.poll();
				if (visitor.visit(node.key, node.value)) return;
				if (node.left != null) {
					queue.offer(node.left);
				}
				if (node.right != null) {
					queue.offer(node.right);
				}
			}
		}
	}
	
	
	// 生成索引
	private int index(K key) {
		return hash(key) & (table.length - 1);
	}
	
	// 生成索引
	private int index(Node<K, V> node) {
		return node.hash & (table.length - 1);
	}
	
	private int hash(K key) {
		if (key == null) return 0;
		int hash = key.hashCode();
		return (hash ^ (hash >>> 16));
	}
	
	private void resize() {
		// 装填因子 <= 0.75
		if (size * 1.0 / table.length <= DEFAULT_LOAD_FACTOR) {
			return;
		}
		Node<K, V>[] oldTable = table;
		this.table = new Node[oldTable.length << 1];
//		size = 0;
		
		Queue<Node<K, V>> queue = new LinkedList<>(); 
		for (int i = 0; i < oldTable.length; i++) {
			if (oldTable[i] == null) continue;
			
			queue.offer(oldTable[i]);
			while (!queue.isEmpty()) {
				Node<K, V> node = queue.poll();
				
				if (node.left != null) {
					queue.offer(node.left);
				}
				if (node.right != null) {
					queue.offer(node.right);
				}
				// 不影响移动
				moveNode(node);
			}
		}
	}
	
	private void moveNode(Node<K, V> newNode) {
		// 重置
		newNode.parent = null;
		newNode.left = null;
		newNode.right = null;
		newNode.color = RED;
		
		int index = index(newNode);
		// 取出index未知的红黑树根节点
		Node<K, V> root = table[index]; 
		if (root == null) {
			root = newNode;
			table[index] = root;
			fixAfterPut(root);
			return;
		}
		
		// 根节点不为空，添加新节点到红黑树上
		// 添加的不是第一个节点
		// 找到父节点
		Node<K, V> node = root;
		Node<K, V> parent = root;
		int cmp = 0;
		K k1 = newNode.key;
		int  h1 = newNode.hash;
		do {
			parent = node;
			K k2 = node.key;
			int h2 = node.hash;
			if (h1 > h2) {
				cmp = 1;
			} else if (h1 < h2) {
				cmp = -1;
			} else if (k1 != null && k2 != null
					&& k1.getClass()  ==  k2.getClass()
					&& k1 instanceof Comparable
					&& (cmp = ((Comparable)k1).compareTo(k2)) != 0) {
				// 可比较性
			} else  { 
				// 不用搜索，不用equals
				cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
			} 
			
			// cmp不可能为0
			if (cmp > 0) {
				node = node.right;
			} else if (cmp < 0) {
				node = node.left;
			} 
		} while (node != null);
		
		newNode.parent = parent;
		if (cmp > 0) {
			parent.right = newNode;
		} else if (cmp < 0) {
			parent.left = newNode;
		}
		
		fixAfterPut(newNode);
	}
	
	protected static class Node<K, V> {
		private static final boolean RED = false;
		int hash;
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
			int hash =  key == null ? 0 : key.hashCode();
			this.hash = (hash ^ (hash >>> 16));
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
//			String parentString = "null";
//			if (parent != null) {
//				parentString = parent.toString();
//			}
//			return "{"+ key +":"+ value +"}";
			return "Node_" + (this.color == RED ? "R" : "B") + key + "_" + value;
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
	
	protected Node<K, V> createNode(K key, V value, Node<K, V> parent) {
		return new Node<>(key, value, parent);
	}
	protected void afterRemove(Node<K, V> removeNode) {
	}
	protected void afterRemove(Node<K, V> willNode, Node<K, V> removeNode) {
	}
	
	private void fixAfterPut(Node<K, V> node) {
		Node<K, V> parent = node.parent;
		
		// 添加的是根节点 或者 上溢到达了根节点
		if (parent == null) {
			black(node);
			return;
		}
		
		// 如果父节点是黑色，直接返回
		if (isBlack(parent)) return;
		
		// 叔父节点
		Node<K, V> uncle = parent.sibling();
		// 祖父节点
		Node<K, V> grand = red(parent.parent);
		if (isRed(uncle)) { // 叔父节点是红色【B树节点上溢】
			black(parent);
			black(uncle);
			// 把祖父节点当做是新添加的节点
			fixAfterPut(grand);
			return;
		}
		
		// 叔父节点不是红色
		if (parent.isLeftChild()) { // L
			if (node.isLeftChild()) { // LL
				black(parent);
			} else { // LR
				black(node);
				rotateLeft(parent);
			}
			rotateRight(grand);
		} else { // R
			if (node.isLeftChild()) { // RL
				black(node);
				rotateRight(parent);
			} else { // RR
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
		Node<K, V> child = parent.right;
		grand.left = child;
		parent.right = grand;
		afterRotate(grand, parent, child);
	}
	
	private void afterRotate(Node<K, V> grand, Node<K, V> parent, Node<K, V> child) {
		// 让parent称为子树的根节点
		parent.parent = grand.parent;
		if (grand.isLeftChild()) {
			grand.parent.left = parent;
		} else if (grand.isRightChild()) {
			grand.parent.right = parent;
		} else { // grand是root节点
			table[index(grand)] = parent;
		}
		
		// 更新child的parent
		if (child != null) {
			child.parent = grand;
		}
		
		// 更新grand的parent
		grand.parent = parent;
	}
	
	protected V remove(Node<K, V> node) {
		if (node == null) return null;
		Node<K, V> willNode = node;
		size --;
		
		V oldValue = node.value;
		// 度为2的节点
		if (node.hasTwoChildren()) {
			// 前驱或者后继
			Node<K, V> s = successor(node);
			// 用后继节点的值覆盖度为2 的节点的值
			node.key = s.key;
			node.value = s.value;
			node.hash = s.hash;
			// 删除后继节点
			node = s;
		}
		
		// 删除node节点（node的度避让是1或者0）
		Node<K, V> replacement = node.left != null ? node.left : node.right;
		int index = index(node);
		if (replacement != null)  {
			// node必定为1的节点
			// 更改paren
			replacement.parent = node.parent;
			if (node.parent == null) {
				// 度为1的节点，并且是根节点
				table[index] = replacement;
			} else if (node == node.parent.left) {
				replacement.parent.left = replacement;
			} else {
				replacement.parent.right = replacement;
			}
			
			// 被删除的节点
//			fixAfterRemove(node, replacement);
			fixAfterRemove(replacement);
		} else if (node.parent == null) { // 叶子节点并且是根节点
			table[index] = null;
			
			// 被删除的节点
//			fixAfterRemove(node, null);
//			fixAfterRemove(node);
		} else { // 叶子节点，当不是根节点
			if (node == node.parent.right) {
				node.parent.right = null;
			} else {
				node.parent.left = null;
			}
			// 被删除的节点
			fixAfterRemove(node);
		}
		
		// 交给子类去处理
		afterRemove(willNode, node);
		
		return oldValue;
	}
	private void fixAfterRemove(Node<K, V> node) {
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
					fixAfterRemove(parent);
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
					fixAfterRemove(parent);
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
	private int compare(K k1, K k2, int h1, int h2) {
		// 比较暗黑系指
		int result = h1 - h2;
		if (result != 0) return result;
		// 比较equals
		if (Objects.equals(k1, k2)) return 0;
		
		// 哈希值相同，但是不equals
		// 比较雷鸣
		if (k1 != null && k2 != null
				&& k1.getClass() ==  k2.getClass()
				&& k1 instanceof Comparable
				) {
			// 同一种类型
			return ((Comparable) k1).compareTo(k2);
		}
		// 同一类型，但不具备可比较性
		
		// k1 = null, k2 != null
		
		// k1 != null, k2 != null;
		
		return System.identityHashCode(k1) - System.identityHashCode(k2);
	}
	
	private void keyNotNullCheck(K key) {
		if (key == null) {
			throw new IllegalArgumentException("节点不能为空");
		}
	}

	
	
	private Node<K, V> node(K key) {
		Node<K, V> root = table[index(key)];
		return root == null ? null : node(root, key);
	}
	
	private Node<K, V> node(Node<K, V> node, K k1) {
		int h1 = hash(k1);
		// 查找结果
		Node<K,  V> result = null;
		int cmp = 0;
		while (node != null) {
			K k2 = node.key;
			int h2 = node.hash;
			if (h1 > h2) {
				node = node.right;
			} else if (h1 <  h2) {
				node = node.left;
			} else if (Objects.equals(k1, k2)) {
				return node;
			}  else if (k1 != null && k2 != null
					&& k1.getClass() == k2.getClass()
					&& k1 instanceof  Comparable
					&& (cmp = ((Comparable)k1).compareTo(k2)) != 0) {
				node = cmp > 0 ? node.right : node.left;
			} else {
				// 哈希值相等，不具有比较性，也不equals
				if(node.right != null && (result =  node(node.right, k1)) != null) {
					return result;
				}  else {
					// 不用走地柜
					node = node.left;
				}
//				else if(node.left != null && (result =  node(node.left, k1)) != null) {
//					return result;
//				} else {
//					return null;
//				}
			}
		}
		return null;
	}
	
	public void print() {
		if (size == 0) return;
		for (int i = 0; i < table.length; i++) {
			final Node<K, V> root = table[i];
			System.out.println("【index = " + i + "】");
			BinaryTrees.println(new BinaryTreeInfo() {
				@Override
				public Object string(Object node) {
					return node;
				}
				
				@Override
				public Object root() {
					return root;
				}
				
				@Override
				public Object right(Object node) {
					return ((Node<K, V>)node).right;
				}
				
				@Override
				public Object left(Object node) {
					return ((Node<K, V>)node).left;
				}
			});
			System.out.println("---------------------------------------------------");
		}
	}

}
