package com.dsy;

import java.util.Iterator;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		BloomFilter<Integer> bf = new BloomFilter<>(1_0000_0000, 0.01);
//		int count = 50000000;
//		for (int i = 0; i < count; i++) {
//			bf.put(i);
//		}
//		
//		for (int i = count; i < count + 20000000; i++) {
//			if (bf.contains(i)) {
//				System.out.println(i);
//			}
//		}
		String[] urls = {};
		BloomFilter<String> bf = new BloomFilter<>(1_0000_0000, 0.01);
		for (String url: urls) {
//			if (bf.contains(url)) continue;
//			
//			bf.put(url);
			if (bf.put(url) == false) continue;
		}
	}

}
