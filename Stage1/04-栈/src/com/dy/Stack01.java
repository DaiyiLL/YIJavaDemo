package com.dy;

import com.dy.list.ArrayList;

public class Stack01<E> extends ArrayList<E> {
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void push(E element) {
		add(element);
	}
	
	public E pop() {
//		if (size == 0) return null;
		return removeIndex(size - 1);
	}
	
	public E top() {
		return get(size - 1);
	}

}
