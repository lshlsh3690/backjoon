import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static int N;
	public static long[] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		arr = new long[N];
		for(int i = 0;i<N;i++) {
			arr[i]=Long.parseLong(st.nextToken()); 
		}
		
		Arrays.sort(arr);
		
		int left = 0;
		int right = N-1;
		int leftResult = 0;
		int rightResult=N-1;
		
		long result = arr[left]+ arr[right];
		if (result == 0) {
			System.out.println(arr[left]+ " " +arr[right]);
			return;
		}
		while (left<right) {
			long num = arr[left] + arr[right];
			if (Math.abs(num) < Math.abs(result)) {
				leftResult = left;//인덱스 저장
				rightResult = right; 
				result = Math.abs(num);
			}
			
			if (Math.abs(arr[left]) > Math.abs(arr[right])) {
				left++;
			}else if (Math.abs(arr[left]) < Math.abs(arr[right])) {
				right--;
			}else {
				//같은 경우
				left++;
				right++;
			}
		}
		
		System.out.println(arr[leftResult] + " " + arr[rightResult]);
	}
}
