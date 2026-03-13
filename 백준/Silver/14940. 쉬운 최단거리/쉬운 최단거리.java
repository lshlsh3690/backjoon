import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] arr;
	static int[][] dp;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N][M];
		dp = new int[N][M];
		Queue<int[]> q = new LinkedList<int[]>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
				if (arr[i][j] == 2) {
					q.add(new int[] { i, j });
					dp[i][j] = 0;
				} else if (arr[i][j] == 0) {
					dp[i][j] = 0;
				}
			}
		}

		while (!q.isEmpty()) {
			int[] poll = q.poll();
			int y = poll[0];
			int x = poll[1];
			for (int k = 0; k < 4; k++) {
				int ny = y + dy[k];
				int nx = x + dx[k];
				if (out(ny, nx) || arr[ny][nx] == 0 || dp[ny][nx] != -1) {
					continue;
				}

				dp[ny][nx] = dp[y][x] + 1;
				q.add(new int[] { ny, nx });
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
	}

	static boolean out(int y, int x) {
		return y < 0 || y >= N || x < 0 || x >= M;
	}
}
