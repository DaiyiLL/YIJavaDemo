package com.dsy.字符串;

public class _151_翻转字符串里的单词 {
	public static String reverseWords(String s) {
		if (s == null) return null;
		// 消除多余的空格
		char[] chars = s.toCharArray();
		if (chars.length == 0) return "";
		// 字符串的有效长度
		int len = 0;
		int cur = 0;  // 当前用来存放字符的有效位置
		boolean space = true;
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] != ' ') {
				// 非空格字符
				chars[cur++] = chars[i];
				space = false;
			} else if (space == false) { 
				// chars[i]是空格字符,chars[i - 1]非空格字符
				chars[cur++] = ' ';
				space = true;
			} else {
				// chars[i]是空格字符,chars[i - 1]空格字符
			}
		}
		len = space ? (cur - 1) : cur;
//		System.out.println(len);
		// 翻转,对整个有效字符串进行翻转操作
		reverse(chars, 0, len);
		// -1位置有个假象的哨兵
		int li = -1;
		for (int i = 0; i < len; i++) {
			if (chars[i] != ' ') continue;
			// i是空格字符串位置
			reverse(chars, li + 1, i);
			li = i;
		}
		if (li + 1 < len) {
			reverse(chars, li + 1, len);
		}
		
		
//		return chars.toString().substring(0, len - 1);
		return new String(chars, 0, len);
	}
	
	/**
	 * 将[li, ri)范围内的字符串进行逆序
	 * @param chars
	 * @param li
	 * @param ri
	 */
	private static void reverse(char[] chars, int li, int ri) {
		ri--;
		while (li < ri) {
			char ch = chars[li];
			chars[li] = chars[ri];
			chars[ri] = ch;
			li++;
			ri--;
		}
	}
	
	public static void main(String[] args) {
		System.out.println("666_" + reverseWords("") + "_666");
		System.out.println("666_" + reverseWords("  hello world!     ") + "_666");
		System.out.println("666_" + reverseWords("a good   example") + "_666");
		System.out.println("666_" + reverseWords("are you ok") + "_666");
	}
}
