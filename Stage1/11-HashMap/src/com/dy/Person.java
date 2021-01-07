package com.dy;

public class Person {

	private int age;
	private float height;
	private String name;
	public Person(int age, float height, String name) {
		super();
		this.age = age;
		this.height = height;
		this.name = name;
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
//		return super.hashCode();
		int hashCode = Integer.hashCode(age);
		hashCode = hashCode * 31 + Float.hashCode(height);
		hashCode = hashCode * 31 + (name != null ? name.hashCode() : 0);
		return hashCode;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		// 内存地址一样
		if (this == obj) return true;
		if (obj == null || obj.getClass() != getClass()) return false;
//		if (obj == null || !(obj instanceof Person)) return false;
		Person person = (Person)obj;
		return person.age == age && person.height == height
				&& person.name == null ? name == null : person.name.equals(name);
	}
	
}
