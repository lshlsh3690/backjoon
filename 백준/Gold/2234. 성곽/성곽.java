import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int N, M, cntRoom = 0, maxRoom = 0, wallRoom = 0;
	public static int[][] arr;
	public static boolean[][] visited;
	public static int[] dy = { 0, -1, 0, 1 };
	public static int[] dx = { -1, 0, 1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		Queue<int[]> q = new LinkedList<int[]>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int cnt = 0;
				if (!visited[i][j]) {
					cntRoom++;
					visited[i][j] = true;
					q.add(new int[] { i, j });
					while (!q.isEmpty()) {
						int[] poll = q.poll();
						int y = poll[0];
						int x = poll[1];
						cnt++;
						for (int k = 0; k < 4; k++) {
							// 서쪽에 벽이 있을 때는 1을,
							// 북쪽에 벽이 있을 때는 2를,
							// 동쪽에 벽이 있을 때는 4를,
							// 남쪽에 벽이 있을 때는 8을 더한 값이 주어진다.
							int ny = y + dy[k];
							int nx = x + dx[k];
							if (ny < 0 || ny >= N || nx < 0 || nx >= M || visited[ny][nx]) {
								continue;
							}

							if ((arr[y][x] & 1 << k) == 0) {
								visited[ny][nx] = true;
								q.add(new int[] { ny, nx });
							} else {
								// dfs로 벽 깨는 경우
								dfs(y, x, k);
							}
						}
					}
				}
				maxRoom = Math.max(cnt, maxRoom);
			}
		}

		System.out.println(cntRoom);
		System.out.println(maxRoom);
		System.out.println(wallRoom);

	}

	public static void dfs(int y, int x, int k) {
		boolean[][] visited2 = new boolean[N][M];
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { y, x });
		q.add(new int[] { y + dy[k], x + dx[k] });

		int cnt = 0;
		while (!q.isEmpty()) {
			int[] poll = q.poll();
			int curY = poll[0];
			int curX = poll[1];
			cnt++;
			visited2[curY][curX] = true;
			for (int k2 = 0; k2 < 4; k2++) {
				int ny = curY + dy[k2];
				int nx = curX + dx[k2];
				if (ny < 0 || ny >= N || nx < 0 || nx >= M || visited2[ny][nx]) {
					continue;
				}

				if ((arr[curY][curX] & 1 << k2) == 0) {
					visited2[ny][nx] = true;
					q.add(new int[] { ny, nx });
				}
			}
		}

		wallRoom = Math.max(cnt, wallRoom);
	}

}
