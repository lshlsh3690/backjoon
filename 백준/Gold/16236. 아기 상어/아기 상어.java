import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, size, cnt;
	static int INF = 1000000000;
	static int[][] arr;
	static int[] dy = { -1, 0, 0, 1 };
	static int[] dx = { 0, -1, 1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		int cury = -1;
		int curx = -1;
		size = 2;
		cnt = 2;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 9) {
					cury = i;
					curx = j;
					arr[i][j] = 0;
				}
			}
		}

		int ret = 0;
		while (true) {
			int[][] dp = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dp[i][j] = INF;
				}
			}

			dp[cury][curx] = 0;
			Queue<int[]> q = new ArrayDeque<int[]>();
			q.add(new int[] { cury, curx });
			while (!q.isEmpty()) {
				int[] poll = q.poll();
				int py = poll[0];
				int px = poll[1];
				for (int k = 0; k < 4; k++) {
					int ny = py + dy[k];
					int nx = px + dx[k];
					if (out(ny, nx)) {
						continue;
					}

					if (arr[ny][nx] > size) {
						continue;
					}

					if (dp[ny][nx] > dp[py][px] + 1) {
						dp[ny][nx] = dp[py][px] + 1;
						q.add(new int[] { ny, nx });
					}
				}
			}

			PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
				if (o1[2] == o2[2]) {
					if (o1[0] == o2[0]) {
						return o1[1] - o2[1];
					}
					return o1[0] - o2[0];
				}

				return o1[2] - o2[2];
			});

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] < size && arr[i][j] != 0 && dp[i][j]!= INF ) {
						pq.add(new int[] { i, j, dp[i][j] });
					}
				}
			}

			if (pq.isEmpty()) {
				break;
			} else {
				int[] target = pq.poll();
				ret += target[2];
				arr[target[0]][target[1]] = 0;
				cury = target[0];
				curx = target[1];
				cnt--;
				if (cnt == 0) {
					size++;
					cnt = size;
				}
			}

		}

		System.out.println(ret);
	}

	static boolean out(int y, int x) {
		return y < 0 || y >= N || x < 0 || x >= N;
	}
}
