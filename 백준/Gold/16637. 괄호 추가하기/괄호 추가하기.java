import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Stack;


public class Main {
	static int N, ret;
	static int[]nums;
	static char[] opers;
	static boolean[] visited;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String s = br.readLine();
		nums = new int[N/2 + 1];
		opers = new char[N/2];
		visited = new boolean[N/2];
		ret = Integer.MIN_VALUE;
		
		int numIdx=0;
		int opersIdx=0;
		for(int i=0;i<s.length();i++) {
			
			if(s.charAt(i) == '+' || s.charAt(i) =='-' || s.charAt(i)=='*') {
				opers[opersIdx++] = s.charAt(i);
			}else {
				nums[numIdx++] = (int)s.charAt(i)-'0';
			}
		}
		
		int sum = nums[0];
		for(int i = 0;i<N/2;i++) {
			int num = nums[i+1];
			int oper = opers[i];
			if(oper == '+') {
				sum += num;
			}else if(oper == '-') {
				sum -= num;
			}else if(oper == '*') {
				sum *= num;
			}
		}
		ret= Math.max(ret, sum);
		
		for(int i = 0;i<N/2;i++) {
			visited[i] =true;
			dfs(i+2);
			visited[i] = false;
		}
		
		System.out.println(ret);
	}
	
	static void dfs(int idx) {
		Stack<Integer>nStack = new Stack<>();
		Stack<Character>cStack = new Stack<Character>();
		
		nStack.push(nums[0]);
		for(int i = 0;i<N/2;i++) {
			if(visited[i]) {
				int n = nStack.pop();
				if(opers[i] == '+') {
					nStack.push(n + nums[i+1]);
				}else if(opers[i] == '-') {
					nStack.push(n - nums[i+1]);
				}else if(opers[i] == '*') {
					nStack.push(n * nums[i+1]);
				}				
			}else {
				nStack.push(nums[i+1]);
				cStack.push(opers[i]);
			}
		}
		List<Integer>nList= nStack;
		List<Character>cList= cStack;
		int sum = nList.get(0);
		for(int i = 0;i<cList.size();i++) {
			int n = nList.get(i+1);
			char oper = cList.get(i);
			if(oper == '+') {
				sum += n;
			}else if(oper == '-') {
				sum -= n;
			}else if(oper == '*') {
				sum *= n;
			}
		}
		ret= Math.max(ret, sum);

		for(int i = idx;i<N/2;i++) {
			if(visited[i])continue;
			visited[i] = true;
			dfs(i+2);
			visited[i] = false;
		}
	}
}
