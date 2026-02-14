import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int H, W, K;
    static int[][] arr;
    static boolean[][][] visited;
    static int[][][] dp;
    //격자판의 맨 왼쪽 위에서 시작해서 맨 오른쪽 아래까지 가야한다.
    static int[][] move = {
            {-1, 0}, {0, 1}, {1, 0}, {0, -1},
    };
    static int[][] horseMove = {
            {-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        arr = new int[H][W];
        visited = new boolean[H][W][K + 1];
        dp = new int[H][W][K + 1];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < W; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                for (int k = 0; k <= K; k++) {
                    dp[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }

        dp[0][0][0] = 0;
        visited[0][0][0] = true;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0, 0});
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int y = poll[0];
            int x = poll[1];
            int k = poll[2];

            for (int i = 0; i < 4; i++) {
                int ny = y + move[i][0];
                int nx = x + move[i][1];
                if (ny < 0 || ny >= H || nx < 0 || nx >= W || arr[ny][nx] == 1 || visited[ny][nx][k])continue;
                if(dp[ny][nx][k] > dp[y][x][k] + 1) {
                    visited[ny][nx][k] = true;
                    q.add(new int[]{ny, nx, k});
                    dp[ny][nx][k] = Math.min(dp[ny][nx][k], dp[y][x][k] + 1);
                }
            }

            for(int i =0;i<8;i++){
                int ny = y + horseMove[i][0];
                int nx = x + horseMove[i][1];
                if (ny < 0 || ny >= H || nx < 0 || nx >= W || arr[ny][nx] == 1 || k+1 > K || visited[ny][nx][k+1])continue;
                if (dp[ny][nx][k+1] > dp[y][x][k] + 1){
                    visited[ny][nx][k + 1] = true;
                    q.add(new int[]{ny, nx, k + 1});
                    dp[ny][nx][k + 1] = Math.min(dp[ny][nx][k + 1], dp[y][x][k] + 1);
                }
            }
        }

        int ret= Integer.MAX_VALUE;
        for(int i = 0;i<K+1;i++) {
            ret = Math.min(ret, dp[H - 1][W - 1][i]);
        }
        System.out.println(ret == Integer.MAX_VALUE ? "-1" : ret);
    }
}
