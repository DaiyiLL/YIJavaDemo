package com.dsy.union;

/**
 * 基于rank优化 - 路劲分裂(path spliting)
 * @author mac
 *
 */
public class UnionFind_QU_R_PS extends UnionFind_QU_R {

	public UnionFind_QU_R_PS(int capacity) {
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
			// 指向祖父节点
			int p = parents[v];
			parents[v] = parents[parents[v]];
			v = p;
		}
		return v;
	}

}
