package com.dsy;

public class Person implements Comparable<Person> {
	private String name;
	private int boneBreak;
	
	public Person(String name, int boneBreak) {
		super();
		this.name = name;
		this.boneBreak = boneBreak;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBoneBreak() {
		return boneBreak;
	}
	public void setBoneBreak(int boneBreak) {
		this.boneBreak = boneBreak;
	}
	@Override
	public int compareTo(Person o) {
		// TODO Auto-generated method stub
		return this.boneBreak - o.boneBreak;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name + " : " + this.boneBreak;
	}

}
