package com.dsy;


public class Assert {

	public static void test(boolean value) {
		try {
			if (!value) throw new Exception("测试未通过");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void test(boolean value, String desc) {
		try {
			if (!value) throw new Exception(desc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
