import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	static int T, N, M, K, ret;
	// 상1 우4 좌3 하2
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	static PriorityQueue<int[]> pq = new PriorityQueue<int[]>((o1, o2) -> {
		return o2[2] - o1[2];// 미생물 수로
	});// y,x,admount, dir

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			ret = 0;
			int[][][] arr = new int[N][N][2];// 미생물 수, 이동방향
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				int amount = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken()) - 1;
				arr[y][x][0] = amount;
				arr[y][x][1] = dir;
			}

			dfs(arr, 0);

			System.out.println("#" + t + " " + ret);
		}
	}

	static void dfs(int[][][] arr, int depth) {
		if (depth == M) {
			// 미생물 개수세기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					ret += arr[i][j][0];
				}
			}
			return;
		}
		int[][][] newArr = new int[N][N][2];

		// q에 담기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j][0] > 0) {
					pq.add(new int[] { i, j, arr[i][j][0], arr[i][j][1] });
				}
			}
		}

		while (!pq.isEmpty()) {
			int[] poll = pq.poll();
			int y = poll[0];
			int x = poll[1];
			int amount = poll[2];
			int dir = poll[3];

			int ny = y + dy[dir];
			int nx = x + dx[dir];
			// 가장 바깥쪽 가장자리 부분에 위치한 셀들에는 특수한 약품이 칠해져 있다.
			if (isBoundary(ny, nx)) {
				dir = reverseDir(dir);
				amount /= 2;
			}
			// 이미 있다면 amount가 큰 쪽으로 방향이 설정되고 양은 합쳐짐
			if (newArr[ny][nx][0] <= 0) {
				newArr[ny][nx][1] = dir;
			}
			newArr[ny][nx][0] += amount;
		}

		dfs(newArr, depth + 1);
	}

	static int reverseDir(int dir) {
		if (dir == 0)
			return 1;
		else if (dir == 1)
			return 0;
		else if (dir == 2)
			return 3;
		else
			return 2;
	}

	static boolean isBoundary(int y, int x) {
		return y == 0 || y == N - 1 || x == 0 || x == N - 1;
	}

	static boolean out(int y, int x) {
		return y < 0 || y >= N || x < 0 || x >= N;
	}
}
