import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int R,C, K, ret=0, cnt= 0;
    static boolean[][] visited;
    static int [] result;
    static int [][]arr;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[R][C];
        visited = new boolean[R][C];
        result = new int[R * C+1];

        for (int i = 0; i < R; i++) {
            String[] s = br.readLine().split("");
            for (int j = 0; j < C; j++) {
                if (s[j].equals("T")) {
                    arr[i][j] = -1;
                }else{
                    arr[i][j] = 0;
                }
            }
        }

        boj1189_dfs(R-1,0);


        System.out.println(ret);
    }

    private static void boj1189_dfs(int y,int x) {
        if (cnt > K) {
            return;
        }
        if (cnt == K-1 && y == 0 && x == C-1) {
            ret++;
        }

        Queue<int[]> q = new LinkedList<>();
        visited[y][x] = true;
        q.add(new int[]{y, x});

        while (!q.isEmpty()) {
            int[] p = q.poll();
            int cy = p[0];
            int cx = p[1];
            for (int k = 0; k < 4; k++) {
                int ny = cy + dy[k];
                int nx = cx + dx[k];
                if (ny < 0 || nx < 0 || ny >= R || nx >= C) {
                    continue;
                }
                if (visited[ny][nx]) {
                    continue;
                }
                if (arr[ny][nx] == -1) {
                    continue;
                }

                visited[ny][nx] = true;
                cnt++;
                arr[ny][nx] = arr[y][x] + 1;
                boj1189_dfs(ny,nx);
                visited[ny][nx] = false;
                cnt--;
                arr[ny][nx] = arr[y][x] - 1;
            }

        }

        visited[y][x] = false;

    }
}
