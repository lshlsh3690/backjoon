

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.Stack;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int t = 1; t <= 10; t++) {
			int N = Integer.parseInt(br.readLine());
			String s = br.readLine();
			Stack<Character> stack = new Stack<>();
			boolean flag = true;
			for (int i = 0; i < N; i++) {
				char c = s.charAt(i);
				if (c == '(' || c == '[' || c == '{' || c == '<') {
					stack.add(c);
					continue;
				}

				if (c == ')' && !stack.isEmpty() && stack.peek() == '(') {
					stack.pop();
					continue;
				} else if (c == ']' && !stack.isEmpty() && stack.peek() == '[') {
					stack.pop();
					continue;
				} else if (c == '}' && !stack.isEmpty() && stack.peek() == '{') {
					stack.pop();
					continue;
				} else if (c == '>' && !stack.isEmpty() && stack.peek() == '<') {
					stack.pop();
					continue;
				}

				if (!stack.isEmpty()) {
					flag = false;
				}

			}
			int result = flag ? 1 : 0;
			System.out.println("#" + t + " " + result);

		}
	}

}
