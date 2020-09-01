package com.dsy;

public class Main {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
//		ArrayList<Person> list = new ArrayList<Person>();
//		list.add(new Person(10, "Jack"));
//		list.add(new Person(20, "Elliot"));
//		list.add(null);	
//		list.add(new Person(12, "Rose"));
//		list.add(null);	
//		
//		System.out.println(list.indexOf(new Person(12, "R11ose")));
		
//		list.clear();
//		
//		System.gc();
		
		
		test01();
	}
	
	public static void test01() {
		ArrayList2<Integer> list = new ArrayList2<Integer>();
		for (int i = 0; i < 50; i++) {
			list.add(i);
		}
		
		for (int i = 0; i < 50; i++) {
			list.remove(i);
		}
		System.out.println(list);
	}
	
	
	public static void test() {
//		int array[] = new int[] {11, 22, 33};
		
//		ArrayList<Integer> list = new ArrayList<Integer>();
//		
//		list.add(99);
//		list.add(88);
//		list.add(77);
//		list.add(66);
//		list.add(55);
		
//		list.remove(list.size() - 1);
//		list.add(0, 100);
//		list.add(6, 44);
		
//		list.set(3, 80);
		
//		Assert.test(list.get(3) == 81, "数据异常");
		
//		for (int i = 0; i < 30; i++) {
//			list.add(i);
//		}
		
		ArrayList<Person> list = new ArrayList<Person>();
		list.add(new Person(10, "Jack"));
		list.add(new Person(20, "Elliot"));
		list.add(new Person(12, "Rose"));
		list.add(null);
		
		
		System.out.println(list.indexOf(null));
		
		System.out.println(list);
	}
	

}
