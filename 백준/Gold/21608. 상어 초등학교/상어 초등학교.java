import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class Student {
		int num;
		int y;
		int x;
		int[] loveNum = new int[4];
	}

	static int N;

	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static int[][] arr;
	static Student[] students;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		students = new Student[(N * N) + 1];
		for (int i = 0; i < N * N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int studentNum = Integer.parseInt(st.nextToken());
			students[i] = new Student();
			students[i].num = studentNum;
			int love1 = Integer.parseInt(st.nextToken());
			int love2 = Integer.parseInt(st.nextToken());
			int love3 = Integer.parseInt(st.nextToken());
			int love4 = Integer.parseInt(st.nextToken());
			students[i].loveNum = new int[] { love1, love2, love3, love4 };
		}

		// 좋아하는 학생수, 비어있는 칸 수, 행의 번호, 열의 번호
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
			if (o1[0] == o2[0]) {
				// 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
				if (o1[1] == o2[1]) {
					// 행의 번호가 가장 작은 칸으로
					if (o1[2] == o2[2]) {
						// 열의 번호가 가장 작은 칸으로 자리를 정한다.
						return o1[3] - o2[3];
					}

					return o1[2] - o2[2];
				}

				return o2[1] - o1[1];
			}

			// 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
			return o2[0] - o1[0];
		});

		for (int k = 0; k < N * N; k++) {
			Student student = students[k];
			pq.clear();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] != 0) {
						continue;
					}
					int aroundLoveCnt = 0;
					int emptyCnt = 0;

					for (int dir = 0; dir < 4; dir++) {
						int ny = i + dy[dir];
						int nx = j + dx[dir];
						if (out(ny, nx)) {
							continue;
						}
						if (arr[ny][nx] == 0) {
							emptyCnt++;
						} else {
							for (Integer num : student.loveNum) {
								if (num == arr[ny][nx]) {
									aroundLoveCnt++;
								}
							}
						}
					}
					pq.add(new int[] { aroundLoveCnt, emptyCnt, i, j });
				}
			}
			int[] poll = pq.poll();
			int aroundLoveCnt = poll[0];
			int emptyCnt = poll[1];
			int y = poll[2];
			int x = poll[3];

			arr[y][x] = student.num;
			student.y = y;
			student.x = x;
		}
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(arr[i]));
//		}

		int ret = 0;
		for (int i = 0; i < N * N; i++) {
			Student student = students[i];
			int y = student.y;
			int x = student.x;
			int cnt = 0;
			for (int dir = 0; dir < 4; dir++) {
				int ny = y + dy[dir];
				int nx = x + dx[dir];
				if (out(ny, nx)) {
					continue;
				}

				for (Integer num : student.loveNum) {
					if (arr[ny][nx] == num) {
						cnt++;
						break;
					}
				}
			}
			if (cnt == 1) {
				ret+= 1;
			}else if (cnt == 2) {
				ret+=10;
			}else if (cnt==3) {
				ret+=100;
			}else if (cnt == 4) {
				ret+=1000;
			}
		}
		
		System.out.println(ret);
	}

	static boolean out(int y, int x) {
		return y < 0 || y >= N || x < 0 || x >= N;
	}
}
