package com.dsy;

public class Knapsack {

	public static void main(String[] args) {
		int[] values = {6, 3, 5, 4, 6};
		int[] weights = {2, 2, 6, 5, 4};
		int capacity = 20;
//		System.out.println(maxValue(values, weights, capacity));
		System.out.println(maxValueExactly(values, weights, capacity));
	}
	
	static int maxValue(int[] values, int[] weights, int capacity) {
		if (values == null || values.length == 0) return 0;
		if (weights == null || weights.length == 0) return 0;
		if (values.length != weights.length || capacity <= 0) return 0;
		
		int[] dp = new int[capacity + 1];
		
		for (int i = 1; i <= values.length; i++) {
			for (int j = capacity; j >= weights[i - 1]; j--) {
				if (j < weights[i - 1]) continue;
//				{
//					// 最后一件物品无法装下
//					dp[j] = dp[j];
//				} else {
//				}
				dp[j] = Math.max(dp[j], 
						values[i - 1] + dp[j - weights[i - 1]]);
			}
		}
		
		return dp[capacity];
	}
	
	static int maxValueExactly(int[] values, int[] weights, int capacity) {
		if (values == null || values.length == 0) return 0;
		if (weights == null || weights.length == 0) return 0;
		if (values.length != weights.length || capacity <= 0) return 0;
		
		int[] dp = new int[capacity + 1];
		for (int j = 1; j < dp.length; j++) {
			dp[j] = Integer.MIN_VALUE;
		}
		
		for (int i = 1; i <= values.length; i++) {
			for (int j = capacity; j >= weights[i - 1]; j--) {
				if (j < weights[i - 1]) continue;
//				{
//					// 最后一件物品无法装下
//					dp[j] = dp[j];
//				} else {
//				}
				dp[j] = Math.max(dp[j], 
						values[i - 1] + dp[j - weights[i - 1]]);
			}
		}
		
		return dp[capacity] < 0 ? -1 : dp[capacity];
	}
}
