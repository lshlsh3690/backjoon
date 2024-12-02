import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static int []dx = {-1, 0, 1, 0};
    public static int []dy = {0, -1, 0, 1};
    public static int [][]arr, visited;
    public static Queue<P> q = new LinkedList<P>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        visited = new int[n][m];
        //입력
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                arr[i][j] = s.charAt(j) - '0';
            }
        }
        q.add(new P(0,0));
        visited[0][0] = 1;
        while (!q.isEmpty()){
            P point = q.poll();
            int x = point.x;
            int y = point.y;
            for (int i=0;i<4;i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= m || ny < 0 || ny >=n || arr[ny][nx] == 0 || visited[ny][nx] > 0){
                    continue;
                }
                q.add(new P(ny,nx));
                visited[ny][nx] = visited[y][x] + 1;
            }
        }
        System.out.println(visited[n-1][m-1]);

    }
}
class P {
    public P(int y, int x){
        this.x=x;
        this.y=y;
    }
    int x;
    int y;
}
