import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static int N, M, cnt;
    public static int[][] arr;
    public static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        visited = new boolean[N];
        cnt = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            arr[u - 1][v - 1] = 1;
            arr[v - 1][u - 1] = 1;
        }

        if (M == 0) {
            cnt = N;
        } else {
            for (int i = 0; i < N; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    cnt++;
                    dfs(i);
                }
            }
        }

        System.out.println(cnt);
    }

    public static void dfs(int y) {
        Queue<Integer> q = new LinkedList<>();
//        System.out.println(y);
        for (int j = 0; j < N; j++) {
            if (arr[y][j] == 1 && !visited[j]) {
                visited[j] = true;
                q.add(j);
            }
        }

        while (!q.isEmpty()) {
            dfs(q.poll());
        }
    }
}
