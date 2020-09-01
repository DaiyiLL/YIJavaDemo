package com.dsy.doubleLinkedList;

import com.dsy.AbstractList;

public class DoubleLinkedList<E> extends AbstractList<E> {
	
	private Node<E> first;
	private Node<E> last;
	
	public DoubleLinkedList() {
//		first = new Node<E>(null, null, null);
//		last = first;
		first = null;
		last = null;
	}
	
	private static class Node<E> {
		E element;
		Node<E> next;
		Node<E> pre;
		
		public Node(E element, Node<E> next, Node<E> pre) {
			super();
			this.element = element;
			this.next = next;
			this.pre = pre;
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			StringBuilder sb = new StringBuilder();
			if (pre != null) {
				sb.append(pre.element);
			}
			sb.append("_").append(element).append("_");
			if (next != null) {
				sb.append(next.element);
			}
			return sb.toString();
		}
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		size = 0;
		first = null;
		last = null;
	}



	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		rangeCheck(index);
		return node(index).element;
	}

	@Override
	public E set(int index, E element) {
		// TODO Auto-generated method stub
		rangeCheck(index);
		Node<E> node = node(index);
		
		E old = node.element;
		node.element = element;
		
		return old;
	}

	@Override
	public void add(int index, E element) {
		rangeCheckForAdd(index);
		// TODO Auto-generated method stub
		if (index == size) {
			Node<E> oldLast = last;
			last = new Node<E>(element, null, oldLast);
			if (oldLast == null) {
				first = last;
			} else {
				oldLast.next = last;
			}
		} else {
			Node<E> next = node(index);
			Node<E> prev = next.pre;
			Node<E> node = new Node<E>(element, next, prev);
			next.pre = node;
				
			if (prev == null) {
				first = node;
			} else {
				prev.next = node;
			}
		}
		
		
		size++;
	}

	@Override
	public E remove(int index) {
		// TODO Auto-generated method stub
		rangeCheck(index);
		
		Node<E> node = node(index);
		Node<E> prev = node.pre;
		Node<E> next = node.next;
		if (prev == null) {
			first = next;
		} else {
			prev.next = next;
		}
		if (next == null) {
			last = prev;
		} else {
			next.pre = prev;
		}
		
		
		size --;
		
		return node.element;
	}



	@Override
	public int indexOf(E element) {
		// TODO Auto-generated method stub
		if (element == null) {
			Node<E> node = first.next;
			for (int i = 0; i < size; i++) {
				// == 只能是地址，  equals可以自定义相等（例如年龄相等）
				if (node.element == null) return i;
				node = node.next;
			}
		} else {
			for (int i = 0; i < size; i++) {
				Node<E> node = first.next;
				if (node.element == null) {
					node = node.next;
					continue;
				}
				
				// == 只能是地址，  equals可以自定义相等（例如年龄相等）
				if (element.equals(node.element)) return i;
				node = node.next;
			}
		}
		
		return ELEMENT_NOT_FOUND;
	}
	
	private Node<E> node (int index) {
		rangeCheck(index);
		
		if (index < (size >> 1)) {
			Node<E> node = first;
			for ( int i = 0; i < index; i++) {
				node = node.next;
			}
			return node;
		} else {
			Node<E> node = last;
			for ( int i = size - 1; i > index; i--) {
				node = node.pre;
			}
			return node;
		}
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder string = new StringBuilder();
		Node<E> node = first;
		string.append("size = ").append(size).append(", [");
		for (int i = 0; i < size; i++) {
			if (i != 0) {
				string.append(", ");
			}
			string.append(node);
			node = node.next;
		}
		
		string.append("]");
		return string.toString();
	}
	
	public String toReverseString() {
		StringBuilder string = new StringBuilder();
		Node<E> node = last;
		string.append("size = ").append(size).append(", [");
		for (int i = 0; i < size; i++) {
			if (i != 0) {
				string.append(", ");
			}
			string.append(node);
			node = node.pre;
		}
		
		string.append("]");
		return string.toString();
	}
}
