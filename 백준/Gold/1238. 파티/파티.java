import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, X, ret;
	static int INF = 1000000000;
	static List<int[]>[] edges;
	static int[] distance;
	static int[] moves;
	static boolean[] visited;
	static PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
		return o1[1] - o2[1];
	});

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken()) - 1;

		edges = new ArrayList[N];
		moves = new int[N];
		for (int i = 0; i < N; i++) {
			edges[i] = new ArrayList<>();
			moves[i] = INF;
		}

		// M개의 줄에 도로의 시작점, 끝점, 소요 시간 T가 주어진다.
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			edges[start].add(new int[] { end, cost });
		}

		// 요원마다 X로 가는 최단 경로를 구하고
		// X에서 각 지역으로 가는 최단거리를 구해서 마지막에 더해주면 될듯

		for (int i = 0; i < N; i++) {
			int startNum = i;
			distance = new int[N];
			for (int k = 0; k < N; k++) {
				distance[k] = INF;
			}

			distance[startNum] = 0;
			pq.clear();
			pq.add(new int[] { startNum, 0 });
			visited = new boolean[N];
			while (!pq.isEmpty()) {
				int[] poll = pq.poll();
				int start = poll[0];
				int cost = poll[1];

				if (visited[start]) {
					continue;
				}
				visited[start] = true;

				for (int j = 0; j < edges[start].size(); j++) {
					int[] get = edges[start].get(j);
					int next = get[0];
					int nextCost = get[1];
					if (distance[next] > cost + nextCost) {
						distance[next] = cost + nextCost;
						pq.add(new int[] { next, distance[next] });
					}
				}
			}

			int move = distance[X];
			moves[i] = move;
		}

		// 이제 X에서 출발!
		distance = new int[N];
		for (int i = 0; i < N; i++) {
			distance[i] = INF;
		}

		visited = new boolean[N];
		pq.clear();
		pq.add(new int[] { X, 0 });
		distance[X] = 0;

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

		ret = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			// 가는길 moves 오는길 distance 둘중 하나 INF면 막음
			if (distance[i] == INF || moves[i] == INF) {
				continue;
			}

			moves[i] += distance[i];
			ret = Math.max(ret, moves[i]);
		}
		System.out.println(ret);
	}
}