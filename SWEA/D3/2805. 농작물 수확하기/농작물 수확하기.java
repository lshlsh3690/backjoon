import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    private static int N,T,sum;
    private static int[][] arr;
    private static int[] dy = {-1, 0, 1, 0};
    private static int[] dx = {0, -1, 0, 1};
    private static boolean[][] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];
            visited = new boolean[N][N];
            sum =0;
            for (int i = 0; i < N; i++) {
                String[] sarr = br.readLine().split("");
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(sarr[j]);
                }
            }

            Queue<int[]> q = new LinkedList<>();
            sum = arr[N / 2][N / 2];
            q.add(new int[]{N / 2, N / 2});
            ww: while (!q.isEmpty()) {
                int[] pos = q.poll();
                int y = pos[0];
                int x = pos[1];

//                sum += arr[y][x];
                visited[y][x] = true;
                for (int i = 0; i < 4; i++) {
                    int ny = y + dy[i];
                    int nx = x + dx[i];
                    if (ny < 0 || ny>=N || nx < 0 || nx >= N ){
                        System.out.println("#"+t+ " " +sum);
                        break ww;
                    }
                    if (visited[ny][nx]) {
                        continue;
                    }
                    sum += arr[ny][nx];
                    visited[ny][nx] = true;
                    q.add(new int[]{ny, nx});
                }
            }
        }
    }

}
