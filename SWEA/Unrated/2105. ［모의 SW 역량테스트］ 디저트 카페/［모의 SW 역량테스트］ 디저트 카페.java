
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Year;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int T, N, ret;
	static int[][] arr;
	static int[] dy = { 1, 1, -1, -1 };
	static int[] dx = { 1, -1, -1, 1 };
	static int[] dyLeft = {};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			ret = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 1; j < N - 1; j++) {
					// 마름모 탐색
					// 우하 탐색 끝나는 노드가 i+1,j-1일때 종료
					ArrayDeque<int[]> q = new ArrayDeque<int[]>();
					q.add(new int[] { i, j });
					dfs(q, 0);
				}
			}

			System.out.println("#" + t + " " + (ret == Integer.MIN_VALUE ? -1 : ret));
		}
	}

	static void dfs(ArrayDeque<int[]> q, int dir) {
		if (dir == 4) {
			return;
		}
		// 다음 노드 탐색
		// 다음 노드가 Q에 있다면 가지치기 return
		int ny = q.getLast()[0] + dy[dir];
		int nx = q.getLast()[1] + dx[dir];
		if (out(ny, nx)) {
			return;
		}

		
		if (ny == q.getFirst()[0] && nx == q.getFirst()[1]) {
			// 마름모 완성
			ret = Math.max(ret, q.size());
//			for (int[] arr : q) {
//				System.out.print(Arrays.toString(arr) + " ");
//			}
//			System.out.println();
			return;
		}

		// 다음노드가 이미 Q에 있다면 가지치기 종료
		for (int[] pos : q) {
			if (arr[ny][nx] == arr[pos[0]][pos[1]]) {
				return;
			}
		}

		q.add(new int[] { ny, nx });
		// 방향 유지 그대로 다음 노드
		dfs(q, dir);
		// 방향 꺽어서 다음 노드
		dfs(q, dir + 1);
		
		q.pollLast();
	}

	static boolean out(int y, int x) {
		return y < 0 || y >= N || x < 0 || x >= N;
	}
}
