import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

//손님을 도착지로 데려다줄 때마다 연료가 충전되고, 연료가 바닥나면 그 날의 업무가 끝난다.
//여러 승객이 같이 탑승하는 경우는 없다. 
//. 0은 빈칸, 1은 벽을 나

public class Main {
	static int N, M, fuel, cury,curx;
	static int[][] arr, dp;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static List<int[]> customer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		customer = new ArrayList<int[]>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine(), " ");
		cury = Integer.parseInt(st.nextToken()) - 1;
		curx = Integer.parseInt(st.nextToken()) - 1;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int sy = Integer.parseInt(st.nextToken()) - 1;
			int sx = Integer.parseInt(st.nextToken()) - 1;
			int dy = Integer.parseInt(st.nextToken()) - 1;
			int dx = Integer.parseInt(st.nextToken()) - 1;

			customer.add(new int[] { sy, sx, dy, dx });
		}

		// 현재 위치에서 최단거리가 가장 짧은 승객을 고른다.
		// 승객이 여러 명이면 행 번호가 가장 작은 그중 열 번호가 가장 작은 승객을 고른
		
		
		while (fuel > 0) {
			// dp에 최단시간 저장
			bfs(cury, curx);
			int[] rc = null;
			int min = Integer.MAX_VALUE;
			for (int[] c : customer) {
				int srcy = c[0];
				int srcx = c[1];
			
				//도달할 수 없는 승객을 제외
				if(dp[srcy][srcx] == Integer.MAX_VALUE)continue;
				// 최단 거리
				if (dp[srcy][srcx] < min) {
					rc = c;
					min = dp[srcy][srcx];

					// 여러 명이면 행 번호가 가장 작은 승객
				} else if (dp[srcy][srcx] == min) {
					int customery = rc[0];
					int customerx = rc[1];

					if (customery > srcy) {
						rc = c;
						min = dp[srcy][srcx];
						// 그중 열 번호가 가장 작은 승객을 고른
					} else if (customery == srcy) {
						if (customerx > srcx) {
							rc = c;
							min = dp[srcy][srcx];
						}
					}
				}
			}

			// 승객 태우면서 연료 소모
			// 승객의 위치로 이동
			if(rc == null) {
				fuel = -1;
				break;
			}
			cury = rc[0];
			curx = rc[1];
			fuel -= dp[cury][curx];
			if (fuel <= 0) {
				break;
			}

			// 승객입장에서 목적지까지 최단거리를 또 구해야됌
			bfs(cury, curx);
			int desty = rc[2];
			int destx = rc[3];
			if(dp[desty][destx] == Integer.MAX_VALUE) {
				fuel = -1;
				break;
			}
			fuel -= dp[desty][destx];
			if (fuel < 0)
				break;
			fuel += (dp[desty][destx] * 2);
			customer.remove(rc);
			cury = desty;
			curx = destx;
			if(customer.size() == 0)
				break;
		}

		System.out.println(fuel < 0 ? "-1" : fuel);
	}

	public static void bfs(int y, int x) {
		dp = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dp[i][j] = Integer.MAX_VALUE;
			}
		}
		boolean[][] visited = new boolean[N][N];
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.add(new int[] { y, x });
		dp[y][x] = 0;
		while (!q.isEmpty()) {
			int[] poll = q.poll();
			int cy = poll[0];
			int cx = poll[1];
			for (int i = 0; i < 4; i++) {
				int ny = cy + dy[i];
				int nx = cx + dx[i];
				if (ny < 0 || ny >= N || nx < 0 || nx >= N || visited[ny][nx] || arr[ny][nx] == 1)
					continue;
				if (dp[ny][nx] < dp[cy][cx] + 1)
					continue;

				visited[ny][nx] = true;
				dp[ny][nx] = dp[cy][cx] + 1;
				q.add(new int[] { ny, nx });
			}
		}
	}
}
