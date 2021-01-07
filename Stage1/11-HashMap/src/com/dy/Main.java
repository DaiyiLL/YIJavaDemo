package com.dy;

import com.dsy.map.HashMap;
import com.dsy.map.LinkedHashMap;
import com.dsy.map.Map;
import com.dsy.map.Map.Visitor;
import com.dsy.model.Key;
import com.dsy.model.SubKey1;
import com.dsy.model.SubKey2;
import com.dsy.tree.file.FileInfo;
import com.dsy.tree.file.Files;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.dy.Times;
import com.dy.Times.Task;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		HashMap <Object, Integer> map = new HashMap<>();
//		map.put(null, 1);
//		map.put(new Object(), 2);
//		map.put("jack", 3);
//		map.put(10, 4);
//		map.put(new Object(), 5);
//		map.put("jack", 6);
//		map.put(10, 7);
//		map.put(null, 8);
//		map.put(10, null);
//		map.print();
		
//		test08(map);
		
//		test10();
		test06(new LinkedHashMap<>());
		
	}
	
	
	
	static void test1Map(Map<String, Integer> map, String[] words) {
		Times.test(map.getClass().getName(), new Task() {
			@Override
			public void execute() {
				for (String word : words) {
					Integer count = map.get(word);
					count = count == null ? 0 : count;
					map.put(word, count + 1);
				}
				System.out.println(map.size()); // 17188
				
				int count = 0;
				for (String word : words) {
					Integer i = map.get(word);
					count += i == null ? 0 : i;
					map.remove(word);
				}
				Asserts.test(count == words.length);
				Asserts.test(map.size() == 0);
			}
		});
	}
	
	static void test2Map(java.util.Map<String, Integer> map, String[] words) {
		Times.test(map.getClass().getName(), new Task() {
			@Override
			public void execute() {
				for (String word : words) {
					Integer count = map.get(word);
					count = count == null ? 0 : count;
					map.put(word, count + 1);
				}
				System.out.println(map.size()); // 17188
				
				int count = 0;
				for (String word : words) {
					Integer i = map.get(word);
					count += i == null ? 0 : i;
					map.remove(word);
				}
				Asserts.test(count == words.length);
				Asserts.test(map.size() == 0);
//				System.out.println(map.size());
			}
		});
	}
	
	static void test10() {
		String filepath = "/Users/mac/Desktop/Resources/数据结构/代码/11-哈希表/src";
		FileInfo fileInfo = Files.read(filepath, null);
		String[] words = fileInfo.words();

		System.out.println("总行数：" + fileInfo.getLines());
		System.out.println("单词总数：" + words.length);
		System.out.println("-------------------------------------");

//		test1Map(new TreeMap<>(), words);
		test1Map(new HashMap<>(), words);
		test2Map(new java.util.HashMap<>(), words);
//		test1Map(new LinkedHashMap<>(), words);
	}
	
	static void test08(HashMap<Object, Integer> map) {
		for (int i = 1; i <= 20; i++) {
			map.put(new SubKey1(i), i);
		}
		map.put(new SubKey2(1), 5);
		Asserts.test(map.get(new SubKey1(1)) == 5);
		Asserts.test(map.get(new SubKey2(1)) == 5);
		Asserts.test(map.size() == 20);
		
		map.print();
	}
	
	static void test07(HashMap<Object, Integer> map) {
		map.put(null, 1); // 1
		map.put(new Object(), 2); // 2
		map.put("jack", 3); // 3
		map.put(10, 4); // 4
		map.put(new Object(), 5); // 5
		map.put("jack", 6);
		map.put(10, 7);
		map.put(null, 8);
		map.put(10, null);
		Asserts.test(map.size() == 5);
		Asserts.test(map.get(null) == 8);
		Asserts.test(map.get("jack") == 6);
		Asserts.test(map.get(10) == null);
		Asserts.test(map.get(new Object()) == null);
		Asserts.test(map.containsKey(10));
		Asserts.test(map.containsKey(null));
		Asserts.test(map.containsValue(null));
		Asserts.test(map.containsValue(1) == false);
	}
	
	static void test06(HashMap<Object, Integer> map) {
		map.put("jack", 1);
		map.put("rose", 2);
		map.put("jim", 3);
		map.put("jake", 4);		
		map.remove("jack");
		map.remove("jim");
		for (int i = 1; i <= 10; i++) {
			map.put("test" + i, i);
			map.put(new Key(i), i);
		}
		for (int i = 5; i <= 7; i++) {
			Asserts.test(map.remove(new Key(i)) == i);
		}
		for (int i = 1; i <= 3; i++) {
			map.put(new Key(i), i + 5);
		}
		Asserts.test(map.size() == 19);
		Asserts.test(map.get(new Key(1)) == 6);
		Asserts.test(map.get(new Key(2)) == 7);
		Asserts.test(map.get(new Key(3)) == 8);
		Asserts.test(map.get(new Key(4)) == 4);
		Asserts.test(map.get(new Key(5)) == null);
		Asserts.test(map.get(new Key(6)) == null);
		Asserts.test(map.get(new Key(7)) == null);
		Asserts.test(map.get(new Key(8)) == 8);
		map.traversal(new Visitor<Object, Integer>() {
			public boolean visit(Object key, Integer value) {
				System.out.println(key + "_" + value);
				return false;
			}
		});
	}
	
	static void test05() {
		HashMap <Object, Integer> map = new HashMap<>();
//		for (int i = 1; i <= 19; i++) {
//			map.put(new Key(i), i);
//		}
		SubKey1 key1 = new SubKey1(1);
		SubKey2 key2 = new SubKey2(1);
		map.put(key1, 1);
		map.put(key2, 2);
		
		map.print();
	}
	
	static void test04() {
		HashMap <Object, Integer> map = new HashMap<>();
		for (int i = 1; i <= 19; i++) {
			map.put(new Key(i), i);
		}
		
//		map.put(new Key(5), 5);
//		map.put(new Key(4), 100);
//		System.out.println(map.size());
		map.put(new Key(4), 100);
		map.print();
//		
//		System.out.println();
//		System.out.println(map.get(new Key(4)));
//		System.out.println(map.get(new Key(18)));
	}
	
	static void test03() {
		Person p1 = new Person(10, 1.67f, "jack");
		Person p2 = new Person(10, 1.67f, "jack");
//		System.out.println(p1.hashCode());
//		System.out.println(p2.hashCode());
		Map<Object, String> map = new HashMap<>();
		map.put(p1, "abc");
		map.put(p2, "adc");
		map.put(null, "32432");
		
		map.put("json",  "abc");
		map.put("jd", "1324");
//		System.out.println(map.size());
//		System.out.println(map.get("jd"));
//		System.out.println(map.get(p1));
		
//		System.out.println(map.size());
//		System.out.println(map.remove("json"));
//		System.out.println(map.size());
//		System.out.println(map.get("json"));
		
		map.traversal(new Visitor<Object, String>() {
			
			@Override
			public boolean visit(Object key, String value) {
				// TODO Auto-generated method stub
				System.out.println(key + ":" + value);
				return false;
			}
		});
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
