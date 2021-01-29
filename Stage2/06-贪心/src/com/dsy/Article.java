package com.dsy;

public class Article {
	public int weight;
	public int value;
	public double valueDensity;
	public Article(int weight, int value) {
		super();
		this.weight = weight;
		this.value = value;
		this.valueDensity = value * 1.0 / weight;
	}
	@Override
	public String toString() {
		return "Article [weight=" + weight + ", value=" + value + ", valueDensity=" + valueDensity + "]";
	}
	
	
	
}
