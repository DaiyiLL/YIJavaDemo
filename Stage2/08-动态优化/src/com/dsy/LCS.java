package com.dsy;


public class LCS {
	
	public static void main(String[] args) {
//		int len = lcs1(
//				new int[] {1, 4, 5, 9, 10, 15, 25},
//				new int[] {1, 4, 9, 10, 12, 15}
//			);
//		System.out.println(len);
		
//		String text1 = "abcdefg";
//		String text2 = "badeehff";
//		System.out.println(longestCommonSubsequence(text1, text2));
		
//		System.out.println(lengthOfLIS(new int[] {10, 2, 2, 5, 1, 7, 101, 18}));
		System.out.println(lengthOfLIS(new int[] {10, 7, 101, 18}));
	}
	
	static int lengthOfLIS(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		// 牌堆的数量
		int len = 0;
		// 牌顶数组
		int[] top = new int[nums.length];
		// 遍历所有的牌
		for (int num: nums) {
			int begin = 0;
			int end = len;
			while (begin < end) {
				int mid = (begin + end) >> 1;
				if (num <= top[mid]) {
					end = mid;
				} else {
					begin = mid + 1;
				}
			}
			// 覆盖牌顶
			top[begin] = num;
			// 检查是否要新建一个牌堆
			if (begin == len) len++;
		}
		return len;
	}
	
	static int lengthOfLIS2(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		// 牌堆的数量
		int len = 0;
		// 牌顶数组
		int[] top = new int[nums.length];
		// 遍历所有的牌
		for (int num: nums) {
			int j = 0;
			while (j < len) {
				// 找到一个>= num的牌顶
				if (top[j] >= num) {
					top[j] = num;
					break;
				}
				j++;
			}
			if (j == len) {
				len++;
				top[j] = num;
			}
		}
		return len;
	}
	
	static int longestCommonSubsequence(String text1, String text2) {
//		if (text1 == null || text1.length() == 0) return 0;
//		if (text2 == null || text2.length() == 0) return 0;
//		String rowString = text1, colString = text2;
//		if (text1.length() < text2.length()) {
//			colString = text1;
//			rowString = text2;
//		} 
//		
//		int colLength = colString.length();
//		int rowLength = rowString.length();
//		int[] dp = new int[colLength + 1];
//		
//		for (int i = 1; i <= rowLength; i++) {
//			int cur = 0;
//			for (int j = 1; j <= colLength; j++) {
//				int leftTop = cur;
//				cur = dp[j];
//				if (rowString.charAt(i - 1) == colString.charAt(j - 1)) {
//					dp[j] = leftTop + 1;
//				} else {
//					dp[j] = Math.max(dp[j], dp[j - 1]);
//				}
//			}
//		}
//		return dp[colLength];
		
		
		if (text1 == null || text2 == null) return 0;
		char[] chars1 = text1.toCharArray();
		if (chars1.length == 0) return 0;
		char[] chars2 = text2.toCharArray();
		if (chars2.length == 0) return 0;
		if (text2 == null || text2.length() == 0) return 0;
		char[] rowChars = chars1, colChars = chars2;
		if (chars1.length < colChars.length) {
			colChars = chars1;
			rowChars = chars2;
		} 
		
		int[] dp = new int[colChars.length + 1];
		
		for (int i = 1; i <= rowChars.length; i++) {
			int cur = 0;
			for (int j = 1; j <= colChars.length; j++) {
				int leftTop = cur;
				cur = dp[j];
				if (rowChars[i - 1] == colChars[j - 1]) {
					dp[j] = leftTop + 1;
				} else {
					dp[j] = Math.max(dp[j], dp[j - 1]);
				}
			}
		}
		return dp[colChars.length];
    }
	
	static int lcs1(int[] nums1, int[] nums2) {
		if (nums1 == null || nums1.length == 0) return 0;
		if (nums2 == null || nums2.length == 0) return 0;
		return lcs(nums1, nums1.length, nums2, nums2.length);
	}
	static int lcs(int[] nums1, int[] nums2) {
		if (nums1 == null || nums1.length == 0) return 0;
		if (nums2 == null || nums2.length == 0) return 0;
		int[] rowsNums = nums1, colsNums = nums2;
		if (nums1.length < nums2.length) {
			colsNums = nums1;
			rowsNums = nums2;
		} 
		
		int[] dp = new int[colsNums.length + 1];
		
		for (int i = 1; i <= rowsNums.length; i++) {
			int cur = 0;
			for (int j = 1; j <= colsNums.length; j++) {
				int leftTop = cur;
				cur = dp[j];
				if (rowsNums[i - 1] == colsNums[j - 1]) {
					dp[j] = leftTop + 1;
				} else {
					dp[j] = Math.max(dp[j], dp[j - 1]);
				}
			}
		}
		return dp[colsNums.length];
	}
	
	static int lcs4(int[] nums1, int[] nums2) {
		if (nums1 == null || nums1.length == 0) return 0;
		if (nums2 == null || nums2.length == 0) return 0;
		int[] dp = new int[nums2.length + 1];
		
		for (int i = 1; i <= nums1.length; i++) {
			int cur = 0;
			for (int j = 1; j <= nums2.length; j++) {
				int leftTop = cur;
				cur = dp[j];
				if (nums1[i - 1] == nums2[j - 1]) {
					dp[j] = leftTop + 1;
				} else {
					dp[j] = Math.max(dp[j], dp[j - 1]);
				}
			}
		}
		return dp[nums2.length];
	}
	
	static int lcs3(int[] nums1, int[] nums2) {
		if (nums1 == null || nums1.length == 0) return 0;
		if (nums2 == null || nums2.length == 0) return 0;
		int[][] dp = new int[2][nums2.length + 1];
		for (int i = 1; i <= nums1.length; i++) {
			int row = i & 1;
			int prevRow = (i - 1) & 1;
			for (int j = 1; j <= nums2.length; j++) {
				if (nums1[prevRow] == nums2[j - 1]) {
					dp[row][j] = dp[prevRow][j - 1] + 1;
				} else {
					dp[row][j] = Math.max(dp[prevRow][j], dp[row][j - 1]);
				}
			}
		}
		return dp[nums1.length & 1][nums2.length];
	}
	
	static int lcs2(int[] nums1, int[] nums2) {
		if (nums1 == null || nums1.length == 0) return 0;
		if (nums2 == null || nums2.length == 0) return 0;
		int[][] dp = new int[nums1.length + 1][nums2.length + 1];
		for (int i = 1; i <= nums1.length; i++) {
			for (int j = 1; j <= nums2.length; j++) {
				if (nums1[i - 1] == nums2[j - 1]) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		return dp[nums1.length][nums2.length];
	}
	
	/**
	 * 求nums1前个元素和nums2前j个元素的最长公共子序列长度
	 * @param nums1
	 * @param i
	 * @param nums2
	 * @param j
	 * @return
	 */
	static int lcs(int[] nums1, int i, int[] nums2, int j) {
		if (i == 0 || j == 0) return 0;
		if (nums1[i - 1] == nums2[j - 1]) {
			return lcs(nums1, i - 1, nums2, j - 1) + 1;
		}
		return Math.max(lcs(nums1, i - 1, nums2, j), lcs(nums1, i, nums2, j - 1));
	}

}
