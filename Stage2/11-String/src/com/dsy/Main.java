package com.dsy;

//import com.dsy.string.BruteForce01;
import com.dsy.string.BruteForce2;
import com.dsy.string.KMP;
import com.dsy.tools.Asserts;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Asserts.test(BruteForce2.indexOf("Hello World", "or") == 7);
//		Asserts.test(BruteForce2.indexOf("Hello World", "H") == 0);
//		Asserts.test(BruteForce2.indexOf("Hello World", "abc") == -1);
		
		Asserts.test(KMP.indexOf("Hello World", "or") == 7);
		Asserts.test(KMP.indexOf("Hello World", "H") == 0);
		Asserts.test(KMP.indexOf("Hello World", "abc") == -1);
	}

}
