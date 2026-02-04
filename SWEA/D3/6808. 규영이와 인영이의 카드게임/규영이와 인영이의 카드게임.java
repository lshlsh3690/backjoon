import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	public static int T, myWin = 0, myLose = 0, sum, oppsiteSum;
	public static int[] arr, arr2;
	public static boolean[] visited, visited2, isMine;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			arr = new int[9]; // 뽑을 수 있는 카드
			arr2 = new int[9];
			isMine = new boolean[18];// 내꺼면
			visited = new boolean[9];// 나
			visited2 = new boolean[9];// 상대
			myWin = 0;
			myLose = 0;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 9; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				isMine[arr[i] - 1] = true;
			}

			int idx = 0;
			for (int i = 0; i < 18; i++) {
				if (!isMine[i]) {
					arr2[idx++] = i + 1;// 내거가아니면 상대거
				}
			}

			dfs(0);

			System.out.println("#" + t + " " + myWin + " " + myLose);
		}
	}

	public static void dfs(int depth) { // 2번

		if (depth >= 9) {
			if (sum > oppsiteSum) {
				myWin++;
			} else if (sum < oppsiteSum) {
				myLose++;
			}
			return;
		}

		int myScore = arr[depth];
		int oppsiteScore = 0;
		int cnt = 0;

		for (int j = 0; j < 9; j++) {
			if (visited2[j])
				continue;
			oppsiteScore = arr2[j];
			visited2[j] = true;
			if (myScore > oppsiteScore) {
				sum += myScore + oppsiteScore;
			} else if (myScore < oppsiteScore) {
				oppsiteSum += myScore + oppsiteScore;
			}

			dfs(depth + 1);
			if (myScore > oppsiteScore) {
				sum -= myScore + oppsiteScore;
			} else if (myScore < oppsiteScore) {
				oppsiteSum -= myScore + oppsiteScore;
			}
			visited2[j] = false;
		}

	}
}
