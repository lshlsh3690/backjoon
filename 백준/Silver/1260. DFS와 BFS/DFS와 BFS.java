import java.util.*;

public class Main {
    private static int N, M, V;
    private static boolean[] visited;
    private static int [][]arr;
    private static Queue<Integer> q;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        V = sc.nextInt();

        arr = new int[N][N];
        visited = new boolean[N];
        q = new LinkedList<>();

        for (int i = 0; i < M; i++) {
            int y = sc.nextInt()-1;
            int x = sc.nextInt()-1;
            arr[y][x] = 1;
            arr[x][y] = 1;
        }

        visited[V - 1] = true;
        dfs(V - 1);

        System.out.println();

        visited = new boolean[N];
        visited[V - 1] = true;
        q.add(V - 1);
        bfs(V - 1);

        while (!q.isEmpty()) {
            Integer v = q.poll();
            System.out.printf(v+1 + " ");
            bfs(v);
        }

    }

    private static void dfs(int v) {
        System.out.print(v+1 + " ");
        for (int i = 0; i < N; i++) {
            if (arr[v][i] == 1 && !visited[i]) {
                visited[i] = true;
                dfs(i);
            }
        }
    }

    private static void bfs(int v) {
        for (int i = 0; i < N; i++) {
            if (arr[v][i] == 1 && !visited[i]) {
                visited[i] = true;
                q.add(i);
            }
        }
    }
}
