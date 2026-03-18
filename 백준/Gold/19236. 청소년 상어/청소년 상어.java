import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N = 4, ret;
	static int[][][] map;

	static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dx = { 0, -1, -1, -1, 0, 1, 1, 1 };

	static int[] fy, fx, fdir;// 물고기 좌표

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new int[4][4][2];

		fy = new int[16];
		fx = new int[16];
		fdir = new int[16];

		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 4; j++) {
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken()) - 1;

				map[i][j][0] = num;
				map[i][j][1] = dir;
			}
		}

		// 청소년 상어는 (0, 0)에 있는 물고기를 먹고, (0, 0)에 들어가게 된다.
		// -1은 상어 0은 빈칸 양수는 다른 물고기
		ret += map[0][0][0];
		map[0][0][0] = -1;

		// 상어가 이동할 수 있는 칸이 없으면 공간에서 벗어나 집으로 간다.
		// 상어가 이동한 후에는 다시 물고기가 이동하며, 이후 이 과정이 계속해서 반복된다.

		go(ret, map);

		System.out.println(ret);
	}

	static void go(int sum, int[][][] arr) {
		ret = Math.max(ret, sum);

		int sy = -1;
		int sx = -1;
		int sdir = -1;
		int fishNum = 1;
		// 물고기 이동
		while (fishNum <= 16) {
			move: for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					int num = arr[i][j][0];
					int dir = arr[i][j][1];

					if (num == 0) {// 빈칸인 경우 스킵
						continue;
					}
					if (fishNum == num) {
						int y = i;
						int x = j;
						int ny = y + dy[dir];
						int nx = x + dx[dir];

						if (out(ny, nx) || arr[ny][nx][0] == -1) {
							// 이동못하는 경우
							for (int k = 0; k < 8; k++) {
								int kDir = (dir + k) % 8;
								ny = y + dy[kDir];
								nx = x + dx[kDir];
								if (out(ny, nx) || arr[ny][nx][0] == -1) {
									continue;
								}
								// 이동하는 경우 물고기의 자리를 바꾸는 방식으로 이동함
								int tempNum = arr[ny][nx][0]; // 다음 물고기의번호
								int tempDir = arr[ny][nx][1];

								arr[ny][nx][0] = arr[y][x][0];
								arr[ny][nx][1] = kDir;

								arr[y][x][0] = tempNum;
								arr[y][x][1] = tempDir;
								break move;
							}
						} else {
							// 이동을 하는 경우
							int tempNum = arr[ny][nx][0];
							int tempDir = arr[ny][nx][1];

							arr[ny][nx][0] = arr[y][x][0];
							arr[ny][nx][1] = arr[y][x][1];

							arr[y][x][0] = tempNum;
							arr[y][x][1] = tempDir;
							break move;
						}
					} else if (num == -1) {
						// 상어임
						sy = i;
						sx = j;
						sdir = dir;
					}
				}
			}

			fishNum++;
		}

		// 물고기의 이동이 끝나고 상어의 이동이 일어남
		List<int[]> list = new ArrayList<>();
		int d = 1;
		while (true) {
			int sny = sy + (dy[sdir] * d);
			int snx = sx + (dx[sdir] * d);

			if (out(sny, snx)) {
				break;
			}

			d++;
			if (arr[sny][snx][0] == 0) {
				continue;
			}

			list.add(new int[] { sny, snx });
		}

		for (int i = 0; i < list.size(); i++) {
			// 상어의 다음 좌표
			int[][][] arr2 = new int[4][4][2];
			for (int a = 0; a < 4; a++) {
				for (int b = 0; b < 4; b++) {
					arr2[a][b][0] = arr[a][b][0];
					arr2[a][b][1] = arr[a][b][1];
				}
			}

			int sny = list.get(i)[0];
			int snx = list.get(i)[1];
			int nextSum = sum + arr2[sny][snx][0];
			int nextDir = arr2[sny][snx][1];

			arr2[sy][sx][0] = 0;
			arr2[sy][sx][1] = 0;
			arr2[sny][snx][0] = -1;
			arr2[sny][snx][1] = nextDir;

			go(nextSum, arr2);
		}
	}

	static boolean out(int y, int x) {
		return y < 0 || y >= 4 || x < 0 || x >= 4;
	}
}
