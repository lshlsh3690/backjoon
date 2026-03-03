import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] arr;
	static int[][] commands;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		M = Integer.parseInt(br.readLine());
		commands = new int[M][2];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			commands[i][0] = Integer.parseInt(st.nextToken());
			commands[i][1] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < M; i++) {
			int g = commands[i][0];
			int num = commands[i][1];
			if (g == 1) {
				int tmp = num;
				// 남학생은 스위치 번호가 자기가 받은 수의 배수이면, 그 스위치의 상태를 바꾼다.
				while (tmp <= N) {
					if (arr[tmp] == 0) {
						arr[tmp] = 1;
					} else if (arr[tmp] == 1) {
						arr[tmp] = 0;
					}
					tmp += num;
				}
			} else if (g == 2) {
				// 여학생은 자기가 받은 수와 같은 번호가 붙은 스위치를 중심으로 좌우가 대칭이면서 가장 많은 스위치를 포함하는 구간을 찾아서,
				// 그 구간에 속한 스위치의 상태를 모두 바꾼다.
				int left = num;
				int right = num;
				while ((left > 0 && right <= N) && arr[left] == arr[right]) {
					if (arr[left] == 1) {
						arr[left] = 0;
						arr[right] = 0;
					} else if (arr[left] == 0) {
						arr[left] = 1;
						arr[right] = 1;
					}
					left--;
					right++;
				}
			}

//			System.out.println(Arrays.toString(arr));
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(arr[i] + " ");
			if (i % 20 == 0) {
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}
}
