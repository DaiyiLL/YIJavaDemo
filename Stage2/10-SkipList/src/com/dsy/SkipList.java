package com.dsy;

import java.util.Comparator;

@SuppressWarnings("unchecked")
public class SkipList<K, V> {
	
	private static final int MAX_LEVEL = 32;
	private static final double P = 0.25;

	private int size;
	private Comparator<K> comparator;
	private int level;
	
	/**
	 * 不存放任何K-V
	 */
	private Node<K, V> first;
	
	public SkipList(Comparator<K> comparator) {
		this.comparator = comparator;
		first = new Node<K, V>(null, null, MAX_LEVEL);
	}
	
	public SkipList() {
		this(null);
	}
	
	public  int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public V get(K key) {
		keyCheck(key);
		
		Node<K, V> node = first;
		for (int i = level - 1; i >= 0; i--) {
			int cmp = -1;
			while (
					node.nexts[i] != null && 
					(cmp = compare(key, node.nexts[i].key)) > 0
					) {
				node = node.nexts[i];
			}
			if (cmp == 0) return node.nexts[i].value;
		}
		
		return null;
	}
	

	public V put(K key, V value) {
		keyCheck(key);
		
		Node<K, V> node = first;
		Node<K, V>[] preNodes = new Node[level];
		for (int i = level - 1; i >= 0; i--) {
			int cmp = -1;
			while (
					node.nexts[i] != null && 
					(cmp = compare(key, node.nexts[i].key)) > 0
					) {
				node = node.nexts[i];
			}
			if (cmp == 0) {
				// 本来就存在的
				V oldValue = node.nexts[i].value;
				node.nexts[i].value = value;
				return value;
			}
			// cmp < 0
			preNodes[i] = node;
		}
		// 新添加节点的前驱: node
		// 新节点的层数
		int newLvel = randomLevel();
		// 添加新节点
		Node<K, V> newNode = new Node<K, V>(key, value, newLvel);
		// 
		for (int i = 0; i < newLvel; i++) {
			if (i >= level) {
				first.nexts[i] = newNode;
				
			} else {
				// 获取前驱
				newNode.nexts[i] = preNodes[i].nexts[i];
				preNodes[i].nexts[i] = newNode;
			}
			
		}
		// 节点数量增加
		size ++;
		// 计算最终的层数
		level = Math.max(level, newLvel);
		
		return null;
	}
	
	public  V remove(K key) {
		keyCheck(key);
		
		Node<K, V> node = first;
		Node<K, V>[] preNodes = new Node[level];
		boolean exist = false;
		for (int i = level - 1; i >= 0; i--) {
			int cmp = -1;
			while (
					node.nexts[i] != null && 
					(cmp = compare(key, node.nexts[i].key)) > 0
					) {
				node = node.nexts[i];
			}
			// cmp < 0
			preNodes[i] = node;
			if (cmp == 0) exist = true;
		}
		// 不存在
		if (exist == false) return null;
		
		// 存在删除
		Node<K, V> oldNode = node.nexts[0];
		// 整体修改next
		for (int i = 0; i < oldNode.nexts.length; i++) {
			preNodes[i].nexts[i] = oldNode.nexts[i];
		}
		size --;
		// 更新level
		int newLevel = level;
		while (--newLevel >= 0 && first.nexts[newLevel] == null) {
			level = newLevel;
		}
		
		return oldNode.value;
	}
	
	private void keyCheck(K key) {
		if (key == null) {
			throw new IllegalArgumentException("key must not be null.");
		}
	}
	
	private int compare(K k1, K k2) {
		return comparator != null 
				? comparator.compare(k1, k2) 
				: ((Comparable<K>)k1).compareTo(k2);
	}
	
	private int randomLevel() {
		int level = 1;
		while (Math.random() < P && level < MAX_LEVEL) {
			level ++;
		}
		return level;
	}
	
	private static class Node<K, V> {
		K key;
		V value;
		Node<K, V>[] nexts;
		public Node(K key, V value, int level) {
			super();
			this.key = key;
			this.value = value;
			this.nexts = new Node[level];
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return key + ":" + value + "_" + nexts.length;
		}
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("一共" + level + "层").append("\n");
		for (int i = level - 1; i >= 0; i--) {
			Node<K, V> node = first;
			while (node.nexts[i] != null) {
				sb.append(node.nexts[i]);
				sb.append(" ");
				node = node.nexts[i];
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
}
