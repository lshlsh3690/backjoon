import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;

	static long[] arr, maxTree, minTree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new long[N];
		maxTree = new long[4 * N];
		minTree = new long[4 * N];

		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}

		buildMax(1, 0, N - 1);
		buildMin(1, 0, N - 1);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int left = Integer.parseInt(st.nextToken()) - 1;
			int right = Integer.parseInt(st.nextToken()) - 1;

			long max = maxQuery(1, 0, N - 1, left, right);
			long min = minQuery(1, 0, N - 1, left, right);

			sb.append(min).append(" ").append(max).append("\n");
		}

		System.out.println(sb);

	}

	static long maxQuery(int nodeNum, int start, int end, int left, int right) {
		if (left > end || right < start) {
			return Long.MIN_VALUE;// 구간을 포함하지 않음
		}

		if (left <= start && right >= end) {
			// 구간을 포함하고 있음
			return maxTree[nodeNum];
		}

		int mid = (start + end) / 2;

		long leftMax = maxQuery(nodeNum * 2, start, mid, left, right);
		long rightMax = maxQuery(nodeNum * 2 + 1, mid + 1, end, left, right);

		return Math.max(leftMax, rightMax);
	}

	static long minQuery(int nodeNum, int start, int end, int left, int right) {
		if (left > end || right < start) {
			return Long.MAX_VALUE;// 구간을 포함하지 않음
		}

		if (left <= start && right >= end) {
			// 구간을 포함하고 있음
			return minTree[nodeNum];
		}

		int mid = (start + end) / 2;

		long leftMin = minQuery(nodeNum * 2, start, mid, left, right);
		long rightMin = minQuery(nodeNum * 2 + 1, mid + 1, end, left, right);

		return Math.min(leftMin, rightMin);
	}

	static void buildMax(int nodeNum, int start, int end) {
		if (start == end) {
			maxTree[nodeNum] = arr[start];
			return;
		}

		int mid = (start + end) / 2;

		buildMax(nodeNum * 2, start, mid);
		buildMax(nodeNum * 2 + 1, mid + 1, end);

		maxTree[nodeNum] = Math.max(maxTree[nodeNum * 2], maxTree[nodeNum * 2 + 1]);
	}

	static void buildMin(int nodeNum, int start, int end) {
		if (start == end) {
			minTree[nodeNum] = arr[start];
			return;
		}

		int mid = (start + end) / 2;

		buildMin(nodeNum * 2, start, mid);
		buildMin(nodeNum * 2 + 1, mid + 1, end);

		minTree[nodeNum] = Math.min(minTree[nodeNum * 2], minTree[nodeNum * 2 + 1]);
	}
}
