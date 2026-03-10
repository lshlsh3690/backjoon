import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K, ret;
	static int[][] arr, graph;
	static boolean[][] visited;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static Map<Integer, List<int[]>> map;
	static List<int[]> edges;
	static int[] parent;

	static int findPraent(int x) {
		if (x != parent[x]) {
			parent[x] = findPraent(parent[x]);
		}
		return parent[x];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		ret = Integer.MAX_VALUE;
		arr = new int[N][M];
		visited = new boolean[N][M];
		map = new HashMap<Integer, List<int[]>>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		edges = new ArrayList<>();
		int numbering = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 1 && !visited[i][j]) {
					Queue<int[]> q = new ArrayDeque<int[]>();
					q.add(new int[] { i, j });
					arr[i][j] = numbering;
					visited[i][j] = true;
					List<int[]> temp = new ArrayList<int[]>();
					while (!q.isEmpty()) {
						int[] poll = q.poll();
						int py = poll[0];
						int px = poll[1];
						temp.add(new int[] { py, px });
						for (int k = 0; k < 4; k++) {
							int ny = py + dy[k];
							int nx = px + dx[k];
							if (out(ny, nx) || visited[ny][nx] || arr[ny][nx] == 0) {
								continue;
							}

							visited[ny][nx] = true;
							q.add(new int[] { ny, nx });
							arr[ny][nx] = numbering;
						}
					}

					map.put(K, temp);
					numbering++;
					K++;
				}
			}
		}

		graph = new int[K][K];
		go(0);

		if (ret == Integer.MAX_VALUE) {
			ret = -1;
		}
		System.out.println(ret);
	}

	static void go(int depth) {
		if (depth == K) {
			// 모든 섬을 다 연결했기 때문에 인접행렬로 BFS해서 인접한 갯수셈
			boolean[] visited2 = new boolean[K];
			Queue<Integer> q = new LinkedList<Integer>();
			q.add(0);
			visited2[0] = true;
			int tmpCnt = 0;
			while (!q.isEmpty()) {
				int start = q.poll();
				tmpCnt++;
				for (int j = 0; j < K; j++) {
					if (graph[start][j] == 0 || visited2[j]) {
						continue;
					}

					visited2[j] = true;
					q.add(j);
				}
			}

			// 인접한 섬의 개수가 K개가 아니면 리턴
			if (tmpCnt != K) {
				return;
			}

			// 크루스칼
			edges.sort((o1, o2) -> {
				return o1[2] - o2[2];
			});

			parent = new int[K];
			for (int i = 0; i < K; i++) {
				parent[i] = i;
			}

			int sum = 0;
			for (int[] edge : edges) {
				int start = edge[0];
				int end = edge[1];
				int cost = edge[2];
				int ps = findPraent(start);
				int pe = findPraent(end);
				if (ps == pe) {
					continue;
				} else if (ps < pe) {
					parent[pe] = ps;
				} else if (pe < ps) {
					parent[ps] = pe;
				}

				sum += cost;
			}

			ret = Math.min(ret, sum);

			return;
		}

		List<int[]> temp = map.get(depth);
		for (int[] pos : temp) {
			int y = pos[0];
			int x = pos[1];
			int start = arr[y][x] - 1;
			for (int k = 0; k < 4; k++) {
				int d = 1;
				int dest = -1;// 다른 땅에 도착하면 갱신

				boolean flag = false;
				while (true) {
					int ny = y + dy[k] * d;
					int nx = x + dx[k] * d;

					if (out(ny, nx) || arr[ny][nx] - 1 == start) {
						flag = false;
						break;
					}

					if (arr[ny][nx] > 0) {
						dest = arr[ny][nx] - 1;
						flag = true;
						break;
					}

					d++;
				}

				if (flag) {
					if (graph[start][dest] == 0 && d - 1 >= 2) {
						graph[start][dest] = 1;
						graph[dest][start] = 1;
						int[] edge = new int[] { start, dest, d - 1 };
						edges.add(edge);
						go(depth + 1);
						edges.remove(edge);
						graph[start][dest] = 0;
						graph[dest][start] = 0;
					}
				}

			}

			go(depth + 1);
		}

	}

	static boolean out(int y, int x) {
		return y < 0 || y >= N || x < 0 || x >= M;
	}
}
