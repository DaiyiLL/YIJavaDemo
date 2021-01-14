package com.dsy;

import java.util.Arrays;

import com.dsy.sort.CountingSort;
import com.dsy.sort.RadixSort;
import com.dsy.sort.Sort;
import com.dsy.sort.comparable.BubbleSort2;
import com.dsy.sort.comparable.HeapSort;
import com.dsy.sort.comparable.InsertionSort;
import com.dsy.sort.comparable.InsertionSort2;
import com.dsy.sort.comparable.InsertionSort3;
import com.dsy.sort.comparable.Integers;
import com.dsy.sort.comparable.MergeSort;
import com.dsy.sort.comparable.QuickSort;
import com.dsy.sort.comparable.SelectionSort;
import com.dsy.sort.comparable.ShellSort;
import com.dsy.tools.Asserts;
import com.dsy.tools.Times;

@SuppressWarnings("rawtypes")
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int[] array = {2, 4, 6, 8, 10};
//		
//		Asserts.test(BinarySearch.indexOf(array, 4) == 1);
//		Asserts.test(BinarySearch.indexOf(array, 2) == 0);
//		Asserts.test(BinarySearch.indexOf(array, 10) == 4);
//		Asserts.test(BinarySearch.indexOf(array, 56) == -1);

		Integer[] array = Integers.random(20000, 1, 20000);
//		testSorts(array, 
//				new HeapSort(), 
//				new SelectionSort(), 
//				new BubbleSort2(), 
//				new InsertionSort());
		
//		Integer[] array = {7, 3, 5, 8, 6, 7, 4, 5};
		testSorts(array, 
//				new HeapSort(), 
//				new SelectionSort(), 
//				new BubbleSort2(), 
//				new InsertionSort(),
//				new InsertionSort2<>(),
//				new InsertionSort3<>(),
				new MergeSort<Integer>(),
				new QuickSort<Integer>(),
//				new ShellSort<Integer>(),
				new CountingSort(),
				new RadixSort()
				);
	}
	
	@SuppressWarnings("unchecked")
	static void testSorts(Integer[] array, Sort... sorts) {
		for (Sort sort : sorts) {
			Integer[] newArray = Integers.copy(array);
			sort.sort(newArray);
			Asserts.test(Integers.isAscOrder(newArray));
//			Integers.println(newArray);
		}
		Arrays.sort(sorts);
		for (Sort sort : sorts) {
			System.out.println(sort);
		}
	}
	
	static void test00() {
		Integer[] array1 = Integers.random(10, 1, 100);
//		Integer[] array1 = Integers.tailAscOrder(1, 100000, 2000);  
		Integer[] array2 = Integers.copy(array1);
		Integer[] array3 = Integers.copy(array1);
		Times.test("冒泡排序", () -> {
			bubbleSorted1(array1);
		});
		Integers.println(array1);
		
		System.out.println("--------------------------");
		Times.test("选择排序", () -> {
			SelectionSort.selectionSort(array2);
		});
		Integers.println(array2);
	}
	
	static void sort_01_bubble() {
//		int[] array = {10, 9, 19, 28, 37, 56, 34};
//		int[] array = {9, 10, 19, 28, 34, 37, 56};
//		Integer[] array1 = Integers.random(1000, -100, 100);
		Integer[] array1 = Integers.tailAscOrder(1, 100000, 2000);  
//		Integer[] array1 = Integers.ascOrder(1, 100000);
		Integer[] array2 = Integers.copy(array1);
		Integer[] array3 = Integers.copy(array1);
		Times.test("冒泡排序", () -> {
			bubbleSorted1(array1);
		});
		Times.test("冒泡排序", () -> {
			bubbleSorted2(array2);
		});
		
		Times.test("冒泡排序", () -> {
			bubbleSorted3(array3);
		});
		
		
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
