package com.dsy;

import java.util.Comparator;

import com.dsy.heap.BinaryHeap;
import com.dsy.printer.BinaryTrees;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test03();
		test04();
	}
	
	static void test04() {
		Integer data[] = {77, 5, 35, 67, 84, 12, 74, 71, 36, 94, 46, 93, 1, 3, 66, 61, 55, 41, 90, 14, 21, 78, 20, 52, 86, 40, 50, 38, 28, 91, 37, 15, 97, 81, 53, 60, 10, 75, 32, 79, 76, 27, 62, 89, 47, 88, 48, 85, 98, 82};
		BinaryHeap<Integer> heap = new BinaryHeap<>(null, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o2 - o1;
			}
		});
		// 最大的前5个
		int k = 5;
		for (int i = 0; i < data.length; i++) {
			if (heap.size() < k) {
				heap.add(data[i]);
			} else {
				if (data[i] > heap.get()) {
					heap.replace(data[i]);
				}
			}
		}
		BinaryTrees.println(heap);
	}
	
	static void test03() {
		Integer data[] = {77, 5, 35, 67, 84, 12, 74, 71, 36, 94, 46, 93, 1, 3, 66, 61, 55, 41, 90, 14, 21, 78, 20, 52, 86, 40, 50, 38, 28, 91, 37, 15, 97, 81, 53, 60, 10, 75, 32, 79, 76, 27, 62, 89, 47, 88, 48, 85, 98, 82};
		BinaryHeap<Integer> heap = new BinaryHeap<>(data, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o2 - o1;
			}
		});
		BinaryTrees.print(heap);
	}
	
	static void test02() {
		Integer data[] = {2, 41, 78, 79, 65, 5, 90, 13, 16, 29, 86, 45, 72, 77, 54};
		BinaryHeap<Integer> heap = new BinaryHeap<>(data);
		BinaryTrees.print(heap);
	}
	
	
	static void test01() {
		BinaryHeap<Integer> heap = new BinaryHeap<>();
		heap.add(68);
		heap.add(72);
		heap.add(43);
		heap.add(50);
		heap.add(38);
		heap.add(10);
		heap.add(90);
		heap.add(65);

		BinaryTrees.print(heap);
		System.out.println();
		
//		heap.remove();
		heap.replace(70);
		BinaryTrees.print(heap);
	}

}
