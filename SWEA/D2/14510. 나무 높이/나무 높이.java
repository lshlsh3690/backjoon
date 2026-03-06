import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int T, N, max;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			max = 0;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				max = Math.max(max, arr[i]);
			}

			int two = 0;
			int one = 0;
			for (int i = 0; i < N; i++) {
				int sub = max - arr[i];
				two += sub / 2;
				one += sub % 2;
			}

			while (two > one + 1) {
				two--;
				one += 2;
			}

			int day = 0;
			day = Math.max(2 * one - 1, 2 * two);

			System.out.println("#" + t + " " + day);
		}
	}
}
