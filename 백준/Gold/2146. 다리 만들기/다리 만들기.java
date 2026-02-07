import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static int N, ret = Integer.MAX_VALUE;
    public static int[][] arr;
    public static boolean[][] visited;
    public static int[] dy = {-1, 0, 1, 0};
    public static int[] dx = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception{
        //0은 바다, 1은 육지를 나타낸다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<int[]> q = new LinkedList<>();
        Queue<int[]> nextQ = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                q.clear();
                nextQ.clear();
                visited = new boolean[N][N];
                if (arr[i][j] == 1) {
                    visited[i][j] = true;
                    q.add(new int[]{i, j});
                    while (!q.isEmpty()) {
                        int[] poll = q.poll();
                        int y = poll[0];
                        int x = poll[1];
                        for (int k = 0; k < 4; k++) {
                            int ny = y + dy[k];
                            int nx = x + dx[k];
                            if (ny < 0 || ny >= N || nx < 0 || nx >= N || visited[ny][nx]) {
                                continue;
                            }
                            if (arr[ny][nx] == 1) {
                                q.add(new int[]{ny, nx});
                                visited[ny][nx] = true;
                            } else if (arr[ny][nx] == 0) {
                                nextQ.add(new int[]{ny, nx});
                                visited[ny][nx] = true;
                            }
                        }
                    }
                    dfs(1, nextQ);
                }
            }
        }

        System.out.println(ret);

    }

    public static void dfs(int depth, Queue<int []>q) {
        Queue<int[]> nextQ = new LinkedList<>();
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int y = poll[0];
            int x = poll[1];
            for (int k = 0; k < 4; k++) {
                int ny = y + dy[k];
                int nx = x + dx[k];
                if (ny < 0 || ny >= N || nx < 0 || nx >= N || visited[ny][nx]) {
                    continue;
                }
                if (arr[ny][nx] == 1) {
                    ret = Math.min(ret, depth);
                } else if (arr[ny][nx] == 0) {
                    nextQ.add(new int[]{ny, nx});
                    visited[ny][nx] = true;
                }
            }
        }
        if (!nextQ.isEmpty()) {
            dfs(depth+1,nextQ);
        }
    }
}
