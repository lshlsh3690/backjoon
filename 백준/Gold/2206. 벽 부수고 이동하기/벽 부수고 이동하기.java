import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static int N, M;
    public static int[][] arr;
    public static int[][][] dp;
    public static boolean[][][] visited;
    public static int[] dy = {-1, 0, 1, 0};
    public static int[] dx = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        dp = new int[N][M][2];//벽 부술때 안 부술때
        visited = new boolean[N][M][2];//벽 부술때 안 부술때

        for (int i = 0; i < N; i++) {
            String[] sarr = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(sarr[j]);
                dp[i][j][0] = Integer.MAX_VALUE;
                dp[i][j][1] = Integer.MAX_VALUE;
            }
        }

        dp[0][0][0] = 1;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0, 0});

        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int y = poll[0];
            int x = poll[1];
            int wallCnt = poll[2];
            for (int k = 0; k < 4; k++) {
                int ny = y + dy[k];
                int nx = x + dx[k];
                if (ny < 0 || ny >= N || nx < 0 || nx >= M) {
                    continue;
                }

                if (arr[ny][nx] == 0){
                    if (visited[ny][nx][wallCnt] || dp[ny][nx][wallCnt] < dp[y][x][wallCnt] + 1)
                    {
                        continue;
                    }
                    q.add(new int[]{ny, nx, wallCnt});
                    visited[ny][nx][wallCnt] = true;
                    dp[ny][nx][wallCnt] = dp[y][x][wallCnt] + 1;

                }
                if (arr[ny][nx] == 1){
                    if (wallCnt + 1 >= 2 ||  visited[ny][nx][wallCnt+1])continue;
                    if (dp[ny][nx][wallCnt+1] < dp[y][x][wallCnt] + 1) continue;
                    q.add(new int[]{ny, nx, wallCnt + 1});
                    visited[ny][nx][wallCnt + 1] = true;
                    dp[ny][nx][wallCnt + 1] = dp[y][x][wallCnt] + 1;
                }
            }
        }


        int ret = Math.min(dp[N - 1][M - 1][0], dp[N - 1][M - 1][1]);
        System.out.println(ret == Integer.MAX_VALUE ? -1 : ret);

    }
}
