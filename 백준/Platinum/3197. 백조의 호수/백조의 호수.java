import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int R, C, result = 0;
    public static int[] dy = {-1, 0, 1, 0};
    public static int[] dx = {0, 1, 0, -1};
    public static char[][] arr;

    public static boolean[][] isWater;
    public static boolean[][] visited;


    public static void main(String[] args) throws Exception {
        //'.'은 물 공간, 'X'는 빙판 공간, 'L'은 백조가 있는 공간으로 나타낸다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[R][C];
        isWater = new boolean[R][C];
        visited  = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            arr[i] = br.readLine().toCharArray();
        }
        Queue<int[]> iceQ = new LinkedList<>();
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arr[i][j] == 'X') {
                    for (int k = 0; k < 4; k++) {
                        int ny = i + dy[k];
                        int nx = j + dx[k];
                        if (ny < 0 || ny >= R || nx < 0 || nx >= C) {
                            continue;
                        }
                        if (arr[ny][nx] == '.' || arr[ny][nx] =='L') {
                            iceQ.add(new int[]{i, j});
                            isWater[i][j] = true;
                        }
                    }
                }
            }
        }

        loop1:
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arr[i][j] == 'L') {
                    q.add(new int[]{i, j});
                    visited[i][j] = true;
                    break loop1;
                }
            }
        }

        dfs(0, q, iceQ);
        System.out.println(result);
    }

    public static void dfs(int depth, Queue<int[]>q, Queue<int[]>iceQ) {
        Queue<int[]> nextIceQ = new LinkedList<>();
        Queue<int[]> nextSwanQ = new LinkedList<>();

        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int y = poll[0];
            int x = poll[1];
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (ny < 0 || ny >= R || nx < 0 || nx >= C || visited[ny][nx]) {
                    continue;
                }
                if (arr[ny][nx] == 'X' && !visited[ny][nx]) {
                    nextSwanQ.add(new int[]{ny, nx});
                    visited[ny][nx] = true;
                    continue;
                }

                if (arr[ny][nx] == 'L') {
                    result = depth;
                    return;
                }

                q.add(new int[]{ny, nx});
                visited[ny][nx] = true;
            }
        }

        //빙판 녹이기~
        while (!iceQ.isEmpty()) {
            int[] poll = iceQ.poll();
            int y = poll[0];
            int x = poll[1];
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (ny < 0 || ny >= R || nx < 0 || nx >= C) {
                    continue;
                }
                if (arr[ny][nx] == 'X' && !isWater[ny][nx]) {
                    nextIceQ.add(new int[]{ny, nx});
                    isWater[ny][nx] = true;
                }
                if (arr[ny][nx] == '.' || arr[ny][nx] == 'L') {
                    arr[y][x] = '.';
                    isWater[y][x] = true;
                }
            }
        }

        dfs(depth+1, nextSwanQ, nextIceQ);
    }
}
