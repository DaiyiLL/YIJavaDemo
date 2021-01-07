package com.dsy.model;

public class Key {
	protected int value;
	public Key(int value) {
		this.value = value;
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return value / 20;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj == this) return true;
		if (obj == null || obj.getClass() != getClass()) return false;  
		return ((Key)obj).value == value;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "V(" + value + ")";
	}

}
