import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	static int H, W;
	static int[][] arr;

	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		H = 12;
		W = 6;

		arr = new int[H][W];

		// R은 빨강, G는 초록, B는 파랑, P는 보라, Y는 노랑이다.
		// R=1, G=2, B=3, P=4, Y=5;
		for (int i = 0; i < H; i++) {
			String s = br.readLine();
			for (int j = 0; j < W; j++) {
				char c = s.charAt(j);
				if (c == '.') {
					arr[i][j] = 0;
				} else if (c == 'R') {
					arr[i][j] = 1;
				} else if (c == 'G') {
					arr[i][j] = 2;
				} else if (c == 'B') {
					arr[i][j] = 3;
				} else if (c == 'P') {
					arr[i][j] = 4;
				} else if (c == 'Y') {
					arr[i][j] = 5;
				}
			}
		}

		down();

		// 터질 수 있는 뿌요가 여러 그룹이 있다면 동시에 터져야 하고
		// 여러 그룹이 터지더라도 한번의 연쇄가 추가된다.
		int ret = 0;
		while (true) {
			boolean flag = false;
			boolean[][] visited = new boolean[H][W];

			for (int h = H - 1; h >= 0; h--) {
				for (int w = 0; w < W; w++) {
					if (arr[h][w] == 0 || visited[h][w]) {
						continue;
					}

					int pivot = arr[h][w];

					Queue<int[]> q = new ArrayDeque<int[]>();
					Queue<int[]> q2 = new ArrayDeque<>();
					q.add(new int[] { h, w });
					visited[h][w] = true;

					while (!q.isEmpty()) {
						int[] poll = q.poll();
						int y = poll[0];
						int x = poll[1];
						q2.add(new int[] { y, x });

						for (int k = 0; k < 4; k++) {
							int ny = y + dy[k];
							int nx = x + dx[k];

							if (out(ny, nx) || visited[ny][nx]) {
								continue;
							}

							if (arr[ny][nx] == pivot) {
								q.add(new int[] { ny, nx });
								visited[ny][nx] = true;
							}
						}
					}

					if (q2.size() >= 4) {
						flag = true;
						while (!q2.isEmpty()) {
							int[] poll = q2.poll();
							int y = poll[0];
							int x = poll[1];
							arr[y][x] = 0;
						}
					}
				}
			}

			if (!flag) {
				break;
			} else {
				ret++;
				down();
			}
		}

		System.out.println(ret);
	}

	static void down() {
		for (int w = 0; w < W; w++) {
			for (int h = H - 1; h > 0; h--) {
				if (arr[h][w] != 0) {
					continue;
				}

				// arr[h][w]가 빈칸이면 위에서 숫자 한개를 찾아서 여기에 가져옴
				int target = h - 1;
				while (target >= 0 && arr[target][w] == 0) {
					target--;
				}

				if (target < 0) {
					// -1을 가르키면 위에가 다 0이란 뜻
					break;
				}

				arr[h][w] = arr[target][w];
				arr[target][w] = 0;
			}
		}

//		System.out.println();
//		for (int i = 0; i < H; i++) {
//			System.out.println(Arrays.toString(arr[i]));
//		}
//		System.out.println();
	}

	static boolean out(int y, int x) {
		return y < 0 || y >= H || x < 0 || x >= W;
	}
}
