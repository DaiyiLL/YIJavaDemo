package com.dsy.sort.comparable;

import com.dsy.sort.Sort;

public class InsertionSort2<E extends Comparable<E>> extends Sort<E> {

	@Override
	protected void sort() {
		// TODO Auto-generated method stub
		for (int begin = 1; begin < array.length; begin++) {
			int  cur = begin;
			E v = array[cur];
			while (cur > 0 && cmp(v, array[cur - 1]) < 0) {
				array[cur] = array[cur - 1];
				cur --;
			}
			array[cur] = v;
		}
		
	}

}
