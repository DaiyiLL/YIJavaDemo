package com.dsy.sort.comparable;

import com.dsy.sort.Sort;

public class BubbleSort1<E extends Comparable<E>> extends Sort<E> {

	@Override
	protected void sort() {
		// TODO Auto-generated method stub
		for (int end = array.length; end > 0; end--) {
			boolean  isSorted =  true;
			for (int begin = 1; begin < end; begin++) {
				if (cmp(begin, begin - 1) < 0) {
					swap(begin, begin - 1);
					isSorted = false;
				}
			}
			// 有序，跳出循环
			if (isSorted) {
				break;
			}
		}
	}

}
