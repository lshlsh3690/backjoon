import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int N, T;
	static double E;
	static int[] parent;
	static double[] X, Y;
	static List<double[]> edges;

	static int findParent(int x) {
		if (x != parent[x]) {
			parent[x] = findParent(parent[x]);
		}
		return parent[x];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			parent = new int[N];
			edges = new ArrayList<double[]>();
			for (int i = 0; i < N; i++) {
				parent[i] = i;
			}
			X = new double[N];
			Y = new double[N];

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				X[i] = Double.parseDouble(st.nextToken());
			}

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				Y[i] = Double.parseDouble(st.nextToken());
			}

			E = Double.parseDouble(br.readLine());

			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					double disX = X[i] - X[j];
					double disY = Y[i] - Y[j];
					disX = disX * disX;
					disY = disY * disY;

					double cost = disX + disY;

					edges.add(new double[] { i, j, cost });
				}
			}

			Collections.sort(edges, (o1, o2) -> {
				double cost = o1[2] - o2[2];
				if (cost == 0) {
					return 0;
				} else if (cost > 0) {
					return 1;
				} else {
					return -1;
				}
			});

			double sum = 0;
			for (double[] edge : edges) {
				int start = (int) edge[0];
				int end = (int) edge[1];
				double cost = edge[2];

				int ps = findParent(start);
				int pe = findParent(end);
				if (ps == pe) {
					continue;
				}

				if (ps < pe) {
					parent[pe] = ps;
				} else if (pe < ps) {
					parent[ps] = pe;
				}

				sum += cost;

			}

			long ret = (long) (Math.round(sum * E));
			System.out.println("#" + t + " " + ret);
		}
	}
}
