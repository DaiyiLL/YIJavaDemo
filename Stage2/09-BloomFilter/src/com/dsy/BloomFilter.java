package com.dsy;

public class BloomFilter<T> {
	
	// 二进制向量的长度(一共有多少个二进制位)
	private int bitSize;
	/**
	 * 二进制向量
	 */
	private long[] bits;
	
	/**
	 * 哈希函数的个数
	 */
	private int hashSize;
	/**
	 * 
	 * @param n 数据规模
	 * @param p 误判率(0,1)
	 */
	public BloomFilter(int n, double p) {
		if (n <= 0 || p <= 0 || p >= 1) {
			throw new IllegalArgumentException("wrong n or p");
		}
		double ln2 = Math.log(2);
		// 求出二进制向量的长度
		bitSize = (int) -((n * Math.log(p)) / (ln2 * ln2));
		// 求出哈希函数的个数
		hashSize = (int) (bitSize * ln2 / n);
		
//		System.out.println(bitSize);
//		System.out.println(hashSize);
		
		// bits的数组长度
		bits = new long[(bitSize + Long.SIZE - 1) / Long.SIZE];
		
	}

	public boolean put(T value) {
		nullCheck(value);
		
		int hash1 = value.hashCode();
		int hash2 = hash1 >>> 16;
		
		boolean  changed = false;
		for (int i = 0; i < hashSize; i++) {
			int index = hash1 + (i * hash2);
			if (index < 0) {
				index = ~index;
			}
			if (setBit(index % bitSize)) {
				changed = true;
			}
		}
		
		return changed;
	}
	
	public boolean contains(T value) {
		nullCheck(value);
		
		int hash1 = value.hashCode();
		int hash2 = hash1 >>> 16;
		for (int i = 0; i < hashSize; i++) {
			int index = hash1 + (i * hash2);
			if (index < 0) {
				index = ~index;
			}
			// 查询index位置的二进制位是否为0
			if (!get(index)) {
				return false;
			}
		}
		return true;
	}
	
	private void nullCheck(T value) {
		if (value == null) {
			throw new IllegalArgumentException("Value must not be null.");
		}
	}
	private boolean setBit(int index) {
		int  curIndex = index / Long.SIZE;
		// 找到对应long值
		long value = bits[curIndex];
		// 找到二进制所在的索引
		int bitValue = (1 << (index % Long.SIZE));
		bits[curIndex] = value | bitValue;
		return (value & bitValue) == 0;
	}
	
	private boolean get(int index) {
		// 找到对应long值
		long value = bits[index / Long.SIZE];
		// 找到二进制所在的索引
		value &= (1 << (index % Long.SIZE));
		
		return value != 0;
	}
}
