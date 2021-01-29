package com.dsy;

import java.util.Iterator;

public class Main {
	public static void main(String[] args) {
		int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
		System.out.println(maxSubarray(nums));
	}
	static int maxSubarray(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		return maxSubarray(nums, 0, nums.length);
	}
	
	/*
	 * 求解[begin, end)之间最大连续子序列的和
	 */
	static int maxSubarray(int[] nums, int begin, int end) {
		if (end  - begin < 2) return nums[begin];
		int mid = (begin + end) >> 1;
		int max =  Math.max(maxSubarray(nums, begin, mid), maxSubarray(nums, mid, end));
		
		int leftMax =  nums[mid - 1];
		int rightMax =  nums[mid];
		int leftSum = 0;
		for (int i = mid - 1; i >= begin; i--) {
			leftSum += nums[i];
			leftMax = Math.max(leftMax, leftSum);
		}
		int rightSum = 0;
		for (int i = mid; i < end; i++) {
			rightSum += nums[i];
			rightMax = Math.max(rightMax, rightSum);
		}
		
		return Math.max(leftMax + rightMax, max);
	}
	
	static int maxSubarray2(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		
		int max = nums[0];
		for (int begin = 0; begin < nums.length; begin++) {
			int sum = 0;
			for (int end = begin; end < nums.length; end++) {
				sum += nums[end];
				max = Math.max(max, sum);
			}
		}
		
		return max;
	}
}
