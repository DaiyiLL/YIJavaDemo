package com.dsy.sort.comparable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.dsy.sort.Sort;

public class ShellSort<E extends Comparable<E>> extends Sort<E> {

//	private List<Integer> stepSequence; // {8, 4, 2, 1}
	
	@Override
	protected void sort() {
		List<Integer> stepSequence = sedgewickStepSequence();
		// TODO Auto-generated method stub
		for (Integer step : stepSequence) {
			sort(step);
		}
	}
	
	/*
	 * 分成多少列进行排序
	 */
	private void sort(int step) {
		for (int col = 0; col < step; col++) {
			for (int begin = col + step; begin < array.length; begin += step) {
				int  cur = begin;
				while (cur > col && cmp(cur, cur - step) < 0) {
					swap(cur, cur - step);
					cur -= step;
				}
			}
		}
	}
	
	private List<Integer> shellStepSequence() {
		List<Integer> stepSequence = new ArrayList<>();
		int step = array.length;
		
		while ((step >>= 1) > 0) {
			stepSequence.add(step);
		}
		
		return stepSequence;
	}
	
	private List<Integer> sedgewickStepSequence() {
		List<Integer> stepSequence = new LinkedList<>();
		int k = 0, step = 0;
		while (true) {
			if (k % 2 == 0) {
				int pow = (int) Math.pow(2, k >> 1);
				step = 1 + 9 * (pow * pow - pow);
			} else {
				int pow1 = (int) Math.pow(2, (k - 1) >> 1);
				int pow2 = (int) Math.pow(2, (k + 1) >> 1);
				step = 1 + 8 * pow1 * pow2 - 6 * pow2;
			}
			if (step >= array.length) break;
			stepSequence.add(0, step);
			k++;
		}
		return stepSequence;
	}
	
//	private List<Integer> myStepSequence() {
//			
//	}
	
	@Override
		protected boolean isStable() {
			// TODO Auto-generated method stub
			return false;
		}
}
