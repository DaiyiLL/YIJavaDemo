package com.dsy;

public class Student implements Comparable<Student> {
	
	public int score;
	public int age;
	public String name;
	public Student(int score, int age, String name) {
		super();
		this.score = score;
		this.age = age;
		this.name = name;
	}
	@Override
	public int compareTo(Student o) {
		// TODO Auto-generated method stub
		return age - o.age;
	}
	
	
	

}
