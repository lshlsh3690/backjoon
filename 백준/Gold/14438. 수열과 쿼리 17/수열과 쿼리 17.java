import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] arr, tree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		arr = new int[N];
		tree = new int[4 * N];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		build(1, 0, N - 1);

		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken());

			if (a == 1) {
				// 1 i v : Ai를 v로 바꾼다. (1 ≤ i ≤ N, 1 ≤ v ≤ 109)
				update(1, 0, N - 1, b, c);
			} else if (a == 2) {
				// 2 i j : Ai, Ai+1, ..., Aj에서 크기가 가장 작은 값을 출력한다. (1 ≤ i ≤ j ≤ N)
				int ret = query(1, 0, N - 1, b, c - 1);
				System.out.println(ret);
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

		tree[nodeNum] = Math.min(tree[nodeNum * 2], tree[nodeNum * 2 + 1]);
	}

	static void update(int nodeNum, int start, int end, int idx, int value) {
		if (idx < start || idx > end) {
			return;
		}

		if (start == end) {
			arr[idx] = value;
			tree[nodeNum] = value;
			return;
		}

		int mid = (start + end) / 2;

		update(nodeNum * 2, start, mid, idx, value);
		update(nodeNum * 2 + 1, mid + 1, end, idx, value);

		tree[nodeNum] = Math.min(tree[nodeNum * 2], tree[nodeNum * 2 + 1]);
	}

	static int query(int nodeNum, int start, int end, int left, int right) {
		if (left > end || right < start) {
			return Integer.MAX_VALUE;
		}

		if (left <= start && right >= end) {
			return tree[nodeNum];
		}

		int mid = (start + end) / 2;

		int leftMin = query(nodeNum * 2, start, mid, left, right);
		int rightMin = query(nodeNum * 2 + 1, mid + 1, end, left, right);

		return Math.min(leftMin, rightMin);
	}
}
