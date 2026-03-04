import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int T, N, X, ret;
	static int[][] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			ret = 0;
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());

			arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < N; i++) {
				// 가로 체크
				int[] line = new int[N];
				for (int j = 0; j < N; j++) {
					line[j] = arr[i][j];
				}
				if (check(line)) {
					ret++;
				}

				line = new int[N];
				for (int j = 0; j < N; j++) {
					line[j] = arr[j][i];
				}
				if (check(line)) {
					ret++;
				}

			}

			System.out.println("#" + t + " " + ret);
		}
	}

	static boolean check(int[] line) {
		// 경사로가 이미 놓인 칸
		boolean[] used = new boolean[N];

		for (int i = 0; i < N - 1; i++) {
			int cur = line[i];
			int next = line[i + 1];

			if (cur == next)
				continue;

			// 높이 차 2 이상이면 불가능
			if (Math.abs(cur - next) > 1)
				return false;

			// 오르막: next = cur + 1, 뒤로 X칸이 cur 높이여야 함
			if (next == cur + 1) {
				for (int k = 0; k < X; k++) {
					int idx = i - k;
					if (idx < 0)
						return false;
					if (line[idx] != cur)
						return false;
					if (used[idx])
						return false;
					used[idx] = true;
				}
			}
			// 내리막: next = cur - 1, 앞으로 X칸이 next 높이여야 함
			else if (next == cur - 1) {
				for (int k = 1; k <= X; k++) {
					int idx = i + k;
					if (idx >= N)
						return false;
					if (line[idx] != next)
						return false;
					if (used[idx])
						return false;
					used[idx] = true;
				}
				i += (X - 1);
			}
		}

		return true;
	}
}
