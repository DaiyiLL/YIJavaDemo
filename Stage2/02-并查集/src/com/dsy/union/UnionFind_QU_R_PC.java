package com.dsy.union;

/**
 * 给予rank优化 - 路劲压缩
 * @author mac
 *
 */
public class UnionFind_QU_R_PC extends UnionFind_QU_R {

	public UnionFind_QU_R_PC(int capacity) {
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
		if (parents[v] != v) {
			parents[v] = find(parents[v]);
			v = parents[v];
		}
		return parents[v];
	}

}
