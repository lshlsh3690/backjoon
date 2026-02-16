import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int T, N, M, L, R, C, ret;
	static int[][] arr;
	static boolean[][] visited;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static boolean[][] turnel = { { false, false, false, false }, // 0은 이동못함
			{ true, true, true, true }, // 상하좌우
			{ true, false, true, false }, // 상 하
			{ false, true, false, true }, // 좌 우
			{ true, true, false, false }, // 상 우
			{ false, true, true, false }, // 하 우
			{ false, false, true, true }, // 하 좌
			{ true, false, false, true }// 상 좌
	};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			ret = 0;
			arr = new int[N][M];
			visited = new boolean[N][M];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < M; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			visited[R][C] = true;
			Queue<int[]> q = new LinkedList<>();
			q.add(new int[] { R, C });

			dfs(1, q);

			System.out.println("#" + t + " " + ret);
		}
	}

	public static void dfs(int depth, Queue<int[]> q) {
		if (depth == L) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (visited[i][j]) {
						ret++;
					}
				}
			}
			return;
		}

		Queue<int[]> nextQ = new LinkedList<int[]>();
		while (!q.isEmpty()) {
			int[] poll = q.poll();
			int y = poll[0];
			int x = poll[1];
			for (int k = 0; k < 4; k++) {
				int ny = y + dy[k];
				int nx = x + dx[k];

				if (ny < 0 || ny >= N || nx < 0 || nx >= M || visited[ny][nx])
					continue;
				if (arr[y][x] == 0 || arr[ny][nx] == 0 || !turnel[arr[y][x]][k])
					continue;
				// 다음터널의 이동도 true여야됌
				if (k == 0 && !turnel[arr[ny][nx]][2])
					continue;
				if (k == 1 && !turnel[arr[ny][nx]][3])
					continue;
				if (k == 2 && !turnel[arr[ny][nx]][0])
					continue;
				if (k == 3 && !turnel[arr[ny][nx]][1])
					continue;
				visited[ny][nx] = true;
				nextQ.add(new int[] { ny, nx });
			}
		}

		dfs(depth + 1, nextQ);

	}
}
