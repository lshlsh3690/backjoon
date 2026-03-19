import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static int[][] arr, ret;
	static Map<Integer, List<int[]>> map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N][M];
		ret = new int[N][M];
		map = new HashMap<Integer, List<int[]>>();

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = s.charAt(j) - '0';
			}
		}

		int numbering = 2;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 1) {
					ret[i][j] = 1;
					continue;
				}

				if (arr[i][j] > 0) {
					continue;
				}

				Queue<int[]> q = new ArrayDeque<int[]>();
				q.add(new int[] { i, j });
				List<int[]> temp = new ArrayList<int[]>();
				arr[i][j] = numbering;
				while (!q.isEmpty()) {
					int[] poll = q.poll();
					int y = poll[0];
					int x = poll[1];
					temp.add(new int[] { y, x });

					for (int k = 0; k < 4; k++) {
						int ny = y + dy[k];
						int nx = x + dx[k];
						if (out(ny, nx) || arr[ny][nx] == 1) {
							continue;
						}

						if (arr[ny][nx] == 0) {
							q.add(new int[] { ny, nx });
							arr[ny][nx] = numbering;
						}
					}
				}

				map.put(numbering, temp);
				numbering++;
			}
		}

		for (int n = 2; n < numbering; n++) {
			List<int[]> temp = map.get((Integer) n);
			int cnt = temp.size();

			Set<Integer> set = new HashSet<>();
			for (int[] pos : temp) {
				int y = pos[0];
				int x = pos[1];
				for (int k = 0; k < 4; k++) {
					int ny = y + dy[k];
					int nx = x + dx[k];
					if (out(ny, nx) || arr[ny][nx] != 1) {
						continue;
					}

					int posN = ny * M + nx;
					set.add(posN);
				}
			}

			for (Integer posN : set) {
				int y = posN / M;
				int x = posN % M;

				ret[y][x] = (ret[y][x] + cnt) % 10;
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(ret[i][j]);
			}
			System.out.println();
		}

	}

	static boolean out(int y, int x) {
		return y < 0 || y >= N || x < 0 || x >= M;
	}
}
