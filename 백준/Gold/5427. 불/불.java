import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int T, W, H, ret;
	static char[][] arr;
	static int[][] dp;
	static int INF = 1000000000;

	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	// '.': 빈 공간
	// '#': 벽
	// '@': 상근이의 시작 위치
	// '*': 불
	// 각 지도에 @의 개수는 하나이다.
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			ret = Integer.MAX_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			arr = new char[H][W];
			dp = new int[H][W];

			Queue<int[]> q = new LinkedList<int[]>();
			Queue<int[]> fire = new LinkedList<>();
			for (int i = 0; i < H; i++) {
				Arrays.fill(dp[i], INF);
				String[] sarr = br.readLine().split("");
				for (int j = 0; j < W; j++) {
					arr[i][j] = sarr[j].charAt(0);
					if (arr[i][j] == '@') {
						q.add(new int[] { i, j });
						dp[i][j] = 0;

					} else if (arr[i][j] == '*') {
						fire.add(new int[] { i, j });
					}
				}
			}

			tf: while (true) {
				Queue<int[]> q2 = new LinkedList<>();
				Queue<int[]> fire2 = new LinkedList<>();

				while (!q.isEmpty()) {
					int[] poll = q.poll();
					int y = poll[0];
					int x = poll[1];
					if (arr[y][x] == '*') {
						continue;
					}
					for (int k = 0; k < 4; k++) {
						int ny = y + dy[k];
						int nx = x + dx[k];
						if (out(ny, nx)) {
							ret = Math.min(ret, dp[y][x] + 1);
							break tf;
						}
						if (dp[ny][nx] != INF || arr[ny][nx] == '#' || arr[ny][nx] == '*') {
							continue;
						}

						dp[ny][nx] = dp[y][x] + 1;
						q2.add(new int[] { ny, nx });
					}
				}

				while (!fire.isEmpty()) {
					int[] poll = fire.poll();
					int y = poll[0];
					int x = poll[1];
					for (int k = 0; k < 4; k++) {
						int ny = y + dy[k];
						int nx = x + dx[k];
						if (out(ny, nx) || arr[ny][nx] == '#' || arr[ny][nx] == '*') {
							continue;
						}

						arr[ny][nx] = '*';
						fire2.add(new int[] { ny, nx });
					}
				}

				if (q2.isEmpty() && fire2.isEmpty()) {
					break;
				}
				q = q2;
				fire = fire2;
			}

			System.out.println(ret == Integer.MAX_VALUE ? "IMPOSSIBLE" : ret);
		}
	}

	static boolean out(int y, int x) {
		return y < 0 || y >= H || x < 0 || x >= W;
	}
}
