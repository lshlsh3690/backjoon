import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static boolean ret = false;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		char[][] arr = new char[N][M];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = s.charAt(j);
			}
		}

		go(1, arr, -1);
		System.out.println(ret ? 1 : 0);
	}

	static void go(int depth, char[][] arr, int prevDir) {
		if (depth > 10) {
			return;
		}
		if (ret) {
			return;
		}
		// 이전 방향과 똑같은 방향으로 기울이는건 가지치기 해야될듯.
//		print(arr);

		// 이전 기울인 방향이 위쪽이 아니라면 위쪽 기울이기
		if (prevDir != 0) {
			char[][] clone = copyArr(arr);
			boolean isRedLive = true;
			boolean isBlueLive = true;
			for (int i = 1; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (clone[i][j] == 'O' || clone[i][j] == '.' || clone[i][j] == '#') {
						// 빨간 공, 파란 공 아니면 움직일 필요 없으므로
						continue;
					}

					// 위로 갈 수 잇는 인덱스 찾기
					int idx = i - 1;
					while (idx > 0) {
						if (clone[idx][j] == '#' || clone[idx][j] == 'R' || clone[idx][j] == 'B'
								|| clone[idx][j] == 'O') {
							break;
						}
						idx--;
					}

					if (clone[idx][j] == 'O') {
						if (clone[i][j] == 'R') {
							// 빨간공이 탈출함
							isRedLive = false;
						} else if (clone[i][j] == 'B') {
							// 파란공이 탈출함
							isBlueLive = false;
						}
						clone[i][j] = '.';
					} else {
						idx++;
						if (idx != i) {
							clone[idx][j] = clone[i][j];
							clone[i][j] = '.';
						}
					}
				}
			}

			// 빨간 구슬이 구멍에 빠지면 성공이지만, 파란 구슬이 구멍에 빠지면 실패이다.
			// 빨간 구슬과 파란 구슬이 동시에 구멍에 빠져도 실패이다.
			if (isBlueLive && !isRedLive) {
				ret = true;
				return;
			}

			if (isBlueLive) {
				go(depth + 1, clone, 0);
			}
		}

		if (ret) {
			return;
		}

		// 오른쪽으로 기울이기
		if (prevDir != 1) {
			char[][] clone = copyArr(arr);
			boolean isRedLive = true;
			boolean isBlueLive = true;
			for (int i = M - 2; i >= 0; i--) {
				for (int j = 0; j < N; j++) {
					if (clone[j][i] == 'O' || clone[j][i] == '.' || clone[j][i] == '#') {
						continue;
					}
					// 오른쪽으로 이동할수 잇는 위치 찾기
					int idx = i + 1;
					while (idx < M) {
						if (clone[j][idx] == '#' || clone[j][idx] == 'R' || clone[j][idx] == 'B') {
							break;
						}

						if (clone[j][idx] == 'O') {
							break;
						}
						idx++;
					}

					if (clone[j][idx] == '#' || clone[j][idx] == 'R' || clone[j][idx] == 'B') {
						idx--;
						if (idx != i) {
							clone[j][idx] = clone[j][i];
							clone[j][i] = '.';
						}
					} else if (clone[j][idx] == 'O') {
						if (clone[j][i] == 'R') {
							isRedLive = false;
						} else if (clone[j][i] == 'B') {
							isBlueLive = false;
						}
						clone[j][i] = '.';
					}
				}
			}

			if (isBlueLive && !isRedLive) {
				ret = true;
				return;
			}

			if (isBlueLive) {
				go(depth + 1, clone, 1);
			}
		}

		if (ret) {
			return;
		}

		// 이전 방향이 아래쪽이 아니었으면 아래쪽으로 기울이기
		if (prevDir != 2)

		{
			char[][] clone = copyArr(arr);
			boolean isRedLive = true;
			boolean isBlueLive = true;

			for (int i = N - 2; i >= 0; i--) {
				for (int j = 0; j < M; j++) {
					if (clone[i][j] == '#' || clone[i][j] == '.' || clone[i][j] == 'O') {
						continue;
					}

					int idx = i + 1;
					while (idx < N) {
						if (clone[idx][j] == '#' || clone[idx][j] == 'R' || clone[idx][j] == 'B') {
							break;
						}

						if (clone[idx][j] == 'O') {
							break;
						}

						idx++;
					}

					if (clone[idx][j] == '#' || clone[idx][j] == 'R' || clone[idx][j] == 'B') {
						idx--;
						if (idx != i) {
							clone[idx][j] = clone[i][j];
							clone[i][j] = '.';
						}
					} else if (clone[idx][j] == 'O') {
						if (clone[i][j] == 'R') {
							isRedLive = false;
						} else if (clone[i][j] == 'B') {
							isBlueLive = false;
						}
						clone[i][j] = '.';
					}
				}
			}

			if (isBlueLive && !isRedLive) {
				ret = true;
				return;
			}

			if (isBlueLive) {
				go(depth + 1, clone, 2);
			}

		}

		if (ret) {
			return;
		}

		if (prevDir != 3) {
			char[][] clone = copyArr(arr);
			boolean isRedLive = true;
			boolean isBlueLive = true;

			for (int i = 1; i < M; i++) {
				for (int j = 0; j < N; j++) {
					if (clone[j][i] == '.' || clone[j][i] == '#' || clone[j][i] == 'O') {
						continue;
					}

					int idx = i - 1;
					while (idx > 0) {
						if (clone[j][idx] == '#' || clone[j][idx] == 'R' || clone[j][idx] == 'B') {
							break;
						}

						if (clone[j][idx] == 'O') {
							break;
						}
						idx--;
					}

					if (clone[j][idx] == '#' || clone[j][idx] == 'R' || clone[j][idx] == 'B') {
						idx++;
						if (idx != i) {
							clone[j][idx] = clone[j][i];
							clone[j][i] = '.';
						}
					} else if (clone[j][idx] == 'O') {
						if (clone[j][i] == 'R') {
							isRedLive = false;
						} else if (clone[j][i] == 'B') {
							isBlueLive = false;
						}
						clone[j][i] = '.';
					}
				}
			}

			if (isBlueLive && !isRedLive) {
				ret = true;
				return;
			}

			if (isBlueLive) {
				go(depth + 1, clone, 3);
			}
		}
	}

	static void print(char[][] arr) {
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(arr[i]));
		}
		System.out.println();
		System.out.println();
	}

	static char[][] copyArr(char[][] arr) {
		char[][] temp = new char[N][M];
		for (int i = 0; i < N; i++) {
			temp[i] = arr[i].clone();
		}
		return temp;
	}
}
