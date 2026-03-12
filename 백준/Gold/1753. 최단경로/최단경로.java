import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int V, E, startNum;
	static int INF = 1000000000;
	static int[] distance;
	static boolean[] visited;
	static List<int[]>[] edges;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		startNum = Integer.parseInt(br.readLine()) - 1;

		distance = new int[V];
		visited = new boolean[V];
		edges = new ArrayList[V];
		for (int i = 0; i < V; i++) {
			distance[i] = INF;
			edges[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());

			edges[start].add(new int[] { end, cost });
			// 방향 그래프라서 반대편은 등록 안함
			// edges[end].add(new int[] { start, cost });
		}

		distance[startNum] = 0;
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
			return o1[1] - o2[1];
		});
		pq.add(new int[] { startNum, 0 });

		while (!pq.isEmpty()) {
			int[] poll = pq.poll();
			int start = poll[0];
			int cost = poll[1];

			if (visited[start]) {
				continue;
			}
			visited[start] = true;

			for (int i = 0; i < edges[start].size(); i++) {
				int[] get = edges[start].get(i);
				int next = get[0];
				int nextCost = get[1];

				if (distance[next] > cost + nextCost) {
					distance[next] = cost + nextCost;
					pq.add(new int[] { next, distance[next] });
				}
			}
		}

		// System.out.println(Arrays.toString(distance));
		for (int i = 0; i < V; i++) {
			System.out.println(distance[i] == INF ? "INF" : distance[i]);
		}
	}
}
