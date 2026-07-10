import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int N;

	static class swea_1231_Node {
		Character value;
		int left = -1;
		int right = -1;
	}

	static swea_1231_Node[] arr;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int t = 1; t <= 10; t++) {
			N = Integer.parseInt(br.readLine());

			arr = new swea_1231_Node[101];
			for (int i = 0; i < 101; i++) {
				arr[i] = new swea_1231_Node();
			}
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");

				int idx = Integer.parseInt(st.nextToken());

				char c = st.nextToken().charAt(0);
				arr[idx].value = c;
				if (st.hasMoreTokens()) {
					arr[idx].left = Integer.parseInt(st.nextToken());
				}
				if (st.hasMoreTokens()) {
					arr[idx].right = Integer.parseInt(st.nextToken());
				}
			}

			sb = new StringBuilder();
			sb.append("#").append(t).append(" ");
			go(1);

			System.out.println(sb.toString());
		}
	}

	static void go(int idx) {
		if (arr[idx].left != -1) {
			go(arr[idx].left);
		}
		sb.append(arr[idx].value);
		if (arr[idx].right != -1) {
			go(arr[idx].right);
		}
	}

}
