import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Solution {
	public static int T, count, totalEnergy;
	public static int[] dy = { 1, -1, 0, 0 };
	public static int[] dx = { 0, 0, -1, 1 };
	public static int N = 4001;
	public static int[][] map = new int[N][N];
	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			totalEnergy = 0;//
			count = Integer.parseInt(br.readLine());

			ArrayDeque<Unit> dq = new ArrayDeque<>();

			for (int i = 0; i < count; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");

				int x = (Integer.parseInt(st.nextToken()) + 1000) << 1;
				int y = (Integer.parseInt(st.nextToken()) + 1000) << 1;
				int dir = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());

				map[y][x] = e;
				dq.addLast(new Unit(x, y, dir, e));
			}

			while (!dq.isEmpty()) {
				Unit cur = dq.pollFirst();

				// 1. 충돌 확인
				if (map[cur.y][cur.x] != cur.e) {
					totalEnergy += map[cur.y][cur.x];
					map[cur.y][cur.x] = 0;
					continue;
				}

				// 2. 충돌되지 않았으면 다음 방향에 기록 후 덱에 넣기
				map[cur.y][cur.x] = 0;
				int nx = cur.x + dx[cur.dir];
				int ny = cur.y + dy[cur.dir];

				if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
					cur.x = nx;
					cur.y = ny;
					map[cur.y][cur.x] += cur.e;
					dq.addLast(cur);

				}

			}

			System.out.println("#" + t + " " + totalEnergy);
		}
	}

	static class Unit {
		int x, y, dir, e;

		public Unit(int x, int y, int dir, int e) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.e = e;
		}
	}
}
