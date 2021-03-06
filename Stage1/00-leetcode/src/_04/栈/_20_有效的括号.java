package _04.栈;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

// https://leetcode-cn.com/problems/valid-parentheses/submissions/
public class _20_有效的括号 {
	
	public boolean isValid(String str) {
		int len = str.length();
		if (len % 2 == 1) return false;
		HashMap<Character, Character> map = new HashMap<Character, Character>();
		map.put('(', ')');
		map.put('{', '}');
		map.put('[', ']');
		
		Deque<Character> stack = new LinkedList<Character>();
		for (int i = 0; i < len; i++) {
			char ch = str.charAt(i);
			if (map.containsKey(ch)) {
                if (stack.isEmpty() || stack.peek() != map.get(ch)) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(ch);
            }
		}
		
		return stack.isEmpty();
	}
	
	public boolean isValid01(String str) {
		int len = str.length();
		if (len % 2 == 1) return false;
		HashMap<Character, Character> map = new HashMap<Character, Character>();
		map.put('(', ')');
		map.put('{', '}');
		map.put('[', ']');
		
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < len; i++) {
			char c = str.charAt(i);
			if (map.containsKey(c)) {
				stack.push(c);
			} else {
				if (stack.isEmpty()) return false;
				if (map.get(stack.pop()) != c) return false;
			}
		}
		
		return stack.isEmpty();
	}
	

	public boolean isValid00(String str) {
		int len = str.length();
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < len; i++) {
			char c = str.charAt(i);
			if (c == '(' || c == '{' || c == '[') {
				stack.push(c);
			} else {
				if (stack.isEmpty()) return false;
				char left = stack.pop();
				if (left == '(' && c != ')') return false;
				if (left == '{' && c != '}') return false;
				if (left == '[' && c != ']') return false;
			}
		}
		
		return stack.isEmpty();
	}
}
