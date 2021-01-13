package com.dsy.sort;

public class HeapSort<E extends Comparable<E>> extends Sort<E> {
	
	private int heapSize;

	@Override
	protected void sort() {
		// TODO Auto-generated method stub
		heapSize = array.length;
		// 原地建堆
		for (int i = (heapSize >> 1) - 1; i >= 0; i--) {
			siftDown(i);
		}
		
		while (heapSize > 1) {
			// 交换对定远和尾部元素
			swap(0, --heapSize);
			
			// 对0未知进行siftdown(回复对的性质)
			siftDown(0);
		}
	}
	
	private void siftDown(int index) {
		int half = heapSize >> 1;
		// 第一个叶子节点的索引完全就是非叶子节点个数
		E element = array[index];
		while (index < half) { // 保证index位置是非也自己点
			// index的节点有两种情况
			// 1,只有左子节点
			// 2,同事有右子子节点
			// 默认为左子节点的索引,左子节点相比较
			int childIndex = (index << 1) + 1;
			E child = array[childIndex];
			
			// 柚子节点
			int rightIndex = childIndex + 1;
			// 选出左右子节点最大 的那个
			if (rightIndex < heapSize && cmp(array[rightIndex], child) > 0) {
				child = array[childIndex = rightIndex];
			}
			
			if (cmp(element, child) >= 0) break;
			
			// 将子节点存放到index位置
			array[index] = child;
			// 重新设置index
			index = childIndex;
		}
		array[index] = element;
	}

}


