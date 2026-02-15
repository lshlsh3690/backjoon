import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[]arr;
    static int[] parent;
    static List<int[]> edges;

    static int findParent(int x){
        if (x != parent[x]){
            parent[x] = findParent(parent[x]);
        }

        return parent[x];
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        arr = new int[N + 1];
        parent = new int[N + 1];
        edges = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges.add(new int[]{start, end, cost});
            start = findParent(start);
            end = findParent(end);

        }

        edges.sort((o1, o2) -> o1[2] - o2[2]);

        int result = 0;
        for (int[] e : edges) {
            int start = e[0];
            int end = e[1];
            int cost = e[2];
            start = findParent(start);
            end = findParent(end);
            if (start < end){
                parent[end] = start;
            }else if (end < start){
                parent[start] = end;
            }else if (findParent(start) == findParent(end))continue;

            result += cost;
        }

        System.out.println(result);
    }
}
