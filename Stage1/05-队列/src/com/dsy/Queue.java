package com.dsy;

import com.dsy.list.LinkedHeadList;
import com.dsy.list.List;

public class Queue<E> {
	
	private List<E> list = new LinkedHeadList<E>();
	
	public int size() {
		return list.size();
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	public void enQueue(E element) {
		list.add(element);
	}
	
	public E deQueue() {
		return list.removeIndex(0);
	}
	
	public E front() {
		return list.get(0);
	}
	
	public void clear() {
		list.clear();
	}

}
