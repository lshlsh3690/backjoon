import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.Key;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, ret = Integer.MAX_VALUE, cnt = 0;
    static int[][] arr;
    static int[][] visited;

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];
        visited = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boj14620_dfs();


        System.out.println(ret);
    }

    private static void boj14620_dfs() {
        if (cnt == 3) {
            int sum = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j] > 0) {
                        sum += arr[i][j];
                    }
                }
            }
            ret = Math.min(sum, ret);
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] == 0) {
                    boolean flag = true;
                    ArrayList<int[]> list = new ArrayList<>();
                    for (int k = 0; k < 4; k++) {
                        int ny = i + dy[k];
                        int nx = j + dx[k];
                        if (ny < 0 || nx < 0 || ny >= N || nx >= N) {
                            flag = false;
                            break;
                        }
                        if (visited[ny][nx]  > 0) {
                            flag = false;
                            break;
                        }
                        list.add(new int[]{ny, nx});
                    }

                    if (flag) {
                        for (int[] l : list) {
                            int y = l[0];
                            int x = l[1];
                            visited[y][x] = 1;
                        }
                        visited[i][j] = 1;
                        cnt++;

                        boj14620_dfs();
                        visited[i][j] = 0;
                        cnt--;
                        for (int[] l : list) {
                            int y = l[0];
                            int x = l[1];
                            visited[y][x] = 0;
                        }

                    }


                }
            }
        }
    }
}
