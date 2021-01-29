import java.util.Iterator;

public class Fib {

	static int fib(int n) {
		if (n <= 2) return 1;
		return fib(n - 1) + fib(n - 2);
	}
	
	int fib1(int n) {
		if (n <= 2) return 1;
		int[] array = new int[n + 1];
		array[1] = array[2] = 1;
		return fib1(n, array);
	}
	
	int fib1(int n, int[] array) {
		if (array[n] == 0) {
			array[n] =  fib1(n - 1, array) + fib1(n - 2, array);
		}
		
		return array[n];
	}
	
	int  fib2(int n) {
		if (n <= 2) return 1;
		int count = n + 1;
		int[] array = new int[count];
		array[1] = array[2] = 1;
		for (int i = 3; i < count; i++) {
			array[i] = array[i -  1] + array[i - 2];
		}
		
		return array[n];
	}
	
	int fib3(int n) {
		if (n <= 2) return 1;
		int[] array = new int[2];
		array[0] = array[1] = 1;
		for (int i = 3; i <= n; i++) {
			int index = i % 2;
			array[index] = array[(index + 1) % 2] + array[index];
		}
		
		return array[n % 2];
	}
	
	int fib4(int n) {
		if (n <= 2) return 1;
		int[] array = new int[2];
		array[0] = array[1] = 1;
		for (int i = 3; i <= n; i++) {
			int index = i & 1;
			array[index] = array[(i + 1) & 1] + array[index];
		}
		
		return array[n & 1];
	}
	
	int fib5(int n) {
		if (n <= 2) return 1;
		int first = 1;
		int second = 1;
		for (int i = 3; i <= n; i++) {
			second = first + second;
			first = second - first;
		}
		
		return second;
	}
	
	int fib6(int n) {
		double c = Math.sqrt(5);
		return (int)((Math.pow((1 + c) / 2, n) - Math.pow((1-c)/2, n)) / c);
	}
	
	int fib7(int n) {
		return fib(n, 1, 1);
	}
	public int fib(int n, int first, int second) {
		if (n <= 1) return first;
		return fib(n - 1, second, first + second);
	}
}
