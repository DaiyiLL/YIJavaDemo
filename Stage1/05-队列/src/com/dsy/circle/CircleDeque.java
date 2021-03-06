package com.dsy.circle;

@SuppressWarnings("unchecked")
public class CircleDeque<E> {

	private int front;
	private int size;
	private E[] elements; 
	private static final int DEFAULT_CAPACITY = 10;
	
	public CircleDeque() {
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
	
	public void enQueueFront(E element) {
		enLargeCapacity();
		int newFront = index(-1);
		elements[newFront] = element;
		front = newFront;
//		System.out.println("front = " + front);
		size ++;
		
		
	}
	
	public E deQueueFront() {
//		System.out.println("front = " + front);
		E frontElement = elements[front];
		elements[front] = null;
		front = index(1); 
		size --;
		
		enSmallCapacity();
		return frontElement;
	}
	
	public void enQueueRear(E element) {
		enLargeCapacity();
		elements[index(size)] = element;
		size ++;
	}
	
	public E deQueueRear() {
		int rearIndex = index(size - 1);
		E rear = elements[rearIndex];
		elements[rearIndex] = null;
		size --;
		
		enSmallCapacity();
		return rear;
	}
	
	
	public E front() {
		return elements[front];
	}
	
	public E rear() {
		return elements[index(size - 1)];
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
			string.append(elements[index(i)]);
		}
		string.append("]");
		
		return string.toString();
	}
	
	private void enLargeCapacity() {
		// 需要扩容
		if (size == elements.length) {
			int newSize = elements.length + (elements.length >> 1);
			E[] newElements = (E[])new Object[newSize];
			
			for (int i = 0; i < size; i++) {
				newElements[i] = elements[index(i)];
			}
			front = 0;
			elements = newElements;
			System.out.println("扩容为: " + newSize);
		}
		
	}
	
	private void enSmallCapacity() {
		// 需要缩减容量
		int newSize = (elements.length >> 1);
		if (size <= newSize && newSize > DEFAULT_CAPACITY) {
			E[] newElements = (E[])new Object[newSize];
			for (int i = 0; i < size; i++) {
				newElements[i] = elements[index(i)];
			}
			front = 0;
			elements = newElements;
			System.out.println("缩容为: " + elements.length);
		}
	}
	
	private int index(int index) {
		index += front;
		if (index < 0) {
			return index + elements.length;
		}
		if (index >= elements.length) {
			return index - elements.length;
		}
		return index;
	}
}
