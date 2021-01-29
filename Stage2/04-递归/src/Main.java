import com.dsy.tools.Times;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println(sum(100));
		
//		Hanoi h = new Hanoi();
//		h.hanoi(3, "A", "B", "C");
		
		testFib();
	}
	
	static void testFib() {
		int n = 30;
		Fib fib = new Fib();
		Times.test("fib0", () -> {
			System.out.println(fib.fib2(n));
		});
		Times.test("fib1", () -> {
			System.out.println(fib.fib1(n));
		});
		
		Times.test("fib3", () -> {
			System.out.println(fib.fib3(n));
		});
		
		Times.test("fib4", () -> {
			System.out.println(fib.fib4(n));
		});
		
		Times.test("fib5", () -> {
			System.out.println(fib.fib5(n));
		});
		
		Times.test("fib6", () -> {
			System.out.println(fib.fib6(n));
		});
		
		Times.test("fib7", () -> {
			System.out.println(fib.fib7(n));
		});
	}
	
	static int sum(int n) {
		if (n <=  1) return n;
		return n + sum(n - 1);
	}
	
	static int fib(int n) {
		if (n <= 2) return 1;
		return fib(n - 1) + fib(n - 2);
	}

}
