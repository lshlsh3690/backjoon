import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int V, E;
    static int[] parent;
    static List<int[]> edges;

    static int findParent(int x) {
        if (x != parent[x]) {
            parent[x] = findParent(parent[x]);
        }
        return parent[x];
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            if (V == 0 && E == 0){
                break;
            }
            parent = new int[V];
            for (int i = 0; i < V; i++) {
                parent[i] = i;
            }
            edges = new ArrayList<>();

            int totalCost = 0;

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                edges.add(new int[]{start, end, cost});
                totalCost += cost;
            }

            edges.sort((o1, o2) -> {
                return o1[2] - o2[2];
            });

            int minCost = 0;
            int pick = 0;
            for (int[] edge : edges) {
                int start = edge[0];
                int end = edge[1];
                int cost = edge[2];

                int ps = findParent(start);
                int pe = findParent(end);
                if (ps == pe) continue;
                else if (ps < pe) parent[pe] = ps;
                else if (pe < ps) parent[ps] = pe;
                pick++;
                minCost += cost;
                if (pick == V - 1) {
                    break;
                }
            }

            System.out.println(totalCost - minCost);
//        System.out.println(totalCost);
//        System.out.println(minCost);
        }
    }
}
