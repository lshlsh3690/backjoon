import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static int[][] arr;
	static int[][][][] dp;

	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static int INF = 1000000000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			String line = st.nextToken();
			for (int j = 0; j < M; j++) {
				arr[i][j] = (line.charAt(j) - '0');
			}
		}
		dp = new int[N][M][K + 1][2];// K는 벽 부수는 개수 , 2는 밤낮

		dp[0][0][0][0] = 1;
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.add(new int[] { 0, 0, 0, 0 });

		while (!q.isEmpty()) {
			int[] poll = q.poll();
			int y = poll[0];
			int x = poll[1];
			int k = poll[2];
			int isDay = poll[3];// 0은 낮 1은 밤
			int nextDay = (isDay + 1) % 2;
			boolean wait = false;
			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];
				if (out(ny, nx)) {
					continue;
				}

				// 0은 이동할 수 있는 곳, 1은 이동할 수 없는 벽이 있는 곳
				// 벽이 있는 경우에서 밤과 낮으로 나뉘어짐
				if (arr[ny][nx] == 1) {
					// 낮인 경우 벽돌을 깰 수 있음
					if (isDay == 0 && k < K) {
						if (dp[ny][nx][k + 1][nextDay] == 0) {
							dp[ny][nx][k + 1][nextDay] = dp[y][x][k][isDay] + 1;
							q.add(new int[] { ny, nx, k + 1, nextDay });
						}
					} else if (isDay == 1) {// 밤인 경우 벽돌
						wait = true;
					}
				}

				// 벽이 없는 경우
				else if (arr[ny][nx] == 0) {
					if (dp[ny][nx][k][nextDay] == 0) {
						dp[ny][nx][k][nextDay] = dp[y][x][k][isDay] + 1;
						q.add(new int[] { ny, nx, k, nextDay });
					}
				}
			}

			if (wait && dp[y][x][k][nextDay] == 0) {
				dp[y][x][k][nextDay] = dp[y][x][k][isDay] + 1;
				q.add(new int[] { y, x, k, nextDay });
			}

		}

		int ret = INF;
		for (int a = 0; a <= K; a++) {
			for (int b = 0; b < 2; b++) {
				if (dp[N - 1][M - 1][a][b] == 0) {
					continue;
				}
				ret = Math.min(ret, dp[N - 1][M - 1][a][b]);
			}
		}
		if (ret == INF) {
			ret = -1;
		}
		System.out.println(ret);
	}

	static boolean out(int y, int x) {
		return y < 0 || y >= N || x < 0 || x >= M;
	}
}
