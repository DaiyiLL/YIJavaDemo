package com.dsy;

import com.dsy.tools.Asserts;
import com.dsy.tools.Times;
import com.dsy.union.UnionFind;
import com.dsy.union.UnionFind_QF;
import com.dsy.union.UnionFind_QU;
import com.dsy.union.UnionFind_QU_R;
import com.dsy.union.UnionFind_QU_R_PC;
import com.dsy.union.UnionFind_QU_R_PH;
import com.dsy.union.UnionFind_QU_R_PS;
import com.dsy.union.UnionFind_QU_S;

public class Main {

	static final int count = 500000;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		UnionFind  uf = new  UnionFind_QU_S(12);
		
//		test(new UnionFind_QF(12));
//		test(new UnionFind_QU(12));
//		test(new UnionFind_QU_S(12));
		
//		testTime(new UnionFind_QF(count));
//		testTime(new UnionFind_QU(count));
		testTime(new UnionFind_QU_S(count));
		testTime(new UnionFind_QU_R(count));
		testTime(new UnionFind_QU_R_PC(count));
		testTime(new UnionFind_QU_R_PS(count));
		testTime(new UnionFind_QU_R_PH(count));
	}
	
	static void testTime(UnionFind  uf) {
		Times.test(uf.getClass().getSimpleName(), () -> {
			for (int i = 0; i < count; i++) {
				uf.union((int)(Math.random() * count), (int)(Math.random() * count));
			}
			System.out.println("union complete");
			for (int i = 0; i < count; i++) {
				uf.isSame((int)(Math.random() * count), (int)(Math.random() * count));
			}
		});	
	}
	
	static void test(UnionFind  uf) {
		uf.union(0, 1);
		uf.union(0, 3);
		uf.union(0, 4);
		uf.union(2, 3);
		uf.union(2, 5);
		
		uf.union(6, 7);
		
		uf.union(8, 10);
		uf.union(9, 10);
		uf.union(9, 11);
		
//		System.out.println(uf.isSame(0, 6));
//		System.out.println(uf.isSame(0, 5));
		Asserts.test(!uf.isSame(2, 7));
		
		uf.union(4, 6);
//		System.out.println(uf.isSame(0, 6));
//		System.out.println(uf.isSame(2, 7));
		
		Asserts.test(uf.isSame(2, 7));
	}

}
