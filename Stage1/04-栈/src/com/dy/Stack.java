package com.dy;

import com.dy.list.ArrayList;

public class Stack<E> {
	
	private ArrayList<E> list = new ArrayList<E>();
	
	public int size() {
		return list.size();
	}
	
	public void clear() {
		list.clear();
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	public void push(E element) {
		list.add(element);
	}
	
	public E pop() {
//		if (size == 0) return null;
		return list.removeIndex(list.size() - 1);
	}
	
	public E top() {
		return list.get(list.size() - 1);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return list.toString();
	}

}
