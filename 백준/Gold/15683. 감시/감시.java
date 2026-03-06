import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static int N, M, cnt, ret;
	static int[][] arr;
	static Map<Integer, Long> map;
	static boolean[] visited;

	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	static long key(int y, int x) {
		return (((long) y) << 32) ^ (x & 0xffffffffL);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		ret = Integer.MAX_VALUE;
		map = new HashMap<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] > 0 && arr[i][j] < 6) {
					Long posKey = key(i, j);
					map.put(++cnt, posKey);
				}
			}
		}

		visited = new boolean[cnt + 1];

		go(0, 1);
		System.out.println(ret);
	}

	static void go(int depth, int idx) {
		if (depth == cnt) {
			int tCnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (arr[i][j] == 0) {
						tCnt++;
					}
				}
			}

			ret = Math.min(ret, tCnt);
			return;
		}

		for (int i = idx; i <= cnt; i++) {
			if (visited[i]) {
				continue;
			}
			visited[i] = true;
			long pos = map.get(i);

			int y = (int) (pos >> 32);
			int x = (int) pos;
			List<Integer> direction = new ArrayList<>();
			if (arr[y][x] == 1) {
				direction.add(0);
			} else if (arr[y][x] == 2) {
				direction.add(0);
				direction.add(2);
			} else if (arr[y][x] == 3) {
				direction.add(0);
				direction.add(-1);
			} else if (arr[y][x] == 4) {
				direction.add(0);
				direction.add(-1);
				direction.add(-2);
			} else if (arr[y][x] == 5) {
				direction.add(0);
				direction.add(1);
				direction.add(2);
				direction.add(3);
			}
			// 4방향 탐색
			for (int k = 0; k < 4; k++) {
				List<int[]> temp = new ArrayList<>();
				for (int dir : direction) {
					int d = 1;
					while (true) {
						int tempDir = k + dir;
						if (tempDir < 0) {
							tempDir += 4;
							;
						} else if (tempDir >= 4) {
							tempDir %= 4;
						}
						int ny = y + (dy[tempDir] * d);
						int nx = x + (dx[tempDir] * d);
						if (out(ny, nx) || arr[ny][nx] == 6) {
							break;
						}
						if (arr[ny][nx] == 0) {
							temp.add(new int[] { ny, nx });
							arr[ny][nx] = -1;
						}
						d++;
					}
				}
				go(depth + 1, i);
				for (int[] t : temp) {
					arr[t[0]][t[1]] = 0;
				}
			}
			visited[i] = false;
		}
	}

	static boolean out(int y, int x) {
		return y < 0 || y >= N || x < 0 || x >= M;
	}
}
