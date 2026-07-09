import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Solution {
	static int N, result;
	static int[] arr;
	static boolean[] isDanzo;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			isDanzo = new boolean[N];
			result = -1;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			Arrays.sort(arr);

			for (int i = 0; i < N; i++) {
				int left = arr[i];
				for (int j = i + 1; j < N; j++) {
					int right = arr[j];
					int mul = left * right;
					boolean danzo = true;

					int last = Integer.MIN_VALUE;

					String s = String.valueOf(mul);
					for (int k = 0; k < s.length(); k++) {
						int n = s.charAt(k) - '0';
						if (n < last) {
							danzo = false;
							break;
						}
						last = n;
					}

					if (!danzo) {
						continue;
					}
					result = Math.max(result, left * right);

				}
			}

			System.out.println("#" + t + " " + result);

		}
	}

}
