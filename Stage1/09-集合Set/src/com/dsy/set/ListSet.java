package com.dsy.set;

import java.util.LinkedList;
import java.util.List;

public class ListSet<E> implements Set<E> {

	private List<E> list = new LinkedList<E>();
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return list.isEmpty();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		list.clear();
	}

	@Override
	public boolean contains(E element) {
		// TODO Auto-generated method stub
		return list.contains(element);
	}

	@Override
	public void add(E element) {
		// TODO Auto-generated method stub
		int index = list.indexOf(element);
		if (index != -1) {
			list.set(index, element);
		} else {
			list.add(element);
		}
	}

	@Override
	public void remove(E element) {
		// TODO Auto-generated method stub
		int index = list.indexOf(element);
		if (index != -1) {
			list.set(index, element);
		} 
	}

	@Override
	public void traversal(Visitor<E> visitor) {
		// TODO Auto-generated method stub
		for (int i = 0; i < list.size(); i++) {
			if (visitor.visit(list.get(i))) {
				return;
			}
		}
	}

}
