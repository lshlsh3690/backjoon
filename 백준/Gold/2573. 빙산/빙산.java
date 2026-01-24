import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static int dy[] = {-1, 0, 1, 0};
    public static int dx[] = {0, -1, 0, 1};
    public static int N, M;
    public static int[][] arr;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(1);
    }

    public static void dfs(int depth) {
        Queue<int[]> seaCnt = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int seaNum = 0;
                for (int k = 0; k < 4; k++) {
                    int ny = i + dy[k];
                    int nx = j + dx[k];
                    if (ny < 0 || ny >= N || nx < 0 || nx >= M) {
                        continue;
                    }
                    if (arr[i][j] > 0 && arr[ny][nx] == 0) {
                        seaNum++;
                    }
                }
                if (seaNum > 0) {
                    seaCnt.add(new int[]{i, j, seaNum});
                }
            }
        }

        while (!seaCnt.isEmpty()) {
            int[] poll = seaCnt.poll();
            int y = poll[0];
            int x = poll[1];
            arr[y][x] = Math.max(arr[y][x] - poll[2], 0);
        }

        boolean[][] visited = new boolean[N][M];
        int landCnt = 0;
        Queue<int[]> lands = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j]) continue;
                visited[i][j] = true;
                if (arr[i][j] > 0) {
                    landCnt++;
                    lands.add(new int[]{i, j});
                    while (!lands.isEmpty()) {
                        int[] poll = lands.poll();
                        int y = poll[0];
                        int x = poll[1];
                        for (int k = 0; k < 4; k++) {
                            int ny = y + dy[k];
                            int nx = x + dx[k];
                            if (ny < 0 || ny >= N || nx < 0 || nx >= M) {
                                continue;
                            }
                            if (visited[ny][nx]) {
                                continue;
                            }
                            if (arr[ny][nx] > 0) {
                                lands.add(new int[]{ny, nx});
                                visited[ny][nx] = true;
                            }
                        }
                    }
                }
                if (landCnt > 1) {
                    System.out.println(depth);
                    return;
                }
            }
        }
        if (landCnt == 0) {
            System.out.println(0);
            return;
        }
        dfs(depth + 1);
    }
}
