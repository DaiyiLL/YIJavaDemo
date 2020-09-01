package com.dsy.circle;

@SuppressWarnings("unchecked")
public class CirCleQueue<E> {
	
	private int front;
	private int size;
	private E[] elements; 
	private static final int DEFAULT_CAPACITY = 10;
	
	public CirCleQueue() {
		elements = (E[])new Object[DEFAULT_CAPACITY];
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void clear() {
		size = 0;
		for (int i = 0; i< elements.length; i++) {
			elements[i] = null;
		}
		elements = (E[])new Object[DEFAULT_CAPACITY];
		front = 0;
	}
	
	public void enQueue(E element) {
		enLargeCapacity();
		elements[(front + size) % elements.length] = element;
		size ++;
	}
	
	public E deQueue() {
		System.out.println("front = " + front);
		E frontElement = elements[front];
		elements[front] = null;
		front = (front + 1) % elements.length; 
		size --;
		
		enSmallCapacity();
		return frontElement;
	}
	
	
	public E front() {
		if (size == 0) return null;
		return elements[front];
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder string = new StringBuilder();
		string.append("capacity=").append(elements.length).append(" size=")
		.append(size).append(", [");
		for (int i = 0; i < size; i++) {
			if (i != 0) {
				string.append(", ");
			}
			string.append(elements[(i + front) % elements.length]);
		}
		string.append("]");
		
		return string.toString();
	}
	
	private void enLargeCapacity() {
		// 需要扩容
		if (size == elements.length) {
			E[] oldElements = elements;
			int newSize = elements.length + (elements.length >> 1);
			elements = (E[])new Object[newSize];
			
			for (int i = 0; i < size; i++) {
				elements[i] = oldElements[(i + front) % oldElements.length];
			}
			front = 0;
			System.out.println("扩容为: " + newSize);
		}
		
	}
	
	private void enSmallCapacity() {
		// 需要缩减容量
		int newSize = (elements.length >> 1);
		if (size <= newSize && newSize > DEFAULT_CAPACITY) {
			E[] oldElements = elements;
			elements = (E[])new Object[newSize];
			for (int i = 0; i < size; i++) {
				elements[i] = oldElements[(i + front) % oldElements.length];
			}
			front = 0;
			System.out.println("缩容为: " + elements.length);
		}
		
	}
	
	
}
