package com.dsy.字符串;

import java.util.HashMap;
import java.util.Map;

public class _3_无重复字符串的最长子串 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static int lengthOfLongestSubstring(String s) {
		if (s == null) return 0;
		char[] chars = s.toCharArray();
		if (chars.length == 0) return 0;
		// 用来保存每一个字符上一次出现的位置
		Map<Character, Integer> prevIdxes = new HashMap<>();
		prevIdxes.put(chars[0], 0);
		// 上一个字符结束的最长不重复子串的起始位置
		Integer li = -1;
		int maxLen = 1;
		for (int i = 1; i < chars.length; i++) {
			// 位置的字符上一次出现的位置
			Integer pi = prevIdxes.getOrDefault(chars[i], -1);
			if (li <= pi) {
				li = pi + 1;
			}
			
			// 存储这个字符串的出现的最后一次位置
			prevIdxes.put(chars[i], i);
			// 求出最长不重复子串的长度
			maxLen = Math.max(maxLen, i - li + 1); 
		}
		return maxLen;
	}
	
	// 英文26小写字母
	public static int lengthOfLongestSubstring2(String s) {
		if (s == null) return 0;
		char[] chars = s.toCharArray();
		if (chars.length == 0) return 0;
		// 用来保存每一个字符上一次出现的位置
//		Map<Character, Integer> prevIdxes = new HashMap<>();
		int[] prevIdxes = new int[26];
		prevIdxes[chars[0] - 'a'] = 0;
		// 上一个字符结束的最长不重复子串的起始位置
		Integer li = -1;
		int maxLen = 1;
		for (int i = 1; i < chars.length; i++) {
			// 位置的字符上一次出现的位置
			Integer pi = prevIdxes[chars[i] - 'a'];
			if (pi != null && li <= pi) {
				li = pi + 1;
			}
			
			// 存储这个字符串的出现的最后一次位置
			prevIdxes[chars[i] - 'a'] = i;
			// 求出最长不重复子串的长度
			maxLen = Math.max(maxLen, i - li + 1); 
		}
		return maxLen;
	}
	
	public static int lengthOfLongestSubstring3(String s) {
		if (s == null) return 0;
		char[] chars = s.toCharArray();
		if (chars.length == 0) return 0;
		// 用来保存每一个字符上一次出现的位置
//		Map<Character, Integer> prevIdxes = new HashMap<>();
		Integer[] prevIdxes = new Integer[128];
		prevIdxes[chars[0]] = 0;
		// 上一个字符结束的最长不重复子串的起始位置
		Integer li = -1;
		int maxLen = 1;
		for (int i = 1; i < chars.length; i++) {
			// 位置的字符上一次出现的位置
			Integer pi = prevIdxes[chars[i]];
			if (pi != null && li <= pi) {
				li = pi + 1;
			}
			
			// 存储这个字符串的出现的最后一次位置
			prevIdxes[chars[i]] = i;
			// 求出最长不重复子串的长度
			maxLen = Math.max(maxLen, i - li + 1); 
		}
		return maxLen;
	}

}
