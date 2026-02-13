import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, L, R, X;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int ret = 0;
		for (int mask = 0; mask < (1 << N); mask++) {
			if (Integer.bitCount(mask) < 2)
				continue;
			int sum = 0;
			int max = Integer.MIN_VALUE;
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				// 선택되지 않으면 스킵
				if ((mask & 1 << i) == 0)
					continue;

				// 선택된 원소
				sum += arr[i];
				max = Math.max(max, arr[i]);
				min = Math.min(min, arr[i]);
			}

			if (L <= sum && sum <= R && Math.abs(max - min) >= X) {
				ret++;
			}
		}
		
		System.out.println(ret);
	}
}
