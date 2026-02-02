import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static int N, problem;
    public static int[] dy = {-1, 0, 1, 0};
    public static int[] dx = {0, 1, 0, -1};
    public static int[][]arr,dp;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        problem = 1;
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) {
                return;
            }
            arr = new int[N][N];
            dp = new int[N][N];
            for (int i = 0; i < N; i++) {
                Arrays.fill(dp[i], Integer.MAX_VALUE);
            }
            StringTokenizer st;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int y = 0;
            int x = 0;
            dp[0][0] = arr[0][0];

            Queue<int[]> q = new LinkedList<>();
            q.add(new int[]{y, x});
            while (!q.isEmpty()) {
                int[] poll = q.poll();
                y = poll[0];
                x = poll[1];
                for (int i = 0; i < 4; i++) {
                    int ny = y + dy[i];
                    int nx = x + dx[i];
                    if (ny < 0 || ny >= N || nx < 0 || nx >= N) {
                        continue;
                    }
                    if (dp[y][x] + arr[ny][nx] < dp[ny][nx]) {
                        dp[ny][nx] = dp[y][x] + arr[ny][nx];
                        q.add(new int[]{ny, nx});
                    }
                }
            }

            System.out.println("Problem " + problem+": "+ dp[N-1][N-1]);
            problem++;
        }
    }
}
