import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static int N,M, ans=0;
    public static int[][] arr;
    public static ArrayList<point> virus = new ArrayList<>();
    public static Queue<point> t_virus;
    public static ArrayList<point> wall = new ArrayList<>();
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        init();
        solve();
        System.out.println(ans);
    }

    private static void solve() {
        dfs(0);
    }

    private static void dfs(int cnt) {
        if (cnt == 3) {
            ans =  Math.max(ans, bfs());
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 0) {
                    arr[i][j] = 1;
                    dfs(cnt + 1);
                    arr[i][j] = 0;
                }
            }
        }
    }

    private static int bfs() {
        int temp[][] = new int[N][M];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < M; j++) {
                temp[i][j] =arr[i][j];
            }
        }
        boolean[][] visited = new boolean[N][M];
        t_virus = new LinkedList<>();
        for (int i = 0; i < virus.size(); i++) {
            t_virus.add(new point(virus.get(i).x, virus.get(i).y));
        }
        while (!t_virus.isEmpty()){
            point v = t_virus.poll();
            for (int k = 0; k < 4; k++) {
                int nx = v.x + dx[k];
                int ny = v.y + dy[k];
                if (nx < 0 || ny < 0 || nx >= M || ny >= N)
                    continue;
                if (temp[ny][nx] == 0 && !visited[ny][nx]) {
                    temp[ny][nx] = 2;
                    visited[ny][nx] = true;
                    t_virus.add(new point(nx,ny));
                }
            }
        }
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (temp[i][j] == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 1) {
                    wall.add(new point(j,i));
                } else if (arr[i][j] == 2) {
                    virus.add(new point(j,i));
                }
            }
        }
    }
}

class point{
    int x;
    int y;

    public point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
