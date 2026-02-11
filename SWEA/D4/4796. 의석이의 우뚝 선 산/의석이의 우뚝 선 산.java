
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Stack;

public class Solution {
	public static int T, N, ret;
	public static int[] arr;

	public static void main(String[] args) throws Exception {
		StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

		st.nextToken(); // 다음 토큰 읽기
		T = (int) st.nval; // 숫자는 nval에 저장됨 (double 타입), 문자열은 sval

		for (int t = 1; t <= T; t++) {
			st.nextToken();
			N = (int) st.nval;

			arr = new int[N];
			for (int i = 0; i < N; i++) {
				st.nextToken();
				arr[i] = (int) st.nval;
			}

			ret = 0;
			for (int i = 1; i < N-1; i++) {
				int pivot = arr[i];
				int left = i - 1;
				int right = i + 1;
				if (arr[left] < pivot && pivot > arr[right]) {
					int leftCnt=0;
					int rightCnt=0;
					while(left >= 0 && arr[left] < arr[left+1]) {
						left--;
						leftCnt++;
					}
					
					while(right < N && arr[right] < arr[right-1]) {
						right++;
						rightCnt++;
					}
					
					ret += leftCnt * rightCnt;
					
				}
			}

			System.out.println("#"+t+" " +ret);
		}
	}
}
