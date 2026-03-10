import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int T, V, E;
	static List<int[]> edges;
	static int[] parent;

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

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			edges = new ArrayList<int[]>();

			parent = new int[V];
			for (int i = 0; i < V; i++) {
				parent[i] = i;
			}
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int start = Integer.parseInt(st.nextToken()) - 1;
				int end = Integer.parseInt(st.nextToken()) - 1;
				int cost = Integer.parseInt(st.nextToken());

				edges.add(new int[] { start, end, cost });
			}

			Collections.sort(edges, (o1, o2) -> {
				return o1[2] - o2[2];
			});

			long ret = 0;
			int cnt = 0;

			for (int[] edge : edges) {
				int start = edge[0];
				int end = edge[1];
				int cost = edge[2];
				int ps = findParent(start);
				int pe = findParent(end);
				if (ps == pe) {
					continue;
				} else if (ps < pe) {
					parent[pe] = ps;
				} else if (pe < ps) {
					parent[ps] = pe;
				}

				cnt++;
				ret += cost;
				if (cnt == V - 1) {
					break;
				}
			}

			System.out.println("#" + t + " " + ret);
		}
	}
}
