import java.util.*;
import java.io.*;
import java.math.*;
import java.lang.*;

public class Main {
	public static int N;
	public static int[]arr, result;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		result = new int[N];
		Stack<int[]> stack = new Stack<>();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < arr.length; i++) {
			arr[i]=  Integer.parseInt(st.nextToken());
		}
		
		int  last = 0;
		for(int i = N-1;i>=0;i--) {
			if(stack.isEmpty() || stack.peek()[1] > arr[i]) {
				stack.add(new int[] {i, arr[i]});
			}else {
				while(!stack.isEmpty() && stack.peek()[1] <= arr[i]) {
					int[] pop = stack.pop();
					result[pop[0]] = i+1;
				}

				stack.add(new int[] {i, arr[i]});
			}
		}
		
		while(!stack.isEmpty()) {
			int pop[] = stack.pop();
			result[pop[0]] = 0;
		}
		
		for(int i = 0;i<N;i++) {
			System.out.print(result[i]+ " " );
		}
	}
}
