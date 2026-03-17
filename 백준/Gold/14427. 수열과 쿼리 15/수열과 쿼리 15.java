import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] arr;
	static int[][] tree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		arr = new int[N];
		tree = new int[4 * N][2];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// 트리에는 어떤 값을 저장해야될까
		// 아마 크기가 가장 작은 인덱스, 크기가 같으면 인덱스가 작은것을 출력해야될듯
		build(1, 0, N - 1);

		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			if (a == 1) {
				// 1 i v : Ai를 v로 바꾼다. (1 ≤ i ≤ N, 1 ≤ v ≤ 109)
				int b = Integer.parseInt(st.nextToken()) - 1;
				int c = Integer.parseInt(st.nextToken());
				update(1, 0, N - 1, b, c);
			} else if (a == 2) {
				// 2 : 수열에서 크기가 가장 작은 값의 인덱스를 출력한다.
				// 그러한 값이 여러개인 경우에는 인덱스가 작은 것을 출력한다.
				System.out.println(tree[1][1] + 1);
			}
		}
	}

	static void build(int nodeNum, int start, int end) {
		if (start == end) {
			tree[nodeNum][0] = arr[start];
			tree[nodeNum][1] = start;
			return;
		}

		int mid = (start + end) / 2;

		build(nodeNum * 2, start, mid);
		build(nodeNum * 2 + 1, mid + 1, end);

		int leftValue = tree[nodeNum * 2][0];
		int leftIdx = tree[nodeNum * 2][1];
		int rightValue = tree[nodeNum * 2 + 1][0];
		int rightIdx = tree[nodeNum * 2 + 1][1];

		if (leftValue <= rightValue) {
			tree[nodeNum][0] = leftValue;
			tree[nodeNum][1] = leftIdx;
		} else {
			tree[nodeNum][0] = rightValue;
			tree[nodeNum][1] = rightIdx;
		}

	}

	static void update(int nodeNum, int start, int end, int idx, int value) {
		if (idx < start || idx > end) {
			return; // 안 겹침
		}

		if (start == end) {
			arr[idx] = value;
			tree[nodeNum][0] = value;
			tree[nodeNum][1] = idx;
			return;
		}

		int mid = (start + end) / 2;

		update(nodeNum * 2, start, mid, idx, value);
		update(nodeNum * 2 + 1, mid + 1, end, idx, value);

		int leftValue = tree[nodeNum * 2][0];
		int leftIdx = tree[nodeNum * 2][1];
		int rightValue = tree[nodeNum * 2 + 1][0];
		int rightIdx = tree[nodeNum * 2 + 1][1];

		if (leftValue <= rightValue) {
			tree[nodeNum][0] = leftValue;
			tree[nodeNum][1] = leftIdx;
		} else {
			tree[nodeNum][0] = rightValue;
			tree[nodeNum][1] = rightIdx;
		}
	}
}
