package com.dsy;

import com.dsy.TimeTool.Task;

public class Main {

	/*
	 * 0 1 1 2 3 5 8 13 21 34
	 */
	public static int fib1(int n) {
		if (n <= 1) return n;
		return fib(n - 1) + fib(n - 2);
	}
	
	public static int fib(int n) {
		if (n <= 1) return 1;
		int first = 0;
		int second = 1;
		
		for (int i = 0; i < n - 1; i++) {
			int sum = first + second;
			first = second;
			second = sum;
		}
		return second;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println();
		int n = 70;
		TimeTool.check("fib1", new Task() {
			
			public void execute() {
				// TODO Auto-generated method stub
				System.out.println(fib1(n));
			}
		});
		
		TimeTool.check("fib", new Task() {
			
			public void execute() {
				// TODO Auto-generated method stub
				System.out.println(fib(n));
			}
		});
		
	}

}
