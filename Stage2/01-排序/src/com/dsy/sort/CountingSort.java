package com.dsy.sort;

public class CountingSort extends Sort<Integer> {

	@Override
	protected void sort() {
		// TODO Auto-generated method stub
		// 找出最大值
		int max = array[0];
		int min = array[0];
		for (int i = 1; i < array.length; i++) {
			if (array[i] > max) {
				max = array[i];
			}
			if (array[i] < min) {
				min = array[i];
			}
		}
		
		// 开辟内存空间，存储次数
		int[] counts = new int[max - min + 1];
		// 统计每个整数出现的次数
		for (int i = 0; i < array.length; i++) {
			counts[array[i] - min] += 1;
		}
		// 累加次数
		int count = counts[0];
		for (int i = 1; i < counts.length; i++) {
			counts[i] += count;
			count = counts[i];
		}
		
		// 从后往前比阿尼元素，将它放到有序数组中的合适位置
		int[] newArray = new int[array.length];
		for (int i = array.length - 1; i >= 0; i--) {
//			int newCount = counts[array[i] - min] - 1;
//			counts[array[i] - min] = newCount;
			newArray[--counts[array[i] - min]] = array[i];
		}
		// 将有序数组复制到array
		for (int i = 0; i < newArray.length; i++) {
			array[i] = newArray[i];
		}
	}
	
	protected void sort0() {
		// 找出最大值
		int max = array[0];
		int min = array[0];
		for (int i = 1; i < array.length; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}
		// 开辟内存空间，存储每个整数出现的次数
		int[] counts = new int[max + 1];
		// 统计每个整数出现的次数
		for (int i = 0; i < array.length; i++) {
			counts[array[i]] += 1;
		}
		
		int begin = 0;
		for (int i = 0; i < counts.length; i++) {
			while (counts[i] > 0) {
				array[begin++] = i;
				counts[i] -= 1;
			}
		}
	}
	
	@Override
	protected boolean isStable() {
		// TODO Auto-generated method stub
		return true;
	}

	

}
