

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static int N, M, K, result = Integer.MAX_VALUE;
    public static boolean[] starts;

    static int[] bfs(int start, int N, List<Integer>[] adj) {
        int[] dist = new int[N];
        Arrays.fill(dist, -1);
        Queue<Integer> q = new LinkedList<Integer>();

        dist[start] = 0;
        q.add(start);

        while (!q.isEmpty()) {
            int poll = q.poll();
            for (int next : adj[poll]) {
                if (dist[next] == -1) {
                    dist[next] = dist[poll] + 1;
                    q.add(next);
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        List<Integer>[] adj = new ArrayList[N];

        
        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;

            adj[start].add(end);
        }

        st = new StringTokenizer(br.readLine(), " ");
        int[] people = new int[K];
        for (int i = 0; i < K; i++) {
            people[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        int[][] dist = new int[K][N];
        for (int i = 0; i < K; i++) {
            dist[i] = bfs(people[i], N, adj);
        }

        int answer = Integer.MAX_VALUE;
        for (int v = 0; v < N; v++) {
            int maxTime = 0;
            boolean reachable = true;
            for (int i = 0; i < K; i++) {
                if (dist[i][v] == -1) {
                    reachable = false;
                    break;
                }
                maxTime = Math.max(maxTime, dist[i][v]);
            }
            if (reachable) {
                answer = Math.min(answer, maxTime);
            }
        }
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }
}
