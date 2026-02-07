import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R,C, ret=Integer.MIN_VALUE;
    static boolean [] visited = new boolean[26];
    static int [][] arr;
    static int [] dy = {-1,0,1,0};
    static int [] dx = {0,-1,0,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new int[R][C];

        for (int i = 0; i < R; i++) {
            String [] s = br.readLine().split("");
            for (int j = 0; j < C; j++) {
                arr[i][j] = s[j].charAt(0) - 'A';
            }
        }

        visited[arr[0][0]] = true;

        boj1987_dfs(0,0,1);

        System.out.println(ret);
    }

    private static void boj1987_dfs(int y, int x, int cnt) {
        ret = Math.max(ret, cnt);
        for (int k = 0;k<4;k++){
            int ny = y + dy[k];
            int nx = x + dx[k];
            if (ny < 0 || nx < 0 || ny >= R || nx >= C)
                continue;
            if (visited[arr[ny][nx]])
                continue;

            visited[arr[ny][nx]] = true;
            boj1987_dfs(ny,nx,cnt+1);
            visited[arr[ny][nx]] = false;
        }
    }
}
