package com.dsy;

@SuppressWarnings("unchecked")
public class ArrayList2<E> {
	
	private int size ;
	
	private E[] elements;
	
	private static final int DEFAULT_CAPATICY = 10;
	private static final int ELEMENT_NOT_FOUND = -1;
	private static final int MAC_CAPATICY_ADD = 100;
	
	public ArrayList2(int capaticy) {
		if (capaticy < DEFAULT_CAPATICY) {
			capaticy = DEFAULT_CAPATICY;
		}
		elements = (E[])new Object[capaticy];
	}
	
	public ArrayList2() {
		this(DEFAULT_CAPATICY);
	}
	
	/**
	 * 
	 */
	public void clear() {
		for (int i = 0; i < size; i++) {
			elements[i] = null;
		}
		size = 0;
		
		if (elements != null && elements.length > DEFAULT_CAPATICY) {
			elements = (E[]) new Object[DEFAULT_CAPATICY];
		}
	}
	
	public int size() {
		return size;
	}
	
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public boolean contains(E element) {
		return indexOf(element) != ELEMENT_NOT_FOUND;
	}
	
	public void add(E element) {
		add(size, element);
	}
	
	public E get(int index) { // O(1)
		rangeCheck(index);
		return elements[index];  
	}
	
	public E set(int index, E element) { // O(1)
		rangeCheck(index);
		E old = elements[index];
		elements[index] = element;
		
		return old;
	}
	
	public void add(int index, E element) { // O(n)
		rangeCheckForAdd(index);
		
		ensureCapacity(size + 1);
		
		for (int i = size; i > index; i--) {
			elements[i] = elements[i - 1]; 
		}
		
//		for (int i = size - 1; i >= index; i--) {
//			elements[i + 1] = elements[i]; 
//		}
		elements[index] = element;
		size ++;
	}
	
	public E removeIndex(int index) { // O(n)
		rangeCheck(index);
		E oldValue = elements[index];
		for (int i = index + 1; i < size; i++) {
			elements[i - 1] = elements[i];
		}
		// 将最后一个元素清空
		elements[--size] = null;
		
		trim();
//		size--;
		return oldValue;
	}
	
	public void remove(E element) { // O(n)
		removeIndex(indexOf(element));
	}
	
	
	public int indexOf(E element) {
		if (element == null) {
			for (int i = 0; i < size; i++) {
				// == 只能是地址，  equals可以自定义相等（例如年龄相等）
				if (elements[i] == null) return i;
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (elements[i] == null) continue;
				
				// == 只能是地址，  equals可以自定义相等（例如年龄相等）
				if (element.equals(elements[i])) return i;
			}
		}
		
		return ELEMENT_NOT_FOUND;
	}
	
	private void trim() {
		int capacity = elements.length;
		int newCapacity = capacity >> 1;
		if (size >= newCapacity || capacity <= DEFAULT_CAPATICY) return;
		if (newCapacity < DEFAULT_CAPATICY) newCapacity = DEFAULT_CAPATICY;
		// 剩余空间很多
		E[] newElements = (E[]) new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			newElements[i] = elements[i];
		}
		elements = newElements;
		
		System.out.println(capacity + " 缩容为: " + newCapacity);
	}
	
	private void ensureCapacity(int capacity) {
		int oldCapacity = elements.length;
		if (oldCapacity >= capacity) return;
		
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		E[] newElements = (E[])new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			newElements[i] = elements[i];
		}
		elements = newElements;
		
		System.out.println(oldCapacity + " newCapacity: " + newCapacity);
	}
	
	private void rangeCheck(int index) {
		if (index < 0 || index >= size) {
			this.outOfBounds(index);
		}
	}
	
	private void rangeCheckForAdd(int index) {
		if (index < 0 || index > size) {
			this.outOfBounds(index);
		}
	}
	
	private void outOfBounds(int index) {
		throw new IndexOutOfBoundsException("Index: " + index + ", Size:" + size);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		// size = 3, [99, 88, 77]
		StringBuilder string = new StringBuilder();
		string.append("size = ").append(size).append(", [");
		for (int i = 0; i < size; i++) {
			if (i != 0) {
				string.append(", ");
			}
			string.append(elements[i]);
		}
		
		string.append("]");
		return string.toString();
	}
	
	

}
