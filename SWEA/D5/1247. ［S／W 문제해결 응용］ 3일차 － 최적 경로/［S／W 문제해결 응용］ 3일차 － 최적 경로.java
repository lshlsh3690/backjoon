import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int T, N, ret;
	static int[] X, Y;
	static int startY, startX;
	static int destY, destX;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			ret = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			Y = new int[N];
			X = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			startX = Integer.parseInt(st.nextToken());
			startY = Integer.parseInt(st.nextToken());
			destX = Integer.parseInt(st.nextToken());
			destY = Integer.parseInt(st.nextToken());
			for (int i = 0; i < N; i++) {
				X[i] = Integer.parseInt(st.nextToken());
				Y[i] = Integer.parseInt(st.nextToken());
			}

			visited = new boolean[N];
			for (int i = 0; i < N; i++) {
				int dy = Math.abs(startY - Y[i]);
				int dx = Math.abs(startX - X[i]);
				visited[i] = true;
				go(1, Y[i], X[i], dy + dx);
				visited[i] = false;
			}

			System.out.println("#" + t + " " + ret);
		}
	}

	static void go(int depth, int curY, int curX, int sum) {
		if (depth == N) {
			int dx = Math.abs(destX - curX);
			int dy = Math.abs(destY - curY);

			ret = Math.min(ret, dx + dy + sum);

			return;
		}

		for (int i = 0; i < N; i++) {
			if (visited[i]) {
				continue;
			}

			visited[i] = true;

			int dy = Math.abs(curY - Y[i]);
			int dx = Math.abs(curX - X[i]);

			go(depth + 1, Y[i], X[i], sum + dy + dx);

			visited[i] = false;
		}

	}
}
