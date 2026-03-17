import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static long[] arr, tree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		arr = new long[N];
		tree = new long[4 * N];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		build(1, 0, N - 1);

		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			if (a == 1) {
				// 1 i j k: Ai, Ai+1, ..., Aj에 k를 더한다.
				int left = Integer.parseInt(st.nextToken()) - 1;
				int right = Integer.parseInt(st.nextToken()) - 1;
				int v = Integer.parseInt(st.nextToken());
				update(1, 0, N - 1, left, right, v);

			} else if (a == 2) {
				// 2 x: Ax 를 출력한다.
				int x = Integer.parseInt(st.nextToken()) - 1;
				System.out.println(query(1, 0, N - 1, x));
			}
		}

	}

	static void build(int nodeNum, int start, int end) {
		if (start == end) {
			tree[nodeNum] = arr[start];
			return;
		}

		int mid = (start + end) / 2;

		build(nodeNum * 2, start, mid);
		build(nodeNum * 2 + 1, mid + 1, end);

		// 트리에는 sum 총 계산이 들어가야되므로 중간 노드에는 아무것도 넣지 않음
	}

	static void update(int nodeNum, int start, int end, int left, int right, int value) {
		if (left > end || right < start) {
			return;
		}

		if (left <= start && right >= end) {
			tree[nodeNum] += value;
			return;
		}

		int mid = (start + end) / 2;

		update(nodeNum * 2, start, mid, left, right, value);
		update(nodeNum * 2 + 1, mid + 1, end, left, right, value);

		// 트리에는 sum 총 계산이 들어가야되므로 중간 노드에는 아무것도 넣지 않음

	}

	static long query(int nodeNum, int start, int end, int x) {
		int mid = (start + end) / 2;
		if (start == end) {
			return tree[nodeNum];
		} else if (x <= mid) {
			return tree[nodeNum] + query(nodeNum * 2, start, mid, x);
		} else if (x > mid) {
			return tree[nodeNum] + query(nodeNum * 2 + 1, mid + 1, end, x);
		}

		return 0;
	}
}
