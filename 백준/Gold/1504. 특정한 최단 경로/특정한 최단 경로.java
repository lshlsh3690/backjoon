import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int V, E, INF = 1000000000;
    static int D1, D2;
    static int[] distance;
    static boolean[] visited;
    static List<int[]>[] edges;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        distance = new int[V];
        visited = new boolean[V];
        edges = new ArrayList[V];

        for (int i = 0; i < V; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            edges[start].add(new int[] { end, cost });
            edges[end].add(new int[] { start, cost });
        }

        st = new StringTokenizer(br.readLine(), " ");
        D1 = Integer.parseInt(st.nextToken()) - 1;
        D2 = Integer.parseInt(st.nextToken()) - 1;

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

        // 1번 정점(0)에서 시작
        Arrays.fill(distance, INF);
        visited = new boolean[V];
        distance[0] = 0;
        pq.add(new int[] { 0, 0 });

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

        int[] dist0 = distance.clone();

        // D1에서 시작
        Arrays.fill(distance, INF);
        visited = new boolean[V];
        pq.clear();
        distance[D1] = 0;
        pq.add(new int[] { D1, 0 });

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

        int[] dist1 = distance.clone();

        // D2에서 시작
        Arrays.fill(distance, INF);
        visited = new boolean[V];
        pq.clear();
        distance[D2] = 0;
        pq.add(new int[] { D2, 0 });

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

        int[] dist2 = distance.clone();

        long path1 = (long) dist0[D1] + dist1[D2] + dist2[V - 1];
        long path2 = (long) dist0[D2] + dist2[D1] + dist1[V - 1];

        long answer = Math.min(path1, path2);

        if (dist0[D1] >= INF || dist1[D2] >= INF || dist2[V - 1] >= INF) {
            path1 = INF;
        }

        if (dist0[D2] >= INF || dist2[D1] >= INF || dist1[V - 1] >= INF) {
            path2 = INF;
        }

        answer = Math.min(path1, path2);

        System.out.println(answer >= INF ? -1 : answer);
    }
}