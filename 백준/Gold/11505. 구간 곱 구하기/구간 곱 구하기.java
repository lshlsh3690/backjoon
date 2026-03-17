import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static long[] arr, tree;
	static long div = 1000000007;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		arr = new long[N];
		tree = new long[4 * N];

		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}

		build(1, 0, N - 1);

		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken()) - 1;
			long c = Long.parseLong(st.nextToken());
			if (a == 1) {
				// a가 1인 경우 b번째 수를 c로 바꾸고
				update(1, 0, N - 1, b, c);
			} else if (a == 2) {
				// a가 2인 경우에는 b부터 c까지의 곱을 구하여 출력
				long ret = query(1, 0, N - 1, b, (int) c - 1);
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

		tree[nodeNum] = (tree[nodeNum * 2] * tree[nodeNum * 2 + 1]) % div;
	}

	static void update(int nodeNum, int start, int end, int idx, long value) {
		if (idx < start || idx > end) {
			return; // 안 겹침
		}

		if (start == end) {
			arr[idx] = value;
			tree[nodeNum] = value;
			return;
		}

		int mid = (start + end) / 2;

		update(nodeNum * 2, start, mid, idx, value);
		update(nodeNum * 2 + 1, mid + 1, end, idx, value);

		tree[nodeNum] = (tree[nodeNum * 2] * tree[nodeNum * 2 + 1]) % div;
	}

	static long query(int nodeNum, int start, int end, int left, int right) {
		if (left > end || right < start) {
			return 1;
		}

		if (left <= start && right >= end) {
			return tree[nodeNum];
		}

		int mid = (start + end) / 2;

		long leftMul = query(nodeNum * 2, start, mid, left, right);
		long rightMul = query(nodeNum * 2 + 1, mid + 1, end, left, right);

		return (leftMul * rightMul) % div;
	}
}
