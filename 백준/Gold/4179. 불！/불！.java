import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int R, C, ret = Integer.MAX_VALUE;

    /*
    #: 벽 0
    .: 지나갈 수 있는 공간 1
    J: 지훈이의 미로에서의 초기위치 (지나갈 수 있는 공간) 2
    F: 불이 난 공간 -1
    J는 입력에서 하나만 주어진다.
     */

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};

    static int[][] arr;

    static boolean[][] visited;
    static boolean[][] f_visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new int[R][C];
        visited = new boolean[R][C];
        f_visit = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String[] ss = br.readLine().split("");
            for (int j = 0; j < C; j++) {
                String s = ss[j];
                if (s.equals("#")) {
                    arr[i][j] = 0;
                } else if (s.equals(".")) {
                    arr[i][j] = 1;
                } else if (s.equals("F")) {
                    arr[i][j] = -1;
                } else if (s.equals("J")) {
                    arr[i][j] = 2;
                }
            }
        }


        boj4179_dfs( 0);


        if (ret == Integer.MAX_VALUE) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(ret);
        }

    }

    private static void boj4179_dfs(int cnt) {
        Queue<boj4179_pos> j_q = new LinkedList<>();

        boolean isLive= false; //불에 안 죽었는지
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arr[i][j] == 2 && !visited[i][j]) {
                    if (j == 0 ||  j == C - 1 ||  i == 0 ||  i == R - 1) {
                        ret = Math.min(ret, cnt + 1);
                        return;
                    }
                    j_q.add(new boj4179_pos(i, j));
                    visited[i][j] = true;
                    isLive = true;
                }
            }
        }

        if (!isLive) {
            return;
        }

        while (!j_q.isEmpty()) {
            boj4179_pos j = j_q.poll();
            for (int k = 0; k < 4; k++) {
                int ny = j.y + dy[k];
                int nx = j.x + dx[k];
                if (ny < 0 || ny >= R || nx < 0 || nx >= C || visited[ny][nx] || arr[ny][nx] <= 0) continue;
                arr[ny][nx] = 2;
            }
        }


        //불 번짐
        Queue<boj4179_pos> f_q = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arr[i][j] == -1 && !f_visit[i][j]) {
                    f_q.add(new boj4179_pos(i, j));
                    f_visit[i][j] = true;
                }
            }
        }

        while (!f_q.isEmpty()) {
            boj4179_pos f = f_q.poll();
            int y = f.y;
            int x = f.x;
            for (int k = 0; k < 4; k++) {
                int ny = y + dy[k];
                int nx = x + dx[k];
                if (ny < 0 || ny >= R || nx < 0 || nx >= C || f_visit[ny][nx] || arr[ny][nx] <= 0) continue;
                arr[ny][nx] = -1;
            }
        }

        boj4179_dfs(cnt + 1);
    }
}

class boj4179_pos {
    int y;
    int x;

    public boj4179_pos(int y, int x) {
        this.y = y;
        this.x = x;
    }
}
