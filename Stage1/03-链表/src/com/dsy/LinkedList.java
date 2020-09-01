package com.dsy;

public class LinkedList<E> extends AbstractList<E> {
	
	private Node<E> first;
	
	private static class Node<E> {
		E element;
		Node<E> next;
		
		public Node(E element, Node<E> next) {
			super();
			this.element = element;
			this.next = next;
		}
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		size = 0;
		first = null;
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
		if (index == 0) {
			first = new Node<E>(element, first);
		} else {
			Node<E> pre = node(index - 1);
			pre.next = new Node<E>(element, pre.next);
		}
		
		size++;
	}

	@Override
	public E remove(int index) {
		// TODO Auto-generated method stub
		rangeCheck(index);
		Node<E> node = first;
		if (index == 0) {
			first = first.next;
		} else {
			Node<E> pre = node(index - 1);
			node = pre.next;
			pre.next = pre.next.next;
		}
		size --;
		
		return node.element;
	}



	@Override
	public int indexOf(E element) {
		// TODO Auto-generated method stub
		if (element == null) {
			Node<E> node = first;
			for (int i = 0; i < size; i++) {
				// == 只能是地址，  equals可以自定义相等（例如年龄相等）
				if (node.element == null) return i;
				node = node.next;
			}
		} else {
			for (int i = 0; i < size; i++) {
				Node<E> node = first;
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
		
		Node<E> node = first;
		for ( int i = 0; i < index; i++) {
			node = node.next;
		}
		
		return node;
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
}
