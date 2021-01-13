package com.dsy.heap;

import java.util.Comparator;

import com.dsy.printer.BinaryTreeInfo;

public class BinaryHeap<E> extends AbstractHeap<E> implements BinaryTreeInfo {
	
	private E[] elements;
	private static final int DEFAULT_CAPACITY = 10;
	
	@SuppressWarnings("unchecked")
	public BinaryHeap(Comparator<E> comparator) {
		super(comparator);
		this.elements = (E[])new Object[DEFAULT_CAPACITY];
	}
	
	public BinaryHeap() {
		this(null, null);
	}
	
	public BinaryHeap(E[] elements, Comparator<E> comparator) {
		super(comparator);
		if (elements == null || elements.length == 0) {
			this.elements = (E[])new Object[DEFAULT_CAPACITY];
		} else {
			int capacity = Math.max(elements.length, DEFAULT_CAPACITY);
			this.elements = (E[]) new Object[capacity];
			for (int i = 0; i < elements.length; i++) {
				this.elements[i] = elements[i];
			}
			size = elements.length;
			heapify();
		}
	}
	
    public BinaryHeap(E[] elements) {
		this(elements, null);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		for (int i = 0; i < elements.length; i++) {
			elements[i] = null;
		}
		size = 0;
	}

	@Override
	public void add(E element) {
		// TODO Auto-generated method stub
		elementNotNullCheck(element);
		ensureCapacity(size + 1);
		
		elements[size ++] = element;
		siftUp(size - 1);
	}

	@Override
	public E get() {
		// TODO Auto-generated method stub
//		if (size == 0) return null;
		emptyCheck();
		return elements[0];
	}

	@Override
	public E remove() {
		// TODO Auto-generated method stub
		emptyCheck();
		
		E root = elements[0];
		int lastIndex = --size;
		elements[0] = elements[lastIndex];
		elements[lastIndex] = null;
		siftDown(0);
		
		return root;
	}

	@Override
	public E replace(E element) {
		// TODO Auto-generated method stub
		elementNotNullCheck(element);
		if (size == 0) {
			elements[0] = element;
			size = 1;
			return null;
		}
		E root = elements[0];
		elements[0] = element;
		siftDown(0);
		return root;
	}
	
	private void emptyCheck() {
		if (size == 0) {
			throw new IndexOutOfBoundsException("Heap is empty");
		}
	}
	private void elementNotNullCheck(E element) {
		if (element == null) {
			throw new IllegalArgumentException("element not be null");
		}
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
		
//		System.out.println(oldCapacity + " newCapacity: " + newCapacity);
	}
	
	private void siftUp(int index) {
//		E e = elements[index];
//		while (index > 0) {
//			int pindex = (index - 1) >> 1;
//			E p = elements[pindex];
//			if (compare(e, p) <= 0) {
//				return;
//			}
//			// 交换index，pindex位置的内容
//			E tmp = elements[index];
//			elements[index] = elements[pindex];
//			elements[pindex] = tmp;
//			// 重新设置index
//			index = pindex;
//		}
		
		E e = elements[index];
		while (index > 0) {
			int pindex = (index - 1) >> 1;
			E p = elements[pindex];
			if (compare(e, p) <= 0) {
				break;
			}
			// 将父元素下移
			elements[index] = p;
			// 重新设置index
			index = pindex;
		}
		elements[index] = e;
	}
	
	private void siftDown(int index) {
		int half = size >> 1;
		// 第一个叶子节点的索引完全就是非叶子节点个数
			E element = elements[index];
		while (index < half) { // 保证index位置是非也自己点
			// index的节点有两种情况
			// 1,只有左子节点
			// 2,同事有右子子节点
			// 默认为左子节点的索引,左子节点相比较
			int childIndex = (index << 1) + 1;
			E child = elements[childIndex];
			
			// 柚子节点
			int rightIndex = childIndex + 1;
			// 选出左右子节点最大 的那个
			if (rightIndex < size && compare(elements[rightIndex], child) > 0) {
				child = elements[childIndex = rightIndex];
			}
			
			if (compare(element, child) >= 0) break;
			
			// 将子节点存放到index位置
			elements[index] = child;
			// 重新设置index
			index = childIndex;
		}
		elements[index] = element;
	}

	/*
	 * 批量 建堆
	 */
	private void heapify() {
		// 自上而下的上滤
//		for (int i = 0; i < size; i++) {
//			siftUp(i);
//		}
		// 自下而上的下滤
		for (int i = (size >> 1) - 1; i >= 0; i--) {
			siftDown(i);
		}
	}
	
	
	
	@Override
	public Object root() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object left(Object node) {
		// TODO Auto-generated method stub
		Integer index = (((Integer)node) << 1) + 1;
		return index >= size ? null : index;
	}

	@Override
	public Object right(Object node) {
		// TODO Auto-generated method stub
		Integer index = (((Integer)node) << 1) + 2;
		return index >= size ? null : index;
	}

	@Override
	public Object string(Object node) {
		// TODO Auto-generated method stub
		Integer index = (Integer)node;
		return elements[index] + "";
	}

}
