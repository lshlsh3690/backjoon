import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
빈 칸: 언제나 이동할 수 있다. ('.')
벽: 절대 이동할 수 없다. ('#')
열쇠: 언제나 이동할 수 있다. 이 곳에 처음 들어가면 열쇠를 집는다. ('a', 'b', 'c', 'd', 'e', 'f')
문: 대응하는 열쇠가 있을 때만 이동할 수 있다. ('A', 'B', 'C', 'D', 'E', 'F')
민식이의 현재 위치: 빈 곳이고, 민식이가 현재 서 있는 곳이다. ('0')
출구: 달이 차오르기 때문에, 민식이가 가야하는 곳이다. 이 곳에 오면 미로를 탈출한다. ('1')
*/
//'a', 'b', 'c', 'd', 'e', 'f'
public class Main {
    static int N, M, y, x;
    static char[][] arr;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int[][][] dp;
    static boolean[][][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new char[N][M];
        dp = new int[N][M][64];
        for(int i = 0;i<N;i++){
            for(int j = 0;j<M;j++){
                for(int k = 0;k<64;k++){
                    dp[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
        visited = new boolean[N][M][64];
        Queue<int[]> q = new LinkedList<>();


        for (int i = 0; i < N; i++) {
            String[] sarr = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                arr[i][j] = sarr[j].charAt(0);
                if (arr[i][j] == '0') {
                    y = i;
                    x = j;
                    arr[i][j] = '.';
                    q.add(new int[]{y, x, 0});
                    dp[y][x][0] = 0;
                }
            }
        }

        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int y = poll[0];
            int x = poll[1];
            int hasKey = poll[2];

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
                if (arr[ny][nx] == '#') continue;

                if (arr[ny][nx] == 'a') {
                    int nextKey = hasKey | (1 << 0);
                    if (visited[ny][nx][nextKey]) continue;
                    visited[ny][nx][nextKey] = true;

                    dp[ny][nx][nextKey] = Math.min(dp[ny][nx][nextKey], dp[y][x][hasKey] + 1);
                    q.add(new int[]{ny, nx, nextKey});
                } else if (arr[ny][nx] == 'b') {
                    int nextKey = hasKey | (1 << 1);
                    if (visited[ny][nx][nextKey]) continue;
                    visited[ny][nx][nextKey] = true;

                    dp[ny][nx][nextKey] = Math.min(dp[ny][nx][nextKey], dp[y][x][hasKey] + 1);
                    q.add(new int[]{ny, nx, nextKey});
                } else if (arr[ny][nx] == 'c') {
                    int nextKey = hasKey | (1 << 2);
                    if (visited[ny][nx][nextKey]) continue;
                    visited[ny][nx][nextKey] = true;

                    dp[ny][nx][nextKey] = Math.min(dp[ny][nx][nextKey], dp[y][x][hasKey] + 1);
                    q.add(new int[]{ny, nx, nextKey});
                } else if (arr[ny][nx] == 'd') {
                    int nextKey = hasKey | (1 << 3);
                    if (visited[ny][nx][nextKey]) continue;
                    visited[ny][nx][nextKey] = true;

                    dp[ny][nx][nextKey] = Math.min(dp[ny][nx][nextKey], dp[y][x][hasKey] + 1);
                    q.add(new int[]{ny, nx, nextKey});
                } else if (arr[ny][nx] == 'e') {
                    int nextKey = hasKey | (1 << 4);
                    if (visited[ny][nx][nextKey]) continue;
                    visited[ny][nx][nextKey] = true;

                    dp[ny][nx][nextKey] = Math.min(dp[ny][nx][nextKey], dp[y][x][hasKey] + 1);
                    q.add(new int[]{ny, nx, nextKey});
                } else if (arr[ny][nx] == 'f') {
                    int nextKey = hasKey | (1 << 5);
                    if (visited[ny][nx][nextKey]) continue;
                    visited[ny][nx][nextKey] = true;

                    dp[ny][nx][nextKey] = Math.min(dp[ny][nx][nextKey], dp[y][x][hasKey] + 1);
                    q.add(new int[]{ny, nx, nextKey});
                }else if (arr[ny][nx] == 'A' && (hasKey & (1 << 0)) != 0) {
                    if (visited[ny][nx][hasKey]) continue;
                    visited[ny][nx][hasKey] = true;

                    q.add(new int[]{ny, nx,hasKey});
                    dp[ny][nx][hasKey] = Math.min(dp[ny][nx][hasKey], dp[y][x][hasKey] + 1);

                } else if (arr[ny][nx] == 'B' && (hasKey & 1 << 1) != 0) {
                    if (visited[ny][nx][hasKey]) continue;
                    visited[ny][nx][hasKey] = true;
                    q.add(new int[]{ny, nx,hasKey});
                    dp[ny][nx][hasKey] = Math.min(dp[ny][nx][hasKey], dp[y][x][hasKey] + 1);
                } else if (arr[ny][nx] == 'C' && (hasKey & 1 << 2) != 0) {
                    if (visited[ny][nx][hasKey]) continue;
                    visited[ny][nx][hasKey] = true;

                    q.add(new int[]{ny, nx,hasKey});
                    dp[ny][nx][hasKey] = Math.min(dp[ny][nx][hasKey], dp[y][x][hasKey] + 1);
                } else if (arr[ny][nx] == 'D' && (hasKey & 1 << 3) != 0) {
                    if (visited[ny][nx][hasKey]) continue;
                    visited[ny][nx][hasKey] = true;
                    q.add(new int[]{ny, nx,hasKey});
                    dp[ny][nx][hasKey] = Math.min(dp[ny][nx][hasKey], dp[y][x][hasKey] + 1);
                } else if (arr[ny][nx] == 'E' && (hasKey & 1 << 4) != 0) {
                    if (visited[ny][nx][hasKey]) continue;
                    visited[ny][nx][hasKey] = true;
                    q.add(new int[]{ny, nx,hasKey});
                    dp[ny][nx][hasKey] = Math.min(dp[ny][nx][hasKey], dp[y][x][hasKey] + 1);
                } else if (arr[ny][nx] == 'F' && (hasKey & 1 << 5) != 0) {
                    if (visited[ny][nx][hasKey]) continue;
                    visited[ny][nx][hasKey] = true;
                    q.add(new int[]{ny, nx,hasKey});
                    dp[ny][nx][hasKey] = Math.min(dp[ny][nx][hasKey], dp[y][x][hasKey] + 1);
                } else if (arr[ny][nx] == '.') {
                    if (visited[ny][nx][hasKey]) continue;
                    visited[ny][nx][hasKey] = true;
                    q.add(new int[]{ny, nx,hasKey});
                    dp[ny][nx][hasKey] = Math.min(dp[ny][nx][hasKey], dp[y][x][hasKey] + 1);
                }else if(arr[ny][nx] == '1'){
                    dp[ny][nx][hasKey] = Math.min(dp[ny][nx][hasKey], dp[y][x][hasKey] + 1);
                }
            }
        }

        int ret = Integer.MAX_VALUE;

        for(int i =0;i<N;i++){
            for(int j = 0;j<M;j++){
                if (arr[i][j] == '1'){
                    for(int k =0;k<64;k++){
                        ret = Math.min(ret, dp[i][j][k]);
                    }
                }
            }
        }
        System.out.println(ret == Integer.MAX_VALUE ? "-1" : ret);

    }
}
