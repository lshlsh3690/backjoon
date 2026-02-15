import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int V, E;
    static List<int[]> edge;
    static int[]parent;

    static int findParent(int x){
        if (x != parent[x]){
            parent[x] = findParent(parent[x]);
        }
        return parent[x];
    }

    public static void main(String[] args) throws  Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=  new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        parent = new int[V+1];
        edge = new ArrayList<>();
        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }

        int result= 0;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edge.add(new int[]{a, b, c,});
        }

        edge.sort((o1, o2) -> o1[2] - o2[2]);

        for (int[] e: edge){
            int start = e[0];
            int end = e[1];
            int cost = e[2];
            if (findParent(start) != findParent(end)){
                start = findParent(start);
                end = findParent(end);
                if (start < end){
                    parent[end] = start;
                }else{
                    parent[start] = end;
                }

                result += cost;
            }
        }

        System.out.println(result);


    }
}
