import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int V,E;
    static List<int[]>[] edges;
    static int INF = 1000000000;
    static int[] distance;
    static boolean[] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());

        distance = new int[V];
        visited = new boolean[V];
        edges = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            edges[i] = new ArrayList<>();
            distance[i] = INF;
        }

        for (int i = 0; i < E; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken())-1;
            int end = Integer.parseInt(st.nextToken())-1;
            int cost = Integer.parseInt(st.nextToken());

            edges[start].add(new int[]{end, cost});
        }

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int startNum = Integer.parseInt(st.nextToken()) - 1;
        int endNum = Integer.parseInt(st.nextToken()) - 1;

        distance[startNum] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{startNum, 0});
        while (!pq.isEmpty()) {
            int[] poll = pq.poll();
            int current = poll[0];
            int cost = poll[1];

            if (visited[current]) continue;
            visited[current] = true;

            for (int i = 0; i < edges[current].size(); i++) {
                int next = edges[current].get(i)[0];
                int nextCost = edges[current].get(i)[1];

                if (distance[next] > cost + nextCost) {
                    distance[next] = cost + nextCost;
                    pq.add(new int[]{next, distance[next]});
                }
            }
        }

        System.out.println(distance[endNum]);

    }
}
