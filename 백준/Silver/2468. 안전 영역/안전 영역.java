import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N,ret=1;
    static int [][]arr;
    static boolean[][] visited;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};
    static ArrayList<location> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int z = 1; z <= 100; z++) {
            int cnt = 0;
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j] && arr[i][j] > z) {
                        visited[i][j] = true;
                        bfs(i,j,z);
                        cnt++;
                    }
                }
            }
            ret = Math.max(ret, cnt);
        }
        System.out.println(ret);
    }

    private static void bfs(int y, int x, int z) {
        for (int k = 0; k < 4; k++) {
            int ny = y + dy[k];
            int nx = x + dx[k];

            if (ny < 0 || ny >= N || nx < 0 || nx >= N) {
                continue;
            }
            if (visited[ny][nx] || arr[ny][nx] <= z) {
                continue;
            }
            list.add(new location(ny, nx));
            visited[ny][nx] = true;
        }

        for (; !list.isEmpty(); ) {
            location l = list.get(0);
            list.remove(0);
            bfs(l.y, l.x, z);
        }
    }
}

class location{
    int y;
    int x;

    public location(int y, int x) {
        this.y=y;
        this.x = x;
    }
}