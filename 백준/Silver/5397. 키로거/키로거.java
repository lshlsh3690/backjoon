import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {
	public static int T;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int t=0;t<T;t++) {
			List<Character>list = new ArrayList<>();
			
			String s = br.readLine();
			Stack<Character>stack1 = new Stack<>();
			Stack<Character>stack2 = new Stack<>();
			for(int i = 0;i<s.length();i++) {
				char c = s.charAt(i);
				if (c == '<') {
					if (stack1.isEmpty()) continue;
					stack2.push(stack1.pop());
				}else if (c =='>') {
					if (stack2.isEmpty()) continue;
					stack1.push(stack2.pop());
				}else if (c == '-') {
					if (stack1.isEmpty()) continue;
					stack1.pop();
				}else {
					stack1.push(c);
				}
			}
			
			StringBuilder sb = new StringBuilder();
			while(!stack2.isEmpty()) {
				stack1.push(stack2.pop());
			}
			while(!stack1.isEmpty()) {
				sb.append(stack1.pop());
			}
			
			System.out.println(sb.reverse());
		}
	}
}
