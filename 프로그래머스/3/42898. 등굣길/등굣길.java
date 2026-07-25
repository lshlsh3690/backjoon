import java.util.Arrays;

class Solution {
	static final int MOD = 1_000_000_007;

	static int N, M;
	static int[][] arr;
	static int[][] dp;

	public int solution(int m, int n, int[][] puddles) {
		// N은 세로 길이, M은 가로 길이
		N = n;
		M = m;

		// arr[y][x] == 1이면 물에 잠긴 지역
		arr = new int[N][M];

		// dp[y][x]는 (y, x)에서 학교까지 가는 경로의 개수
		dp = new int[N][M];

		for (int y = 0; y < N; y++) {
			Arrays.fill(dp[y], -1);
		}

		for (int i = 0; i < puddles.length; i++) {
			// puddles는 [x, y] 순서로 주어진다.
			int x = puddles[i][0] - 1;
			int y = puddles[i][1] - 1;

			arr[y][x] = 1;
		}

		return go(0, 0);
	}

	static int go(int y, int x) {
		// 격자 밖으로 나가면 경로가 아니다.
		if (out(y, x)) {
			return 0;
		}

		// 물에 잠긴 지역으로는 갈 수 없다.
		if (arr[y][x] == 1) {
			return 0;
		}

		// 학교에 도착한 경로 1개를 반환한다.
		if (y == N - 1 && x == M - 1) {
			return 1;
		}

		// 이미 계산한 위치라면 저장된 값을 반환한다.
		if (dp[y][x] != -1) {
			return dp[y][x];
		}

		// 아래쪽으로 이동하는 경로의 수
		int down = go(y + 1, x);

		// 오른쪽으로 이동하는 경로의 수
		int right = go(y, x + 1);

		dp[y][x] = (down + right) % MOD;

		return dp[y][x];
	}

	static boolean out(int y, int x) {
		return y < 0 || y >= N || x < 0 || x >= M;
	}
}