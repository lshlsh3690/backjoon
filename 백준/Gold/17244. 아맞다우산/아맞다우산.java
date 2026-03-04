import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static char[][] arr;
	static int[][][] dp;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static int INF = 1000000000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new char[M][N];
		int y = -1;
		int x = -1;
		int ry = -1;
		int rx = -1;

		for (int i = 0; i < M; i++) {
			String[] sarr = br.readLine().split("");
			for (int j = 0; j < N; j++) {
				arr[i][j] = sarr[j].charAt(0);
				if (arr[i][j] == 'S') {
					y = i;
					x = j;
					arr[i][j] = '.';
				} else if (arr[i][j] == 'E') {
					ry = i;
					rx = j;
				} else if (arr[i][j] == 'X') {
					arr[i][j] = (char) (K + '0');
					K++;
				}
			}
		}

		dp = new int[M][N][1 << K];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < 1 << K; k++) {
					dp[i][j][k] = INF;
				}
			}
		}

		dp[y][x][0] = 0;
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] { y, x, 0 });
		while (!q.isEmpty()) {
			int[] poll = q.poll();
			int py = poll[0];
			int px = poll[1];
			int k = poll[2];
			for (int i = 0; i < 4; i++) {
				int ny = py + dy[i];
				int nx = px + dx[i];
				if (out(ny, nx) || arr[ny][nx] == '#') {
					continue;
				}

				if (Character.isDigit(arr[ny][nx])) {
					int num = arr[ny][nx] - '0';
					int next = k | 1 << num;
					if (dp[ny][nx][next] > dp[py][px][k] + 1) {
						dp[ny][nx][next] = dp[py][px][k] + 1;
						q.add(new int[] { ny, nx, next });
					}
				} else {
					if (dp[ny][nx][k] > dp[py][px][k] + 1) {
						dp[ny][nx][k] = dp[py][px][k] + 1;
						q.add(new int[] { ny, nx, k });
					}
				}
			}
		}

		int ret = INF;
		ret = Math.min(ret, dp[ry][rx][(1 << K) - 1]);
		System.out.println(ret);
	}

	static boolean out(int y, int x) {
		return y < 0 || y >= M || x < 0 || x >= N;
	}
}