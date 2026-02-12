import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    private static int N,cnt, resulty, resultx, result;
    private static int[][] arr;
    private static int[] dy = {-1, 0, 1, 0};
    private static int[] dx = {0, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];
            result = -1;
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    cnt = 0;
                    dfs(i, j, 1);
                    if (result < cnt) {
                        resulty = i;
                        resultx = j;
                        result = cnt;
                    } else if (result == cnt) {
                        if (arr[resulty][resultx] < arr[i][j]) {
                            continue;
                        }else {
                            resulty = i;
                            resultx = j;
                            result = cnt;
                        }
                    }
                }
            }

            System.out.println("#"+t + " " + arr[resulty][resultx]+ " "+result);
        }
    }

    private static void dfs(int y, int x, int depth) {
        for (int k = 0; k < 4; k++) {
            int ny = y + dy[k];
            int nx = x + dx[k];
            if (ny < 0 || ny >= N || nx < 0 || nx >= N) {
                continue;
            }

            if (arr[y][x] + 1== arr[ny][nx]){
                dfs(ny,nx, depth+1);
            }
        }cnt = Math.max(cnt,depth);
    }
}
