package com.dsy.union;

/**
 * Quick Union
 * @author mac
 *
 */
public class UnionFind_QU extends UnionFind {

	public UnionFind_QU(int capacity) {
		super(capacity);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 通过parent聊表不断向上找，知道找到可以
	 */
	@Override
	public int find(int v) {
		// TODO Auto-generated method stub
		rangeCheck(v);
		while (v != parents[v]) {
			v = parents[v];
		}
		return v;
	}

	@Override
	public void union(int v1, int v2) {
		// TODO Auto-generated method stub
		int p1 = find(v1);
		int p2 = find(v2);
		if (p1 == p2) return;
		
		parents[p1] = p2;
	}

}
