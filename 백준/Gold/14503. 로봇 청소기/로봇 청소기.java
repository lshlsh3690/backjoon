import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    //0인 경우 북쪽,
    //1인 경우 동쪽,
    //2인 경우 남쪽,
    //3인 경우 서쪽을 바라보고 있는 것이다
    public static int N, M, direction,x,y,ans=0;
    public static int[][] room,temp;
    public static boolean[][]isClean;
    public static int[]dx = {0,1,0,-1};
    public static int[]dy = {-1,0,1,0};

    public static void main(String[] args) throws IOException {
        init();
        clean();
        System.out.println(ans);
//        for(int i = 0;i<N;i++){
//            for(int j=0;j<M;j++){
//                System.out.printf("%-3d", temp[i][j]);
//            }
//            System.out.println();
//        }
    }

    private static void clean() {
        while (true) {
            //현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
            if (room[y][x] == 0 && !isClean[y][x]) {
                isClean[y][x] = true;
                ans++;
                temp[y][x] = ans;
            }
            int emptyCnt = 0;
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (ny < 0 || ny >= N || nx < 0 || nx >= M) {
                    continue;
                }
                //비어잇는 칸일 경우
                if (room[ny][nx] == 0 && !isClean[ny][nx]) {
                    emptyCnt++;
//                    direction = i;
//                    y = ny;
//                    x = nx;
                }
            }
            if (emptyCnt>0){
                while (true) {
                    direction--;
                    if (direction == -1)
                        direction = 3;
                    int nx = x + dx[direction];
                    int ny = y + dy[direction];
                    if (nx < 0 || nx >= M || ny < 0 || ny >= N) {
                        continue;
                    }
                    if (room[ny][nx] == 0  && !isClean[ny][nx]){
                        y = ny;
                        x = nx;
                        break;
                    }
                }

            }
            if (emptyCnt == 0) {
                int backDirection = (direction+2)%4;
                int ny = y + dy[backDirection];
                int nx = x + dx[backDirection];
                if (nx < 0 || nx >= M || ny < 0 || ny >= N || room[ny][nx] == 1) {
                    return;
                }
                y = ny;
                x = nx;
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        room = new int[N][M];
        isClean = new boolean[N][M];
        temp = new int[N][M];
        st = new StringTokenizer(br.readLine());
        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        direction = Integer.parseInt(st.nextToken());
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                room[i][j] = Integer.parseInt(st.nextToken());
                temp[i][j] = 0;
            }
        }
    }
}
