package com.dsy;

public class Person {
	
	private int age;
	private String name;
	
	public Person(int age, String name) {
		this.age = age;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [age=" + age + ", name=" + name + "]";
	}
	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		System.out.println("Person - finalize");
		
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj instanceof Person) {
			// TODO Auto-generated method stub
			Person person = (Person)obj;
			
			System.out.println(this.age + " == " + person.age);
			return this.age == person.age;
		} else {
			return false;
		}
		
	}
	

}
