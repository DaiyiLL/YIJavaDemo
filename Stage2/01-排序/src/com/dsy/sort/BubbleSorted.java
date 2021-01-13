package com.dsy.sort;

public class BubbleSorted<E extends Comparable<E>> extends Sort<E> {

	@Override
	protected void sort() {
		// TODO Auto-generated method stub
		for (int end = array.length - 1; end > 0; end--) {
			for (int begin = 1; begin <= end; begin++) {
				if (cmp(begin, begin - 1) < 0) {
					swap(begin, begin - 1);
				}
			}
		}
	}
	
	static void bubbleSorted1(Integer[] array) {
		for (int end = array.length; end > 0; end--) {
			for (int begin = 1; begin < end; begin++) {
				if (array[begin] < array[begin - 1]) {
					int tmp = array[begin];
					array[begin] = array[begin - 1];
					array[begin - 1] = tmp;
				}
			}
		}
	}
	
	static void bubbleSorted2(Integer[] array) {
		for (int end = array.length; end > 0; end--) {
			boolean  isSorted =  true;
			for (int begin = 1; begin < end; begin++) {
				if (array[begin] < array[begin - 1]) {
					int tmp = array[begin];
					array[begin] = array[begin - 1];
					array[begin - 1] = tmp;
					isSorted = false;
				}
			}
			// 有序，跳出循环
			if (isSorted) {
				break;
			}
		}
	}
	
	static void bubbleSorted3(Integer[] array) {
		for (int end = array.length - 1; end > 0; end--) {
			int sortIndex = 1;
			for (int begin = 1; begin <= end; begin++) {
				if (array[begin] < array[begin - 1]) {
					int tmp = array[begin];
					array[begin] = array[begin - 1];
					array[begin - 1] = tmp;
					sortIndex = begin;
				}
			}
			end = sortIndex;
		}
	}

}
