package com.dsy.sort.comparable;

import com.dsy.sort.Sort;

public class InsertionSort3<E extends Comparable<E>> extends Sort<E> {
	
//	@Override
//	protected void sort() {
//		// TODO Auto-generated method stub
//		for (int begin = 1; begin < array.length; begin++) {
//			int searchIndex = this.search(begin);
//			E v = array[begin];
//			// 挪动元素
//			for (int i = begin; i > searchIndex; i--) {
//				array[i] = array[i - 1];
//			}
//			// 后移动
//			array[searchIndex] = v;
//		}
//		
//	}
	
	@Override
	protected void sort() {
		// TODO Auto-generated method stub
		for (int begin = 1; begin < array.length; begin++) {
			insert(begin, search(begin));
		}
	}
	
	private void insert(int source, int dest) {
		E v = array[source];
		// 挪动元素
		for (int i = source; i > dest; i--) {
			array[i] = array[i - 1];
		}
		// 后移动
		array[dest] = v;
	}
	
	
	private int search(int index) {
		E v = array[index];
		int begin = 0;
		int end = index;
		while (begin < end) {
			int mid = (begin + end) >> 1;
			if (cmp(v, array[mid]) < 0) {
				end = mid;
			} else {
				begin = mid + 1;
			}
		}
		return begin;
	}
	
	

}
