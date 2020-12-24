package com.dy;

import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person p1 = new Person(10, 1.67f, "jack");
		Person p2 = new Person(10, 1.67f, "jack");
		System.out.println(p1.hashCode());
		System.out.println(p2.hashCode());
		Map<Object, Object> map = new HashMap<>();
		map.put(p1, "abc");
		map.put(p2, "adc");
	}
	
	static void test02() {
		Integer a = 110;
		Float b = 10.6f;
		Long c = 1561l;
		Double d = 10.9;
		String e = "rose";
		System.out.println(a.hashCode());
		System.out.println(Float.floatToIntBits(b));
	}
	
	static void test01() {
		String string = "jack";
		int len = string.length();
		int hashCode = 0;
		for (int i = 0; i < len; i++) {
			char c = string.charAt(i);
			hashCode = hashCode * 31 + c;
//			hashCode = (hashCode << 5) - hashCode + c;
		}
		
		System.out.println(hashCode);
		System.out.println((10 << 5) - 10);
	}
 
}
