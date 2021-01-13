package com.dsy.sort;

public class BubbleSort2<E extends Comparable<E>> extends Sort<E> {

	@Override
	protected void sort() {
		// TODO Auto-generated method stub
		int sortIndex = 1;
		for (int end = array.length - 1; end > 0; end--) {
			sortIndex = 1;
			for (int begin = 1; begin <= end; begin++) {
				if (cmp(begin, begin - 1) < 0) {
					swap(begin, begin - 1);
					sortIndex = begin;
				}
			}
			end = sortIndex;
		}
	}

}
