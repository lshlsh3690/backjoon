import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int N, M, result = Integer.MAX_VALUE;
	public static int[] dy = { -1, 0, 1, 0 };
	public static int[] dx = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		char[][] arr = new char[N][M];

		for (int i = 0; i < N; i++) {
			String[] sarr = br.readLine().split("");
			for (int j = 0; j < M; j++) {
				arr[i][j] = sarr[j].charAt(0);
			}
		}

		dfs(arr, 1, -1);
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}

	public static void dfs(char[][] arr, int depth, int prevDirection) {
		if (depth > 10) {
			return;
		}

		char[][] temp;
		temp = arr.clone();
		for (int i = 0; i < N; i++) {
			temp[i] = arr[i].clone();
		}

		
		boolean isRedOver = false;
		boolean isBlueOver = false;
		if (prevDirection != 1) {

			// 위로 기울이기
			up: for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (temp[i][j] == '#' || temp[i][j] == '.' || temp[i][j] == 'O')
						continue;
					int y = i;
					int x = j;
					while (true) {
						int ny = y - 1;
						int nx = x;
						if (temp[ny][nx] == 'O') {
							if (temp[i][j] == 'R') {
								isRedOver = true;
							}else if (temp[i][j]=='B' ) {
								isBlueOver = true;
							}
							temp[i][j]= '.';
						}
						if (temp[ny][nx] == '#' || temp[ny][nx] == 'R' || temp[ny][nx] == 'B') {
							break;
						}
						y = ny;
						x = nx;
					}

					if (temp[y][x] == '.') {
						temp[y][x] = temp[i][j];
						temp[i][j] = '.';
					}
				}
			}

			if (!isRedOver && !isBlueOver) {
				dfs(temp, depth + 1, 1);
			}else if(isRedOver && !isBlueOver) {
				result = Math.min(result, depth);
			}
		}
		
//		printArr(temp);

		if (prevDirection != 2) {
			isBlueOver = false;
			isRedOver = false;
			temp = arr.clone();
			for (int i = 0; i < N; i++) {
				temp[i] = arr[i].clone();
			}
			// 오른쪽으로 기울이기
			right: for (int i = M - 1; i >= 0; i--) {
				for (int j = 0; j < N; j++) {
					if (temp[j][i] == '#' || temp[j][i] == '.' || temp[j][i] == 'O')
						continue;
					int y = j;
					int x = i;
					while (true) {
						int ny = y;
						int nx = x + 1;
						if (temp[ny][nx] == 'O') {
							if (temp[j][i] == 'R') {
								isRedOver = true;
							}else if (temp[j][i]=='B' ) {
								isBlueOver=true;
							}
							temp[j][i]= '.';
						}
						if (temp[ny][nx] == '#' || temp[ny][nx] == 'R' || temp[ny][nx] == 'B') {
							break;
						}
						y = ny;
						x = nx;
					}

					if (temp[y][x] == '.') {
						temp[y][x] = temp[j][i];
						temp[j][i] = '.';
					}
				}
			}

			if (!isRedOver && !isBlueOver) {
				dfs(temp, depth + 1, 2);
			}else if(isRedOver && !isBlueOver) {
				result = Math.min(result, depth);
			}
		}
		
//		printArr(temp);

		if (prevDirection != 3) {
			isBlueOver = false;
			isRedOver = false;
			temp = arr.clone();
			for (int i = 0; i < N; i++) {
				temp[i] = arr[i].clone();
			}
			// 아래쪽으로 기울이기
			down: for (int i = N - 1; i >= 0; i--) {
				for (int j = 0; j < M; j++) {
					if (temp[i][j] == '#' || temp[i][j] == '.' || temp[i][j] == 'O')
						continue;
					int y = i;
					int x = j;
					while (true) {
						int ny = y + 1;
						int nx = x;
						if (temp[ny][nx] == 'O') {
							if (temp[i][j] == 'R') {
								isRedOver = true;
							}else if (temp[i][j]=='B' ) {
								isBlueOver = true;
							}
							temp[i][j]= '.';
						}
						if (temp[ny][nx] == '#' || temp[ny][nx] == 'R' || temp[ny][nx] == 'B') {
							break;
						}
						y = ny;
						x = nx;
					}

					if (temp[y][x] == '.') {
						temp[y][x] = temp[i][j];
						temp[i][j] = '.';
					}
				}
			}

			if (!isRedOver && !isBlueOver) {
				dfs(temp, depth + 1, 3);
			}else if(isRedOver && !isBlueOver) {
				result = Math.min(result, depth);
			}
		}
		
//		printArr(temp);

		if (prevDirection != 4) {
			isBlueOver = false;
			isRedOver = false;
			temp = arr.clone();
			for (int i = 0; i < N; i++) {
				temp[i] = arr[i].clone();
			}
			// 왼쪽으로 기울이기
			down: for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					if (temp[j][i] == '#' || temp[j][i] == '.' || temp[j][i] == 'O')
						continue;
					int y = j;
					int x = i;
					while (true) {
						int ny = y;
						int nx = x-1;
						if (temp[ny][nx] == 'O') {
							if (temp[j][i] == 'R') {
								isRedOver = true;
							}else if (temp[j][i]== 'B' ) {
								isBlueOver = true;
							}
							temp[j][i]= '.';

						}
						if (temp[ny][nx] == '#' || temp[ny][nx] == 'R' || temp[ny][nx] == 'B') {
							break;
						}
						y = ny;
						x = nx;
					}

					if (temp[y][x] == '.') {
						temp[y][x] = temp[j][i];
						temp[j][i] = '.';
					}
				}
			}

			if (!isRedOver && !isBlueOver) {
				dfs(temp, depth + 1, 4);
			}else if(isRedOver && !isBlueOver) {
				result = Math.min(result, depth);
			}
		}
		
//		printArr(temp);

		
	}
	public static void printArr(char[][]temp) {
		System.out.println();
		for(int i = 0;i<N;i++) {
			
			for(int j = 0;j<M;j++) {
				System.out.print(temp[i][j]+ " ");
			}
			System.out.println();
		}
	}

}
