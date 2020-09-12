package com.dsy;

import com.dsy.tree.Comparable;

public class Person implements Comparable<Person> {
	private String name;
	private int age;
	
	
	
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}



	public int getAge() {
		return age;
	}



	public void setAge(int age) {
		this.age = age;
	}



	@Override
	public int compareTo(Person e) {
		// TODO Auto-generated method stub
		return age - e.age;
	}
}
