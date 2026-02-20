import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int V, E;

    static List<Integer>[] arr;
    static int[] degree;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        arr = new ArrayList[V + 1];
        degree = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            arr[start].add(end);
            degree[end]++;
        }

        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int i = 1; i <= V; i++) {
            if (degree[i] == 0) {
                q.offer(i);
            }
        }

        Queue<Integer> result = new LinkedList<>();
        while (!q.isEmpty()) {
            int now = q.poll();
            result.add(now);

            for (int i = 0; i < arr[now].size(); i++) {
                degree[arr[now].get(i)]--;
                if (degree[arr[now].get(i)] == 0) {
                    q.add(arr[now].get(i));
                }
            }
        }


        for (Integer i : result) {
            System.out.print(i + " ");
        }
    }
}
