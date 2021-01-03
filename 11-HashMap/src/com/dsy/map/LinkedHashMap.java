package com.dsy.map;

import java.util.Objects;

import com.dsy.map.HashMap.Node;

public class LinkedHashMap<K, V> extends HashMap<K,  V> {
	private LinkedNode<K, V> first;
	private LinkedNode<K, V> last;
	
	private static class LinkedNode<K, V>  extends Node<K, V> {
		LinkedNode<K, V> prev;
		LinkedNode<K, V> next;
		public LinkedNode(K key, V value, Node<K, V> parent) {
			super(key, value, parent);
		}
	}
	
	@Override
	protected Node<K, V> createNode(K key, V value, Node<K, V> parent) {
		// TODO Auto-generated method stub
		LinkedNode<K, V> node = new LinkedNode<>(key, value, parent);
		if (first == null) {
			first = last = node;
		} else {
			last.next = node;
			node.prev = last;
			last = node;
		}
		return node;
	}
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		super.clear();
		first = null;
		last = null;
	}
	
	@Override
	public void traversal(Visitor<K, V> visitor) {
		// TODO Auto-generated method stub
//		super.traversal(visitor);
		if (visitor == null) return;
		LinkedNode<K, V> node = first;
		while (node != null) {
			if (visitor.visit(node.key, node.value)) return;
			node = node.next;
		}
	}
	
	@Override
	public boolean containsValue(V value) {
		// TODO Auto-generated method stub
		LinkedNode<K, V> node = first;
		while (node != null) {
			if (Objects.equals(value, node.value)) return true;
			node = node.next;
		}
		return false;
	}
	
	@Override
	protected void afterRemove(Node<K, V> willNode, Node<K, V> removeNode) {
		// TODO Auto-generated method stub
		LinkedNode<K, V> node1 = (LinkedNode<K, V>)willNode;
		LinkedNode<K, V> node2 = (LinkedNode<K, V>)removeNode;
		
		if (willNode != removeNode) {
			// 交换在链表中的位置
			// 交换prev
			LinkedNode<K, V> tmp = node1.prev;
			node1.prev = node2.prev;
			node2.prev = tmp;
			if (node1.prev == null) {
				first = node1;
			} else {
				node1.prev.next = node1;
			}
			if (node2.prev == null) {
				first = node2;
			} else {
				node2.prev.next = node2;
			}
			
			// 交换prev
			tmp = node1.next;
			node1.next = node2.next;
			node2.next = tmp;
			if (node1.next == null) {
				last = node1;
			} else {
				node1.next.prev = node1;
			}
			if (node2.next == null) {
				last = node2;
			} else {
				node2.next.prev = node2;
			}
		}
		
		
		LinkedNode<K, V> prev = node2.prev;
		LinkedNode<K, V> next = node2.next;
		if (prev == null) {
			// 删除的是头结点
			first = next;
		} else {
			prev.next = next;
		}
		if (next == null) {
			// 删除的是尾结点
			last = prev;
		} else {
			next.prev = prev;
		}
	}
}
