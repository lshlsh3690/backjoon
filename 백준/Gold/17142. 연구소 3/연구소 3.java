
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, ret;
	static int[][] arr, dp;
	static boolean[][] visited;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static int INF = 1000000000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		visited = new boolean[N][N];
		ret = Integer.MAX_VALUE;
		dp = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0, 0);

		System.out.println(ret == INF ? "-1" : ret == Integer.MIN_VALUE ? "0" : ret);
	}

	static void dfs(int idx, int depth) {
		if (depth == M) {
			// 최소값 갱신
			Queue<int[]> q = new LinkedList<int[]>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dp[i][j] = INF;
					if (visited[i][j] && arr[i][j] == 2) {
						q.add(new int[] { i, j });
						dp[i][j] = 0;
					}
				}
			}
			// 0은 빈 칸, 1은 벽, 2는 바이러스의 위치이다.
			while (!q.isEmpty()) {
				int[] poll = q.poll();
				int py = poll[0];
				int px = poll[1];
				for (int k = 0; k < 4; k++) {
					int ny = py + dy[k];
					int nx = px + dx[k];
					if (out(ny, nx))
						continue;
					if (arr[ny][nx] == 1)
						continue;
					if (dp[py][px] + 1 > dp[ny][nx])
						continue;
					if (dp[ny][nx] != INF)
						continue;
					q.add(new int[] { ny, nx });
					dp[ny][nx] = Math.min(dp[ny][nx], dp[py][px] + 1);
				}
			}

			int maxTemp = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] == 2 || arr[i][j] == 1)
						continue;
					maxTemp = Math.max(maxTemp, dp[i][j]);
				}
			}
			ret = Math.min(ret, maxTemp);

			return;
		}

		for (int idx2 = idx; idx2 < N * N; idx2++) {
			int y = idx2 / N;
			int x = idx2 % N;

			if (arr[y][x] != 2)
				continue;
			if (visited[y][x])
				continue;

			visited[y][x] = true;
			dfs(idx2 + 1, depth + 1);
			visited[y][x] = false;
		}
	}


	static boolean out(int y, int x) {
		return y < 0 || y >= N || x < 0 || x >= N;
	}
}
