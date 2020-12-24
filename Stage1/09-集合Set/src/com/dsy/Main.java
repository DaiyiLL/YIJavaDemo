package com.dsy;

import com.dsy.map.Map;
import com.dsy.map.Map.Visitor;
import com.dsy.map.TreeMap;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
	}
	
	static void test1() {
		Map<String, Integer> map = new TreeMap<>();
		map.put("class", 2);
		map.put("public", 3);
		map.put("text", 6);
		map.put(",", 8);
		map.put("public", 20);
		map.traversal(new Visitor<String, Integer>() {
			public boolean visit(String key, Integer value) {
				// TODO Auto-generated method stub
				System.out.println(key + "=" + value);
				return false;
			}
		});
	}

}
