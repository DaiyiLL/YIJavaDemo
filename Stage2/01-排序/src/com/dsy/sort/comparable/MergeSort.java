package com.dsy.sort.comparable;

import com.dsy.sort.Sort;

@SuppressWarnings("unchecked")
public class MergeSort <E extends Comparable<E>> extends Sort<E> {
	
	private E[] leftArray;
//	
//	@Override
	protected void sort() {
		// TODO Auto-generated method stub
		System.out.println(array.length);
		leftArray = (E[]) new Comparable[array.length >> 1];
		sort(0, array.length);
	}
	
	/*
	 * 对begin-end范围内的数据进行合并 [begin, end)
	 */
	private void sort(int begin, int end) {
		if (end - begin < 2) return;
		int mid = (end + begin) >> 1;
		sort(begin, mid);
		sort(mid, end);
		merge(begin, mid, end);
	}
	private void merge(int begin, int mid, int end) {
		int li = 0, le = mid - begin;
		int ri = mid, re = end;
		int ai = begin;
		//  备份左边数组
		for (int i = li; i < le; i++) {
			leftArray[i] = array[begin + i];
		}
		while (li < le) {
			if (ri < re && cmp(array[ri], leftArray[li]) < 0) {
				array[ai++] = array[ri++];
			} else {
				array[ai++] = leftArray[li++];
			}
		}
	}
	

}
