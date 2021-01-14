package com.dsy.sort.comparable;

import com.dsy.sort.Sort;

public class SelectionSort<E extends Comparable<E>> extends Sort<E> {
	

	
	public static void selectionSort(Integer[]  array) {
		
	}

	@Override
	protected void sort() {
		// TODO Auto-generated method stub
		for (int end = array.length  - 1; end > 0; end--) {
			int maxIndex = 0;
			for (int begin = 1; begin <= end; begin++) {
				if (cmp(maxIndex, begin) <= 0) {
					maxIndex = begin;
				}
			}
			if (end != maxIndex) {
				swap(maxIndex,  end);
			}
		}
	}

}
