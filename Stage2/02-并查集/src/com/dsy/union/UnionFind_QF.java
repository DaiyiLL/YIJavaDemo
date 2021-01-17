package com.dsy.union;

public class UnionFind_QF extends UnionFind {

	public UnionFind_QF(int capacity) {
		super(capacity);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 查找的并查集
	 * @param v
	 * @return
	 */
	public int find(int v) {
		rangeCheck(v);
		return parents[v];
	}
	/**
	 * 合并所在的集合
	 */
	public void union(int v1, int v2) {
		int p1 = find(v1);
		int p2 = find(v2);
		if (p1 == p2) return;
		
		// 只要是v1的父节点，全部都修改为v2的父节点
		for (int i = 0; i < parents.length; i++) {
			if (parents[i] == p1) {
				parents[i] = p2;
			}
		}
	}
	
}
