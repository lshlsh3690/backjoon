import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static long[] arr, tree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new long[N];
		tree = new long[4 * N];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		build(1, 0, N - 1);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int left = Integer.parseInt(st.nextToken()) - 1;
			int right = Integer.parseInt(st.nextToken()) - 1;
			int idx = Integer.parseInt(st.nextToken()) - 1;
			long value = Long.parseLong(st.nextToken());

			if (left > right) {
				int temp = left;
				left = right;
				right = temp;
			}

			// qeury
			System.out.println(query(1, 0, N - 1, left, right));

			// update
			update(1, 0, N - 1, idx, value);

		}
	}

	static void build(int node, int start, int end) {
		if (start == end) {
			tree[node] = arr[start];
			return;
		}

		int mid = (start + end) / 2;

		build(node * 2, start, mid);
		build(node * 2 + 1, mid + 1, end);

		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}

	static long query(int node, int start, int end, int left, int right) {
		if (left > end || right < start) {
			return 0;
		}

		if (left <= start && right >= end) {
			return tree[node];
		}

		int mid = (start + end) / 2;
		long leftSum = query(node * 2, start, mid, left, right);
		long rightSum = query(node * 2 + 1, mid + 1, end, left, right);

		return leftSum + rightSum;
	}

	static void update(int node, int start, int end, int idx, long value) {
		if (idx < start || idx > end) {
			return;
		}

		if (start == end) {
			arr[idx] = value;
			tree[node] = value;
			return;
		}

		int mid = (start + end) / 2;

		update(node * 2, start, mid, idx, value);
		update(node * 2 + 1, mid + 1, end, idx, value);

		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}
}
