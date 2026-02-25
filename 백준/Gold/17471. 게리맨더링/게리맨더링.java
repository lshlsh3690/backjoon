import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, ret;
	static int[] people;
	static int[][] arr;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		people = new int[N];
		visited = new boolean[N];
		arr = new int[N][N];
		ret = Integer.MAX_VALUE;
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int k = Integer.parseInt(st.nextToken());
			for (int a = 0; a < k; a++) {
				int idx = Integer.parseInt(st.nextToken()) - 1;
				arr[i][idx] = 1;
			}
		}

		// visited true false로 완탐 하기 그런데 0, N-1은 제외
		for (int i = 0; i < N; i++) {
			visited[i] = true;
			dfs(i, 1);
			visited[i] = false;
		}

		System.out.println(ret == Integer.MAX_VALUE ? -1 : ret);
	}

	static void dfs(int idx, int depth) {
		// true인것과 false인 것끼리 인접해있는지 검사 후 최소값 갱신
		if (check()) {
			int tSum = 0; // true인 사람의 인구
			int fSum = 0; // false인 사람의 인구
			for (int i = 0; i < N; i++) {
				if (visited[i]) {
					tSum += people[i];
				} else {
					fSum += people[i];
				}
			}

			ret = Math.min(ret, Math.abs(tSum - fSum));

		}

		for (int i = idx; i < N; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			dfs(i, depth + 1);
			visited[i] = false;
		}
	}

	static boolean check() {
		int tIdx = -1;// true인것 idx
		for (int i = 0; i < N; i++) {
			if (visited[i]) {
				tIdx = i;
				break;
			}
		}
		if (tIdx == -1) {
			return false;
		}

		int fIdx = -1;// false인것 idx
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				fIdx = i;
				break;
			}
		}
		if (fIdx == -1) {
			return false;
		}

		boolean[] visited2 = new boolean[N];
		int tCnt = 0;// true인 것중에 인접한 노드의 개수
		int fCnt = 0;// false인 것중에 인접한 노드의 개수
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(tIdx);
		visited2[tIdx] = true;
		tCnt++;
		while (!q.isEmpty()) {
			tIdx = q.poll();
			for (int i = 0; i < N; i++) {
				if (arr[tIdx][i] == 1 && visited[i] && !visited2[i]) {
					visited2[i] = true;
					tCnt++;
					q.add(i);
				}
			}

		}
		visited2 = new boolean[N];
		q.add(fIdx);
		visited2[fIdx] = true;
		fCnt++;
		while (!q.isEmpty()) {
			fIdx = q.poll();
			for (int i = 0; i < N; i++) {
				if (arr[fIdx][i] == 1 && !visited[i] && !visited2[i]) {
					visited2[i] = true;
					fCnt++;
					q.add(i);
				}
			}
		}

		if (tCnt + fCnt == N) {
			return true;
		}

		return false;
	}
}
