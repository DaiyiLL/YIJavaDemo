import java.util.Iterator;

public class ClimbStairs {
	int climbStairs(int n) {
		if (n <= 2) return n;
		return climbStairs(n - 1) + climbStairs(n - 2);
	}
	
	int climbStairs2(int n) {
		if (n <= 2) return n;
		int first = 1;
		int second = 2;
		for (int i = 0; i <= n; i++) {
			second = second + first;
			first = second - first;
		}
		return second;
	}
	/*
	 n = 1 fn = 1
	 n = 2 fn = 2
	 n = 3 fn = 3
	 n 
	 */
}
