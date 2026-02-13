import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, ret;
	static int[][] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		ret = Integer.MAX_VALUE;
		for (int mask = 0; mask < 1 << N; mask++) {
			if (Integer.bitCount(mask) != N / 2)
				continue;
			int sumA=0;
			int sumB=0;
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {

					if ((mask & (1 << i)) != 0 && (mask & (1 << j)) != 0) {
						// 둘 다 A팀
						sumA += arr[i][j] + arr[j][i];
					}

					else if ((mask & (1 << i)) == 0 && (mask & (1 << j)) == 0) {
						// 둘 다 B팀
						sumB += arr[i][j] + arr[j][i];
					}
				}
			}
			ret = Math.min(ret, Math.abs(sumA - sumB));

		}

		System.out.println(ret);
	}
}
