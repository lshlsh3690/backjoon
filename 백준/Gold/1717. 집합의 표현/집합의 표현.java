import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] parent;

	static int findParent(int x) {
		if (x != parent[x]) {
			parent[x] = findParent(parent[x]);
		}
		return parent[x];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		parent = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			parent[i] = i;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int com = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			int pa = findParent(a);
			int pb = findParent(b);
			if (com == 0) {
				if (pa == pb) {
					continue;
				}
				if (pa < pb) {
					parent[pb] = pa;
				} else {
					parent[pa] = pb;
				}
			} else if (com == 1) {
				if (pa == pb) {
					System.out.println("YES");
				} else {
					System.out.println("NO");
				}
			}
		}
	}
}
