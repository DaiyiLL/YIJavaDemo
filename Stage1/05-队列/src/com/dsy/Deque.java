package com.dsy;

import com.dsy.list.LinkedHeadList;
import com.dsy.list.List;

public class Deque<E> {
	
	private List<E> list = new LinkedHeadList<E>();
	
	public int size() {
		return list.size();
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	public void enQueueRear(E element) {
		list.add(element);
	}
	
	public E deQueueRear() {
		return list.removeIndex(list.size() - 1);
	}
	
	public void enQueueFront(E element) {
		list.add(0, element);
	}
	
	public E deQueueFront() {
		return list.removeIndex(0);
	}
	
	public E front() {
		return list.get(0);
	}
	public E rear() {
		return list.get(list.size() - 1);
	}

}
