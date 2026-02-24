import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int T, N, W, H, ret;

	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			ret = Integer.MAX_VALUE;
			int[][] arr = new int[H][W];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < W; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 가로로 이동하면서
			for (int i = 0; i < W; i++) {
				dfs(i, 0, arr);
			}
			System.out.println("#" + t + " " + ret);
		}

	}

	static void dfs(int x, int depth, int[][] arr) {
		if (depth == N) {
			// 블록의 최소 개수 구하기
			int cnt = 0;
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (arr[i][j] > 0)
						cnt++;
				}
			}

			ret = Math.min(ret, cnt);
			return;
		}

		// 배열 복사
		int[][] clone = new int[H][W];
		for (int i = 0; i < H; i++) {
			clone[i] = arr[i].clone();
		}

		// y의 좌표를 위에서부터 내려가면서 0이 아닌 걸 찾음
		int y = 0;
		for (; y < H; y++) {
			if (clone[y][x] != 0) {
				break;
			}
		}
		Queue<int[]> q = new LinkedList<>();
		if (y < H) {
			q.add(new int[] { y, x, clone[y][x] });
		}

		while (!q.isEmpty()) {
			int[] poll = q.poll();
			int py = poll[0];
			int px = poll[1];
			int distance = poll[2] - 1;
			clone[py][px] = 0;
			for (int k = 0; k < 4; k++) {
				for (int d = 1; d <= distance; d++) {
					int ny = py + (dy[k] * d);
					int nx = px + (dx[k] * d);
					if (out(ny, nx) || clone[ny][nx] == 0) {
						continue;
					}

					if (clone[ny][nx] == 1) {
						clone[ny][nx] = 0;
						continue;
					}
					q.add(new int[] { ny, nx, clone[ny][nx] });
					clone[ny][nx] = 0;
				}
			}
		}

		// 밑으로 내리기
		for (int i = 0; i < W; i++) {
			for (int j = H - 1; j >= 1; j--) {
				// 밑에서 부터 0의 위치를 찾는다.
				if (clone[j][i] != 0)
					continue;
				int idx = -1;
				for (int k = j - 1; k >= 0; k--) {
					if (clone[k][i] != 0) {
						idx = k;
						break;
					}
				}
				// idx 가 -1 이면 0이 아닌 수를 못 찾음
				if (idx == -1) {
					break;
				}

				clone[j][i] = clone[idx][i];
				clone[idx][i] = 0;
			}
		}

		// 가로로 이동하면서 다음 dfs 들어가기
		for (int i = 0; i < W; i++) {
			dfs(i, depth + 1, clone);
		}

	}

	static boolean out(int y, int x) {
		return y < 0 || y >= H || x < 0 || x >= W;
	}
}
