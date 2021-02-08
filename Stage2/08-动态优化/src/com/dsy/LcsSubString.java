package com.dsy;


public class LcsSubString {

	public static void main(String[] args) {
		System.out.println(lcs("ABDCBA", "ABBA"));
	}
	
	
	
	/**
	 * 动态规划
	 * @param str1
	 * @param str2
	 * @return
	 */
	static int lcs(String str1, String str2) {
		if (str1 == null || str2 == null) return 0;
		char[] chars1 = str1.toCharArray();
		if (chars1.length == 0) return 0;
		char[] chars2 = str2.toCharArray();
		if (chars2.length == 0) return 0;
		char[] rowChars = chars1, colChars = chars2;
		if (rowChars.length < colChars.length) {
			colChars = chars1;
			rowChars = chars2;
		}
		int[] dp = new int[colChars.length + 1];
		int max = 0;
		for (int row = 1; row <= rowChars.length; row++) {
			for (int col = colChars.length; col >= 1; col--) {
				if (rowChars[row - 1] != colChars[col - 1]) {
					dp[col] = 0;
				} else {
					dp[col] = dp[col - 1] + 1;
					max = Math.max(max, dp[col]);
				}
			}
		}
		
		return max;
	}
	
	static int lcs2(String str1, String str2) {
		if (str1 == null || str2 == null) return 0;
		char[] chars1 = str1.toCharArray();
		if (chars1.length == 0) return 0;
		char[] chars2 = str2.toCharArray();
		if (chars2.length == 0) return 0;
		char[] rowChars = chars1, colChars = chars2;
		if (rowChars.length < colChars.length) {
			colChars = chars1;
			rowChars = chars2;
		}
		int[] dp = new int[colChars.length + 1];
		int max = 0;
		for (int row = 1; row <= rowChars.length; row++) {
			int cur = 0;
			for (int col = 1; col <= colChars.length; col++) {
				int leftTop = cur;
				cur = dp[col];
				if (rowChars[row - 1] != colChars[col - 1]) {
					dp[col] = 0;
				} else {
					dp[col] = leftTop + 1;
					max = Math.max(max, dp[col]);
				}
			}
		}
		
		return max;
	}
	
	static int lcs1(String str1, String str2) {
		if (str1 == null || str2 == null) return 0;
		char[] chars1 = str1.toCharArray();
		if (chars1.length == 0) return 0;
		char[] chars2 = str2.toCharArray();
		if (chars2.length == 0) return 0;
		int[][] dp = new int[chars1.length + 1][chars2.length + 1];
		int max = 0;
		for (int i = 1; i <= chars1.length; i++) {
			for (int j = 1; j <= chars2.length; j++) {
				if (chars1[i - 1] != chars2[j - 1]) continue;
				dp[i][j] = dp[i - 1][j - 1] + 1;
				max = Math.max(max, dp[i][j]);
			}
		}
		
		return max;
	}
}
