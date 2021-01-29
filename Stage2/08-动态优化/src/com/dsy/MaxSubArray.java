package com.dsy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxSubArray {

	public static void main(String[] args) {
		int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
		System.out.println(maxSubArray(nums));
		
	}
	
	static int maxSubArray(int[] array) {
		if (array == null || array.length == 0) return 0;
//		int[] dp = new int[array.length];
		int dp = array[0];
		int max = dp;
		int maxIndex = 0;
		for (int i = 1; i < array.length; i++) {
			int prev = dp;
			if (prev <= 0) {
				dp = array[i];
			} else {
				dp = prev + array[i];
			}
			if (dp > max) {
				max = dp;
				maxIndex = i;
			} 
		}
		// 获取max子序列
		int index = maxIndex;
		int curDp = max;
		while (curDp > 0) {
			curDp = curDp - array[index];
			index -= 1;
		}
		for (int i = index + 1; i <= maxIndex; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
		
		return max;
	}
	
	static int maxSubArray1(int[] array) {
		if (array == null || array.length == 0) return 0;
		int[] dp = new int[array.length];
		dp[0] = array[0];
		int max = dp[0];
		int maxIndex = 0;
		for (int i = 1; i < array.length; i++) {
			int prev = dp[i - 1];
			if (prev <= 0) {
				dp[i] = array[i];
			} else {
				dp[i] = prev + array[i];
			}
			if (dp[i] > max) {
				max = dp[i];
				maxIndex = i;
			}
		}
		// 获取max子序列
		int index = maxIndex;
		List<Integer> subArray = new ArrayList<>();
		while (dp[index] > 0) {
			subArray.add(0, dp[index]);
			index -= 1;
		}
		for (int i = index + 1; i <= maxIndex; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
		
		return max;
	}
}
